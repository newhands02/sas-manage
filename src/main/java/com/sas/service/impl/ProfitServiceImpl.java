package com.sas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.LaoeEntity;
import com.sas.entity.Message;
import com.sas.entity.ProfitEntity;
import com.sas.mapper.ProfitMapper;
import com.sas.service.ProfitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author better
 * 利润数据服务层实现类
 */
@Service
@Slf4j
public class ProfitServiceImpl implements ProfitService {
    @Autowired
    private ProfitMapper profitMapper;
    @Override
    public Message listProfits(JSONObject params) {
        Message result=null;
        try {
            List<ProfitEntity> profitEntities = profitMapper.listProfits(params);
            result=Message.success(profitEntities);
        }catch (Exception e){
            log.info(e.getLocalizedMessage());
            result=Message.error(e);
        }
        return result;
    }

    @Override
    public Message getProfitByCompanyKey(String companyKey) {
        Message result=null;
        try {
            ProfitEntity profit = profitMapper.selectProfitById(companyKey);
            result=Message.success(profit);
        }catch (Exception e){
            log.info(e.getLocalizedMessage());
            result=Message.error(e);
        }

        return result;
    }
}
