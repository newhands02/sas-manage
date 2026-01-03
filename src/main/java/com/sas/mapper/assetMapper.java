package com.sas.mapper;

import com.sas.entity.assetEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface assetMapper {
    @Select("select * from assets")
    List<assetEntity> selectAll();

    List<assetEntity> listAssets(Map map);

    boolean insertAssetById(assetEntity assetEntity);

    boolean deleteAssetById(String id);

    boolean updateAssetById(assetEntity assetEntity);
}
