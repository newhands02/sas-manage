package com.sas.entity;

import com.alibaba.fastjson.JSONObject;
import com.sas.utils.StringUtil;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;


/**
 * @author better
 */
@Data
@Builder
public class LaoeEntity extends ParentEntity{
    private Long id;

    private String companyKey;

    private String shortTermBorrowing;

    private String financialLiabilitiesIncluded;

    private String accountsPayable;

    private String advanceFromCustomers;

    private String contractLiability;

    private String otherPayables;

    private String oneYearNonCurrentLiabilities;

    private String otherCurrentLiabilities;

    private String longTermLoans;

    private String payableBonds;

    private String longTermPayables;

    private String provisions;

    private String otherNonCurrentLiabilities;

    private String totalLiabilities;

    private String equityAttributableToPowner;

    private String shareCapital;

    private String currentPrice;

    private String marketValue;

    private String reportTime;

    private LocalDate updateTime;

    public static LaoeEntity getLaoeDataByJson(JSONObject data, String companyKey){
        return LaoeEntity.builder()
                .companyKey(companyKey)
                .shortTermBorrowing(StringUtil.spiltJsonStrUnit(data,"短期借款"))
                .financialLiabilitiesIncluded(StringUtil.spiltJsonStrUnit(data,"以公允价值计量且其变动计入当期损益的金融负债"))
                .accountsPayable(StringUtil.spiltJsonStrUnit(data,"应付账款"))
                .advanceFromCustomers(StringUtil.spiltJsonStrUnit(data,"预收款项"))
                .contractLiability(StringUtil.spiltJsonStrUnit(data,"合同负债"))
                .otherPayables(StringUtil.spiltJsonStrUnit(data,"其他应付款合计"))
                .oneYearNonCurrentLiabilities(StringUtil.spiltJsonStrUnit(data,"一年内到期的非流动负债"))
                .otherCurrentLiabilities(StringUtil.spiltJsonStrUnit(data,"其他流动负债"))
                .longTermLoans(StringUtil.spiltJsonStrUnit(data,"长期借款"))
                .payableBonds(StringUtil.spiltJsonStrUnit(data,"应付债券"))
                .longTermPayables(StringUtil.spiltJsonStrUnit(data,"长期应付款合计"))
                .provisions(StringUtil.spiltJsonStrUnit(data,"预计负债"))
                .otherNonCurrentLiabilities(StringUtil.spiltJsonStrUnit(data,"其他非流动负债"))
                .totalLiabilities(StringUtil.spiltJsonStrUnit(data,"负债合计"))
                .equityAttributableToPowner(StringUtil.spiltJsonStrUnit(data,"归属于母公司所有者权益合计"))
                .shareCapital(StringUtil.spiltJsonStrUnit(data,"实收资本（或股本）"))
                .currentPrice(StringUtil.spiltJsonStrUnit(data,"现价"))
                .marketValue(StringUtil.spiltJsonStrUnit(data,"市场价值"))
                .reportTime(data.getString("报告期"))
                .build();
    }
}