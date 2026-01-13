package com.sas.service;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.Message;

public interface StockService {
    public void getAllDataDebt(JSONObject params);
    public void getAllDataBenefit(JSONObject params);
    public void getAllDataCash(JSONObject params);
    public Message getResultList(JSONObject params);
    public Message updateAll();
}
