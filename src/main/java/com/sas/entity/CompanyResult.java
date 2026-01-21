package com.sas.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author 86187
 */
@Data
public class CompanyResult extends CompanyEntity{

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

    private String reportTime;
}
