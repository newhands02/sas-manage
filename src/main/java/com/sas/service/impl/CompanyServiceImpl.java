package com.sas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.CompanyEntity;
import com.sas.entity.Message;
import com.sas.mapper.CompanyMapper;
import com.sas.service.CompanyService;
import com.sas.utils.SnowflakeIdGenerator;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper mapper;
    @Autowired
    private SnowflakeIdGenerator ins;

    @Override
    public Message getCompanys(JSONObject param) {
        List<CompanyEntity> list = mapper.listCompanys(param);
        return Message.success(list);
    }
    @Override
    public Message addCompany(JSONObject param){
        Message result=null;
        try {
            CompanyEntity companyEntity = mapper.selectCompanyByName(param);
            if (companyEntity!=null){
                mapper.activeCompanyById(companyEntity.getId());
            }else {
                companyEntity = param.toJavaObject(CompanyEntity.class);
                companyEntity.setId(ins.nextIdStr());
                mapper.insertCompanyById(companyEntity);
            }

            result= Message.success(companyEntity.getId()+"-"+companyEntity.getName());
        }catch (Exception e){
            result= Message.error(e);
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Message delCompany(JSONObject param) {
        Message result=null;
        try {
            mapper.disableCompany(param);
            result=Message.success(null);
        }catch (Exception e){
            result= Message.error(e);
        }
        return result;
    }
}
