package com.sas.service;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.Message;

/**
 * @author better
 * 资产数据服务层
 */
public interface AssetService {
    /**
     * 获取资产数据列表
     *
     * @param companyName 公司名称
     * @return Message<Asset>
     */
    public Message listAssets(JSONObject params);

    public Message getAssetByCompanyKey(String companyKey);
}
