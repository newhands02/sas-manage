package com.sas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.AssetEntity;
import com.sas.entity.Message;
import com.sas.mapper.AssetMapper;
import com.sas.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86187
 */
@Service
@Slf4j
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetMapper assetMapper;
    @Override
    public Message listAssets(JSONObject params) {
        Message result=null;
        try {
            List<AssetEntity> assetEntities = assetMapper.listAssets(params);
            result=Message.success(assetEntities);
        }catch (Exception e){
            log.info(e.getLocalizedMessage());
            result=Message.error(e);
        }

        return result;
    }
}
