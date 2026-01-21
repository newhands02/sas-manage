package com.sas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.AssetEntity;
import com.sas.entity.CompanyEntity;
import com.sas.entity.LaoeEntity;
import com.sas.entity.Message;
import com.sas.mapper.CompanyMapper;
import com.sas.mapper.LaoeMapper;
import com.sas.service.LaoeService;
import com.sas.service.StockService;
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
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private StockService stockService;
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
    @Override
    public Message updateAllPrice(){
        Message result=null;
        try{
            List<CompanyEntity> companyEntities = companyMapper.listCompanys(null);
            for (CompanyEntity companyEntity : companyEntities) {
                String resultStr = stockService.getPriceAndMarket(companyEntity.getPlace(), companyEntity.getCode());
                String[] split = resultStr.split("~");
                LaoeEntity laoe = LaoeEntity.builder()
                        .companyKey(companyEntity.getId())
                        .currentPrice(split[3])
                        .marketValue(split[9]).build();
                laoeMapper.updatePriceByCompanyKey(laoe);
                result=Message.success(null);
            }
        }catch (Exception e){
            result=Message.error(e);
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Message getLaoeByCompanyKey(String companyKey) {
        Message result=null;
        try {
            LaoeEntity laoeEntity = laoeMapper.selectLaoeById(companyKey);
            result=Message.success(laoeEntity);
        }catch (Exception e){
            log.info(e.getLocalizedMessage());
            result=Message.error(e);
        }

        return result;
    }
}
