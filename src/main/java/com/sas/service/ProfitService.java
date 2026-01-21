package com.sas.service;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.Message;

/**
 * @author better
 * 利润数据服务层
 */
public interface ProfitService {
    /**
     * 获取利润数据列表
     *
     * @param companyName 公司名称
     * @return Message<Profit>
     */
    public Message listProfits(JSONObject params);

    public Message getProfitByCompanyKey(String companyKey);
}
