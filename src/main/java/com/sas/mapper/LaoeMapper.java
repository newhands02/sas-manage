package com.sas.mapper;

import com.sas.entity.AssetEntity;
import com.sas.entity.LaoeEntity;
import com.sas.typeHandler.FinancialLiabilitiesHandler;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface LaoeMapper {
    @Select("select * from sas.loae")
    List<LaoeEntity> selectAll();

    List<LaoeEntity> listLaoes(Map map);

    @Results({
            @Result(property = "financialLiabilitiesIncluded",
                    column = "financial_liabilities_included",
                    typeHandler = FinancialLiabilitiesHandler.class),
            // 其他字段映射
    })
    boolean insertLaoe(LaoeEntity laoeEntity);

    LaoeEntity selectLaoeById(String companyKey);

    boolean deleteLaoeById(String id);

    boolean updateLaoeById(LaoeEntity laoeEntity);
}
