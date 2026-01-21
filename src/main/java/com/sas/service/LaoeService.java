package com.sas.service;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.Message;

/**
 * @author better
 * 负债数据服务层
 */
public interface LaoeService {
    /**
     * 获取负债数据列表
     *
     * @param companyName 公司名称
     * @return Message<Laoe>
     */
    public Message listLaoes(JSONObject params);

    public Message updateAllPrice();

    public Message getLaoeByCompanyKey(String companyKey);
}
