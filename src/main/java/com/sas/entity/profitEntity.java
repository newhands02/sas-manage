package com.sas.entity;

import java.util.Date;
import javax.annotation.Generated;

public class profitEntity {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.company_key")
    private String companyKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.operating_revenue")
    private String operatingRevenue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.goods_sold_cost")
    private String goodsSoldCost;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.sales_expenses")
    private String salesExpenses;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.administrative_expenses")
    private String administrativeExpenses;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.rd_expense")
    private String rdExpense;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.financial_expenses")
    private String financialExpenses;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.net_profit_current")
    private String netProfitCurrent;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.net_profit")
    private String netProfit;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.operating_activities_net_cash")
    private String operatingActivitiesNetCash;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.annual_capital_expenditure")
    private String annualCapitalExpenditure;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.annual_depreciation_amortization")
    private String annualDepreciationAmortization;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.profit_increase")
    private String profitIncrease;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.asset_adjustment_increase")
    private String assetAdjustmentIncrease;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.update_time")
    private Date updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.company_key")
    public String getCompanyKey() {
        return companyKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.company_key")
    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey == null ? null : companyKey.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.operating_revenue")
    public String getOperatingRevenue() {
        return operatingRevenue;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.operating_revenue")
    public void setOperatingRevenue(String operatingRevenue) {
        this.operatingRevenue = operatingRevenue == null ? null : operatingRevenue.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.goods_sold_cost")
    public String getGoodsSoldCost() {
        return goodsSoldCost;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.goods_sold_cost")
    public void setGoodsSoldCost(String goodsSoldCost) {
        this.goodsSoldCost = goodsSoldCost == null ? null : goodsSoldCost.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.sales_expenses")
    public String getSalesExpenses() {
        return salesExpenses;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.sales_expenses")
    public void setSalesExpenses(String salesExpenses) {
        this.salesExpenses = salesExpenses == null ? null : salesExpenses.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.administrative_expenses")
    public String getAdministrativeExpenses() {
        return administrativeExpenses;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.administrative_expenses")
    public void setAdministrativeExpenses(String administrativeExpenses) {
        this.administrativeExpenses = administrativeExpenses == null ? null : administrativeExpenses.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.rd_expense")
    public String getRdExpense() {
        return rdExpense;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.rd_expense")
    public void setRdExpense(String rdExpense) {
        this.rdExpense = rdExpense == null ? null : rdExpense.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.financial_expenses")
    public String getFinancialExpenses() {
        return financialExpenses;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2505853+08:00", comments="Source field: profit.financial_expenses")
    public void setFinancialExpenses(String financialExpenses) {
        this.financialExpenses = financialExpenses == null ? null : financialExpenses.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.net_profit_current")
    public String getNetProfitCurrent() {
        return netProfitCurrent;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.net_profit_current")
    public void setNetProfitCurrent(String netProfitCurrent) {
        this.netProfitCurrent = netProfitCurrent == null ? null : netProfitCurrent.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.net_profit")
    public String getNetProfit() {
        return netProfit;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.net_profit")
    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit == null ? null : netProfit.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.operating_activities_net_cash")
    public String getOperatingActivitiesNetCash() {
        return operatingActivitiesNetCash;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.operating_activities_net_cash")
    public void setOperatingActivitiesNetCash(String operatingActivitiesNetCash) {
        this.operatingActivitiesNetCash = operatingActivitiesNetCash == null ? null : operatingActivitiesNetCash.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.annual_capital_expenditure")
    public String getAnnualCapitalExpenditure() {
        return annualCapitalExpenditure;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.annual_capital_expenditure")
    public void setAnnualCapitalExpenditure(String annualCapitalExpenditure) {
        this.annualCapitalExpenditure = annualCapitalExpenditure == null ? null : annualCapitalExpenditure.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.annual_depreciation_amortization")
    public String getAnnualDepreciationAmortization() {
        return annualDepreciationAmortization;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.annual_depreciation_amortization")
    public void setAnnualDepreciationAmortization(String annualDepreciationAmortization) {
        this.annualDepreciationAmortization = annualDepreciationAmortization == null ? null : annualDepreciationAmortization.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.profit_increase")
    public String getProfitIncrease() {
        return profitIncrease;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.profit_increase")
    public void setProfitIncrease(String profitIncrease) {
        this.profitIncrease = profitIncrease == null ? null : profitIncrease.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.asset_adjustment_increase")
    public String getAssetAdjustmentIncrease() {
        return assetAdjustmentIncrease;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.asset_adjustment_increase")
    public void setAssetAdjustmentIncrease(String assetAdjustmentIncrease) {
        this.assetAdjustmentIncrease = assetAdjustmentIncrease == null ? null : assetAdjustmentIncrease.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2518841+08:00", comments="Source field: profit.update_time")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}