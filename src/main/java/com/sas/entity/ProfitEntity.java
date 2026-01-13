package com.sas.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author better
 */
@Data
@Builder
public class ProfitEntity extends ParentEntity{
    private Long id;

    private String companyKey;

    /**营业收入-TTM**/
    private String operatingRevenue;

    /**营业成本-TTM**/
    private String goodsSoldCost;

    /**销售费用-TTM**/
    private String salesExpenses;

    /**管理费用-TTM**/
    private String administrativeExpenses;

    /**研发费用-TTM**/
    private String rdExpense;

    /**财务费用-TTM**/
    private String financialExpenses;

    /**净利润当期**/
    private String netProfitCurrent;

    /**净利润TTM**/
    private String netProfit;

    /**经营活动现金净额**/
    private String operatingActivitiesNetCash;

    /**资本开支-年**/
    private String annualCapitalExpenditure;

    /**折旧与摊销-年**/
    private String annualDepreciationAmortization;

    /**利润调增**/
    private String profitIncrease;

    /**资产调增**/
    private String assetAdjustmentIncrease;

    private String reportTime;

    private LocalDate updateTime;


}