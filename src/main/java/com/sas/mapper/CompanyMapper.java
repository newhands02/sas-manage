package com.sas.mapper;

import com.sas.entity.CompanyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface CompanyMapper {

    @Select("select * from sas.company")
    List<CompanyEntity> selectAll();

    List<CompanyEntity> listCompanys(Map map);

    boolean insertCompanyById(CompanyEntity companyEntity);

    boolean deleteCompanyById(String id);

    boolean updateCompanyById(CompanyEntity companyEntity);

    @Update("update sas.companys set bactive = 0 where name = #{name} and code=#{code}")
    boolean disableCompany(Map map);

    @Select("select * from sas.companys where name = #{name} and code=#{code}")
    CompanyEntity selectCompanyByName(Map map);

    @Update("update sas.companys set bactive = 1 where id = #{id}")
    boolean activeCompanyById(String id);
}
