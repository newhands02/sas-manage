package com.sas.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author better
 */
@Data
@Builder
public class ResultEntity {
    private String name;
    private String place;
    private String code;

    private String reportDate;
    /**调整项-现金 0**/
    private String adjustCash;
    /**债券成本 0.02**/
    private String bondCost;

    /**预期利润调整项 0**/
    private String profitAdjustExcept;

    /**资本开支**/
    private String capitalExpenditure;

    /**调整后利润**/
    private String profitAdjust;

    /**调整后净资产**/
    private String adjustedNetAssets;

    /**净现金**/
    private String netCash;

    private String roe15;

    private String pe;

    private String pb;

    /**PE/ROE**/
    private String pedroe;

    /**应收/营收比-0.3**/
    private String ardrr;

    /**权益乘数**/
    private String equityMultiplier;

    /**毛利率-0.2**/
    private String grossProfitMargin;

    /**当期-利润现金流-1**/
    private String profitCashFlowCurrent;
}
