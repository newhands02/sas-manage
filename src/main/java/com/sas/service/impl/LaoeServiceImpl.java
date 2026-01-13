package com.sas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.LaoeEntity;
import com.sas.entity.Message;
import com.sas.mapper.LaoeMapper;
import com.sas.service.LaoeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author better
 */
@Service
@Slf4j
public class LaoeServiceImpl implements LaoeService {

    @Autowired
    private LaoeMapper laoeMapper;
    @Override
    public Message listLaoes(JSONObject params) {
        Message result=null;
        try {
            List<LaoeEntity> laoeEntities = laoeMapper.listLaoes(params);
            result=Message.success(laoeEntities);
        }catch (Exception e){
            log.info(e.getLocalizedMessage());
            result=Message.error(e);
        }
        return result;
    }
}
