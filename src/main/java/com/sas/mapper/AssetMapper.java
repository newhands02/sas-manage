package com.sas.mapper;

import com.sas.entity.AssetEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AssetMapper {
    @Select("select * from assets")
    List<AssetEntity> selectAll();


    List<AssetEntity> listAssets(Map map);

    AssetEntity selectAssetByKey(String companyKey);

    boolean insertAsset(AssetEntity assetEntity);


    boolean deleteAssetById(String id);

    boolean updateAssetById(AssetEntity assetEntity);
}
