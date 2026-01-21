package com.sas.service;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.CompanyEntity;
import com.sas.entity.Message;
import com.sas.entity.ResultEntity;

public interface StockService {
    public void getAllDataDebt(JSONObject params);
    public void getAllDataBenefit(JSONObject params);
    public void getAllDataCash(JSONObject params);
    public Message getResultList(JSONObject params);
    public Message updateAll();

    public String getPriceAndMarket(String place,String symbol);
    public Message getResultByCompany(CompanyEntity companyEntity);
}
