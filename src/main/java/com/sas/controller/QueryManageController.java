package com.sas.controller;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.Message;
import com.sas.service.AssetService;
import com.sas.service.LaoeService;
import com.sas.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
