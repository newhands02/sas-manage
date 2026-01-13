package com.sas.controller;


import com.alibaba.fastjson.JSONObject;
import com.sas.entity.Message;
import com.sas.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 86187
 * 请求AKShare
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/getAssetsData")
    public Message getAssetsData(@RequestBody JSONObject params){
        stockService.getAllDataDebt(params);
        return Message.success("");
    }

    @GetMapping("/updateAll")
    public Message updateAll(){
        return stockService.updateAll();
    }

    @GetMapping("/getResultList")
    public Message getResultList(@RequestParam("name")String companyName){
        JSONObject params = new JSONObject();
        params.put("name",companyName);
        return stockService.getResultList(params);
    }
}
