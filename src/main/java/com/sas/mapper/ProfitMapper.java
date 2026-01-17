package com.sas.mapper;

import com.sas.entity.ProfitEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProfitMapper {
    @Select("select * from sas.profit")
    List<ProfitEntity> selectAll();

    List<ProfitEntity> listProfits(Map map);

    ProfitEntity selectProfitById(String companyKey);

    boolean insertProfitById(ProfitEntity profitEntity);

    boolean deleteProfitById(String id);

    boolean updateProfitById(ProfitEntity profitEntity);
}
