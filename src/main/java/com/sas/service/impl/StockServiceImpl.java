package com.sas.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sas.entity.*;
import com.sas.mapper.AssetMapper;
import com.sas.mapper.CompanyMapper;
import com.sas.mapper.LaoeMapper;
import com.sas.mapper.ProfitMapper;
import com.sas.service.StockService;
import com.sas.utils.SnowflakeIdGenerator;
import com.sas.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import java.util.*;

/**
 * @author better
 */
@Service
@Slf4j
public class StockServiceImpl implements StockService {

    private final int seasonNum = 4;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private LaoeMapper laoeMapper;

    @Autowired
    private ProfitMapper profitMapper;

    @Autowired
    private SnowflakeIdGenerator snowInstance;

    @Value("${constant.debtUrl}")
    private String debtUrl;
    @Value("${constant.benefitUrl}")
    private String benefitUrl;
    @Value("${constant.cashUrl}")
    private String cashUrl;
    @Value("${constant.txUrl}")
    private String txUrl;

    @Override
    public void getAllDataDebt(JSONObject params) {
        requestAkShare(debtUrl,params);
    }

    @Override
    public void getAllDataBenefit(JSONObject params) {
        requestAkShare(benefitUrl,params);
    }

    @Override
    public void getAllDataCash(JSONObject params) {
        requestAkShare(cashUrl,params);
    }

