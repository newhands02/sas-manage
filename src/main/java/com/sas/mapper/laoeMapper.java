package com.sas.mapper;

import com.sas.entity.assetEntity;
import com.sas.entity.laoeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface laoeMapper {
    @Select("select * from loae")
    List<assetEntity> selectAll();

    List<assetEntity> listLaoes(Map map);

    boolean insertLaoeById(laoeEntity laoeEntity);

    boolean deleteLaoeById(String id);

    boolean updateLaoeById(laoeEntity laoeEntity);
}
