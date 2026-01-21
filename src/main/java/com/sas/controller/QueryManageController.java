package com.sas.controller;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.CompanyEntity;
import com.sas.entity.Message;
import com.sas.service.AssetService;
import com.sas.service.LaoeService;
import com.sas.service.ProfitService;
import com.sas.service.StockService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Better
 * 查询数据管理
 */
@RestController
@RequestMapping("/query")
public class QueryManageController {

    @Autowired
    private AssetService assetService;
    @Autowired
    private ProfitService profitService;
    @Autowired
    private LaoeService laoeService;
    @Autowired
    private StockService stockService;

    @PostMapping("/assets")
    public Message listAssets(@RequestBody JSONObject params){
        return assetService.listAssets(params);
    }

    @PostMapping("/profits")
    public Message listProfits(@RequestBody JSONObject params){
        return profitService.listProfits(params);
    }

    @PostMapping("/laoes")
    public Message listLaoes(@RequestBody JSONObject params){
        return laoeService.listLaoes(params);
    }

    @GetMapping("/updateAllPrice")
    public Message updateAllPrice(){
        return laoeService.updateAllPrice();
    }
    @GetMapping("/getAssetByCompanyKey")
    public Message getAssetByCompanyKey(@Param("companyKey") String companyKey){
        return assetService.getAssetByCompanyKey(companyKey);
    }
    @GetMapping("/getLaoeByCompanyKey")
    public Message getLaoeByCompanyKey(@Param("companyKey") String companyKey){
        return laoeService.getLaoeByCompanyKey(companyKey);
    }
    @GetMapping("/getProfitByCompanyKey")
    public Message getProfitByCompanyKey(@Param("companyKey") String companyKey){
        return profitService.getProfitByCompanyKey(companyKey);
    }
    @PostMapping("/getResultByCompanyKey")
    public Message getResultByCompanyKey(@RequestBody CompanyEntity param){
        return stockService.getResultByCompany(param);
    }
}
