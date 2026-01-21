package com.sas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.*;
import com.sas.mapper.AssetMapper;
import com.sas.mapper.CompanyMapper;
import com.sas.mapper.LaoeMapper;
import com.sas.mapper.ProfitMapper;
import com.sas.service.CompanyService;
import com.sas.utils.SnowflakeIdGenerator;
import com.sas.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper mapper;
    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private LaoeMapper laoeMapper;
    @Autowired
    private ProfitMapper profitMapper;

    @Autowired
    private SnowflakeIdGenerator ins;

    @Override
    public Message getCompanys(JSONObject param) {
        Message result=null;
        try {
            List<CompanyResult> resultList=new ArrayList<>();
            List<CompanyEntity> list = mapper.listCompanys(param);
            for (CompanyEntity companyEntity : list) {
                CompanyResult resultByCompany = getResultByCompany(companyEntity);
                resultList.add(resultByCompany);
            }
            result=Message.success(resultList);
        }catch (Exception e){
            result= Message.error(e);
        }

        return result;
    }
    private CompanyResult getResultByCompany(CompanyEntity companyEntity){
        String companyKey = companyEntity.getId();
        AssetEntity assetEntity = assetMapper.selectAssetByKey(companyKey);
        LaoeEntity laoeEntity = laoeMapper.selectLaoeById(companyKey);
        ProfitEntity profitEntity = profitMapper.selectProfitById(companyKey);
        CompanyResult result=new CompanyResult();
        result.setId(companyEntity.getId());
        result.setUpdateTime(companyEntity.getUpdateTime());
        result.setName(companyEntity.getName());
        result.setPlace(companyEntity.getPlace());
        result.setCode(companyEntity.getCode());
        if (assetEntity !=null && laoeEntity !=null && profitEntity !=null){
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
            result.setPe(pe);
            result.setPb(pb);
            String pedroe=StringUtil.doubleRound2Str(
                    (Float.parseFloat(laoeEntity.getMarketValue())-Float.parseFloat(laoeEntity.getLongTermLoans()))
                            /Float.parseFloat(laoeEntity.getOneYearNonCurrentLiabilities())
                            /Float.parseFloat(laoeEntity.getPayableBonds()));
            result.setPedroe(pedroe);
            String ardrr=StringUtil.doubleRound2Str(
                    Float.parseFloat(assetEntity.getAccountsNotesReceivable())/Float.parseFloat(profitEntity.getOperatingRevenue()));
            result.setArdrr(ardrr);
            String equityMultiplier=StringUtil.doubleRound2Str(
                    (Float.parseFloat(laoeEntity.getTotalLiabilities())
                            +Float.parseFloat(laoeEntity.getEquityAttributableToPowner()))
                            /Float.parseFloat(laoeEntity.getEquityAttributableToPowner()));
            result.setEquityMultiplier(equityMultiplier);
            String grossProfitMargin=StringUtil.doubleRound2Str(
                    (Float.parseFloat(profitEntity.getOperatingRevenue())
                            -Float.parseFloat(profitEntity.getGoodsSoldCost()))
                            /Float.parseFloat(profitEntity.getOperatingRevenue()));
            result.setGrossProfitMargin(grossProfitMargin);
            String profitCashFlowCurrent=StringUtil.doubleRound2Str(
                    Float.parseFloat(profitEntity.getNetProfit())
                            /Float.parseFloat(profitEntity.getOperatingActivitiesNetCash()));
            result.setProfitCashFlowCurrent(profitCashFlowCurrent);
            String reportDate=assetEntity.getReportTime();
            result.setReportTime(reportDate);
        }
        return result;
    }
    @Override
    public Message addCompany(JSONObject param){
        Message result=null;
        try {
            CompanyEntity companyEntity = mapper.selectCompanyByName(param);
            if (companyEntity!=null){
                mapper.activeCompanyById(companyEntity.getId());
            }else {
                companyEntity = param.toJavaObject(CompanyEntity.class);
                companyEntity.setId(ins.nextIdStr());
                mapper.insertCompanyById(companyEntity);
            }

            result= Message.success(companyEntity.getId()+"-"+companyEntity.getName());
        }catch (Exception e){
            result= Message.error(e);
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Message delCompany(JSONObject param) {
        Message result=null;
        mapper.disableCompany(param);
        //删除其他表中数据
        String id = param.getString("id");
        if (!StringUtil.isBlank(id)){
            assetMapper.deleteAssetById(id);
            laoeMapper.deleteLaoeById(id);
            profitMapper.deleteProfitById(id);
        }
        result=Message.success(null);
        return result;
    }
}
