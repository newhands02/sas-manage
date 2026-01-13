package com.sas.service;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.Message;

public interface CompanyService {
    Message getCompanys(JSONObject param);

    Message addCompany(JSONObject param);

    Message delCompany(JSONObject param);
}
