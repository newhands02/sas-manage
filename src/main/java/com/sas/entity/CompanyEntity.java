package com.sas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author better
 */
@Data
public class CompanyEntity extends ParentEntity{
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String updateTime;

}