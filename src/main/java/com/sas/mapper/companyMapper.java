package com.sas.mapper;

import com.sas.entity.companyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface companyMapper {

    @Select("select * from company")
    List<companyEntity> selectAll();

    List<companyEntity> listCompanys(Map map);

    boolean insertCompanyById(companyEntity companyEntity);

    boolean deleteCompanyById(String id);

    boolean updateCompanyById(companyEntity companyEntity);
}