    @Override
    public Message getResultList(JSONObject params){
        Message result=null;
        try {
            List<ResultEntity> resultList=new ArrayList<>();
            List<CompanyEntity> companyEntities = companyMapper.listCompanys(params);
            for (CompanyEntity companyEntity : companyEntities) {
                String companyKey = companyEntity.getId();
                AssetEntity assetEntity = assetMapper.selectAssetByKey(companyKey);
                LaoeEntity laoeEntity = laoeMapper.selectLaoeById(companyKey);
                ProfitEntity profitEntity = profitMapper.selectProfitById(companyKey);
                float profitAdjust=Float.parseFloat(profitEntity.getFinancialExpenses())
                        +Float.parseFloat(profitEntity.getNetProfit());
                float adjustedNetAssets=Float.parseFloat(laoeEntity.getEquityAttributableToPowner())
                        -Float.parseFloat(assetEntity.getOutputAssetReduction())
                        +Float.parseFloat(profitEntity.getAssetAdjustmentIncrease());
                String netCash=StringUtil.floatRound2Str(Float.parseFloat(assetEntity.getMonetaryFunds())
                        +Float.parseFloat(assetEntity.getTradingFinancialAssets())
                        +Float.parseFloat(assetEntity.getPrepayments())
                        -Float.parseFloat(laoeEntity.getShortTermBorrowing())
                        -Float.parseFloat(laoeEntity.getFinancialLiabilitiesIncluded())
                        -Float.parseFloat(laoeEntity.getOneYearNonCurrentLiabilities())
                        -Float.parseFloat(laoeEntity.getLongTermLoans())
                        -Float.parseFloat(laoeEntity.getPayableBonds()));
                float roe15=(profitAdjust/adjustedNetAssets)*100;
                String pe=StringUtil.doubleRound2Str(Float.parseFloat(laoeEntity.getMarketValue())/profitAdjust/0.06);
                String pb=StringUtil.doubleRound2Str(Float.parseFloat(laoeEntity.getMarketValue())/(roe15/6*adjustedNetAssets));
                String pedroe=StringUtil.doubleRound2Str(
                        (Float.parseFloat(laoeEntity.getMarketValue())-Float.parseFloat(laoeEntity.getLongTermLoans()))
                                /Float.parseFloat(laoeEntity.getOneYearNonCurrentLiabilities())
                                /Float.parseFloat(laoeEntity.getPayableBonds()));
                String ardrr=StringUtil.doubleRound2Str(
                        Float.parseFloat(assetEntity.getAccountsNotesReceivable())/Float.parseFloat(profitEntity.getOperatingRevenue()));
                String equityMultiplier=StringUtil.doubleRound2Str(
                        (Float.parseFloat(laoeEntity.getTotalLiabilities())
                                +Float.parseFloat(laoeEntity.getEquityAttributableToPowner()))
                                /Float.parseFloat(laoeEntity.getEquityAttributableToPowner()));
                String grossProfitMargin=StringUtil.doubleRound2Str(
                        (Float.parseFloat(profitEntity.getOperatingRevenue())
                                -Float.parseFloat(profitEntity.getGoodsSoldCost()))
                        /Float.parseFloat(profitEntity.getOperatingRevenue()));
                String profitCashFlowCurrent=StringUtil.doubleRound2Str(
                        Float.parseFloat(profitEntity.getNetProfit())
                                /Float.parseFloat(profitEntity.getOperatingActivitiesNetCash()));
                ResultEntity entity = ResultEntity.builder().name(companyEntity.getName())
                        .place(companyEntity.getPlace()).code(companyEntity.getCode())
                        .reportDate(assetEntity.getReportTime()).adjustCash("0")
                        .bondCost("0.02").profitAdjustExcept("0")
                        .capitalExpenditure(profitEntity.getProfitIncrease())
                        .profitAdjust(StringUtil.floatRound2Str(profitAdjust))
                        .netCash(netCash)
                        .adjustedNetAssets(StringUtil.floatRound2Str(adjustedNetAssets))
                        .roe15(StringUtil.floatRound2Str(roe15))
                        .pe(pe).pb(pb).pedroe(pedroe).ardrr(ardrr)
                        .equityMultiplier(equityMultiplier)
                        .grossProfitMargin(grossProfitMargin)
                        .profitCashFlowCurrent(profitCashFlowCurrent).build();
                resultList.add(entity);
            }
            result=Message.success(resultList);
        }catch (Exception e){
            result=Message.error(e);
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Message updateAll() {
        Message result=null;
        try {
            List<CompanyEntity> companyEntities = companyMapper.listCompanys(null);
            for (CompanyEntity companyEntity : companyEntities) {
                String symbol = companyEntity.getCode();
                if (!StringUtils.isEmpty(symbol)){
                    updateAssetAndDebt(companyEntity);
                    updateProfitAndCash(symbol,companyEntity.getId());
                }
            }
            result=Message.success("成功");
        }catch (Exception e){
            result=Message.error(e);
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public void updateProfitAndCash(String symbol,String companyKey){
        JSONObject seasonParam=new JSONObject();
        seasonParam.put("indicator","按单季度");
        seasonParam.put("symbol",symbol);
        //获取利润数据
        JSONArray allData=requestAkShare(benefitUrl,seasonParam);
        //营业收入
        String incomeYear = getTotalFloatStr(allData,"一、营业总收入",seasonNum);
        //营业成本
        String costYear = getTotalFloatStr(allData,"其中：营业成本",seasonNum);
        float saleExpense= getTotalFloat(allData,"销售费用",seasonNum);
        float adExpense= getTotalFloat(allData,"管理费用",seasonNum);
        float rdExpense= getTotalFloat(allData,"研发费用",seasonNum);
        float financialExpense= getTotalFloat(allData,"财务费用",seasonNum);
        String netProfitCurrent=StringUtil.spiltUnit(allData.getJSONObject(0).getString("归属于母公司所有者的净利润"));
        String netProfit= getTotalFloatStr(allData,"归属于母公司所有者的净利润",seasonNum);
        //获取现金流量数据
        seasonParam.put("indicator","按报告期");
        JSONArray allCashData=requestAkShare(cashUrl,seasonParam);
        String operatingActivitiesNetCash=getTotalFloatStr(allCashData,"经营活动产生的现金流量净额",2);
        JSONObject currentData=allCashData.getJSONObject(0);
        float ace=json2float(currentData,"购建固定资产、无形资产和其他长期资产支付的现金");
        float ada=json2float(currentData,"无形资产摊销")+json2float(currentData,"固定资产折旧、油气资产折耗、生产性生物资产折旧");
        double adi=financialExpense+rdExpense+adExpense*0.9+saleExpense*0.8;
        ProfitEntity profit= ProfitEntity.builder()
                .operatingRevenue(incomeYear)
                .goodsSoldCost(costYear)
                .salesExpenses(StringUtil.floatRound2Str(saleExpense))
                .administrativeExpenses(StringUtil.floatRound2Str(adExpense))
                .rdExpense(StringUtil.floatRound2Str(rdExpense))
                .financialExpenses(StringUtil.floatRound2Str(financialExpense))
                .netProfitCurrent(netProfitCurrent)
                .netProfit(netProfit)
                .operatingActivitiesNetCash(operatingActivitiesNetCash)
                .annualCapitalExpenditure(StringUtil.floatRound2Str(ace))
                .annualDepreciationAmortization(StringUtil.floatRound2Str(ada))
                .profitIncrease(StringUtil.floatRound2Str(ada-ace))
                .assetAdjustmentIncrease(StringUtil.doubleRound2Str(adi))
                .companyKey(companyKey)
                .reportTime(currentData.getString("报告期"))
                .build();
        ProfitEntity exitProfit = profitMapper.selectProfitById(companyKey);
        if (exitProfit==null){
            profitMapper.insertProfitById(profit);
        }else {
            profit.setId(exitProfit.getId());
            profitMapper.updateProfitById(profit);
        }
    }

    public float getTotalFloat(JSONArray allData,String jsonkey,int count){
        float result=0;
        for (int i = 0; i < count; i++) {
            float f=json2float(allData.getJSONObject(i),jsonkey);
            result+=f;
        }
        return result;
    }
    public String getTotalFloatStr(JSONArray allData,String jsonkey,int count){
        float result=0;
        for (int i = 0; i < count; i++) {
            float f=json2float(allData.getJSONObject(i),jsonkey);
            result+=f;
        }
        return StringUtil.floatRound2Str(result);
    }

    public float json2float(JSONObject data,String jsonkey){
        return StringUtil.str2float(StringUtil.spiltUnit(data.getString(jsonkey)));
    }

    public void updateAssetAndDebt(CompanyEntity companyEntity){
        String symbol=companyEntity.getCode();
        String companyKey=companyEntity.getId();
        JSONObject reportParams = new JSONObject();
        reportParams.put("indicator","按报告期");
        reportParams.put("symbol",symbol);
        JSONArray allData=requestAkShare(debtUrl,reportParams);
        JSONObject data = allData.getJSONObject(0);
        AssetEntity assetEntity = AssetEntity.getAssetDataByJson(data,companyKey);
        String outputAssetReduction=StringUtil.doubleRound2Str(StringUtil.str2float(assetEntity.getGoodwill())
                +StringUtil.str2float(assetEntity.getIntangibleAssets())*0.5
                +StringUtil.str2float(assetEntity.getFixedAssets())*0.2
                +StringUtil.str2float(assetEntity.getInventory())*0.1
                +StringUtil.str2float(assetEntity.getAccountsNotesReceivable())*0.05);
        assetEntity.setOutputAssetReduction(outputAssetReduction);
        AssetEntity assetEntityExit = reportNotExitAsset(companyKey);
        if (assetEntityExit==null){
            //当前报告期的数据不存在
            assetMapper.insertAsset(assetEntity);
        }else {
            assetEntity.setId(assetEntityExit.getId());
            assetMapper.updateAssetById(assetEntity);
        }
        LaoeEntity laoeEntity = reportNotExitLaoe(companyKey);
        LaoeEntity laoeDataByJson=getTargetLaoeEntity(data,companyKey,companyEntity.getPlace(),symbol);
        if (laoeEntity==null){
            laoeMapper.insertLaoe(laoeDataByJson);
        }else {
            laoeDataByJson.setId(laoeEntity.getId());
            laoeMapper.updateLaoeById(laoeDataByJson);
        }
    }

    public LaoeEntity getTargetLaoeEntity(JSONObject data, String companyKey,String place,String symbol){
        LaoeEntity laoeData = LaoeEntity.getLaoeDataByJson(data, companyKey);
        String resultStr = getPriceAndMarket(place, symbol);
        String[] split = resultStr.split("~");
        laoeData.setCurrentPrice(split[3]);
        laoeData.setMarketValue(split[9]);
        return laoeData;
    }

    public AssetEntity reportNotExitAsset(String companyKey){
        return assetMapper.selectAssetByKey(companyKey);
    }
    public LaoeEntity reportNotExitLaoe(String companyKey){
        return laoeMapper.selectLaoeById(companyKey);
    }

    private String getPriceAndMarket(String place,String symbol){
        String url=txUrl+"/q=s_"+place+symbol;
        return restTemplate.getForObject(url, String.class);
    }

    private JSONArray requestAkShare(String url, JSONObject params){
        String indicator=params.getString("indicator");
        String symbol=params.getString("symbol");
        String targetUrl=url+"?indicator="+indicator+"&symbol="+symbol;
        System.out.println("url是"+targetUrl);
        return restTemplate.getForObject(targetUrl, JSONArray.class);
    }
}
