package com.sas.entity;

import java.util.Date;
import javax.annotation.Generated;

public class companyEntity {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.code")
    private String code;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.place")
    private String place;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.update_time")
    private Date updateTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.code")
    public String getCode() {
        return code;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.code")
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.place")
    public String getPlace() {
        return place;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.place")
    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2026-01-02T22:49:58.2453549+08:00", comments="Source field: companys.update_time")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}