package com.sas.controller;

import com.alibaba.fastjson.JSONObject;
import com.sas.entity.Message;
import com.sas.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/getCompanys")
    public Message getCompanys(@RequestBody JSONObject param){
        return companyService.getCompanys(param);
    }

    @PostMapping("/addCompany")
    public Message addCompany(@RequestBody JSONObject param){
        return companyService.addCompany(param);
    }

    @PostMapping("/delCompany")
    public Message delCompany(@RequestBody JSONObject param){
        return companyService.delCompany(param);
    }
}
