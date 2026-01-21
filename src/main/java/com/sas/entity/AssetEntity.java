package com.sas.entity;

import com.alibaba.fastjson.JSONObject;
import com.sas.utils.StringUtil;
import lombok.Builder;
import lombok.Data;

/**
 * @author better
 */
@Builder
@Data
public class AssetEntity extends ParentEntity{

    private Long id;

    private String companyKey;

    private String monetaryFunds;

    private String tradingFinancialAssets;

    private String accountsNotesReceivable;

    private String prepayments;

    private String totalOtherReceivables;

    private String inventory;

    private String oneYearNonCurrentAssets;

    private String otherCurrentAssets;

    private String availableForSaleFinancialAssets;

    private String heldToMaturityInvestment;

    private String longTermEquityInvestment;

    private String otherEquityInstrumentInvestments;

    private String otherNonCurrentFinancialAssets;

    private String investmentOrientedRealEstate;

    private String fixedAssets;

    private String constructionInProgress;

    private String intangibleAssets;

    private String goodwill;

    private String longTermDeferredExpenses;

    private String otherNonCurrentAssets;

    private String outputAssetReduction;
    private String reportTime;

    private String updateTime;

    public static AssetEntity getAssetDataByJson(JSONObject data, String companyKey){
        return AssetEntity.builder()
                .companyKey(companyKey)
                .monetaryFunds(StringUtil.spiltJsonStrUnit(data,"货币资金"))
                .tradingFinancialAssets(StringUtil.spiltJsonStrUnit(data,"交易性金融资产"))
                .accountsNotesReceivable(StringUtil.spiltJsonStrUnit(data,"应收账款及应收票据"))
                .prepayments(StringUtil.spiltJsonStrUnit(data,"预付款项"))
                .totalOtherReceivables(StringUtil.spiltJsonStrUnit(data,"其他应收款合计"))
                .inventory(StringUtil.spiltJsonStrUnit(data,"存货"))
                .oneYearNonCurrentAssets(StringUtil.spiltJsonStrUnit(data,"一年内到期的非流动资产"))
                .otherCurrentAssets(StringUtil.spiltJsonStrUnit(data,"其他流动资产"))
                .availableForSaleFinancialAssets(StringUtil.spiltJsonStrUnit(data,"可供出售金融资产"))
                .heldToMaturityInvestment(StringUtil.spiltJsonStrUnit(data,"持有至到期投资"))
                .longTermEquityInvestment(StringUtil.spiltJsonStrUnit(data,"长期股权投资"))
                .otherEquityInstrumentInvestments(StringUtil.spiltJsonStrUnit(data,"其他权益工具投资"))
                .otherNonCurrentFinancialAssets(StringUtil.spiltJsonStrUnit(data,"其他非流动金融资产"))
                .investmentOrientedRealEstate(StringUtil.spiltJsonStrUnit(data,"投资性房地产"))
                .fixedAssets(StringUtil.spiltJsonStrUnit(data,"固定资产合计"))
                .constructionInProgress(StringUtil.spiltJsonStrUnit(data,"在建工程合计"))
                .intangibleAssets(StringUtil.spiltJsonStrUnit(data,"无形资产"))
                .goodwill(StringUtil.spiltJsonStrUnit(data,"商誉"))
                .longTermDeferredExpenses(StringUtil.spiltJsonStrUnit(data,"长期待摊费用"))
                .otherNonCurrentAssets(StringUtil.spiltJsonStrUnit(data,"其他非流动资产"))
                .reportTime(data.getString("报告期"))
                .build();

    }
}