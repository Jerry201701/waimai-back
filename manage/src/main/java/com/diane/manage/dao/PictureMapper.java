package com.diane.manage.dao;

import com.diane.manage.model.PictureInfo;
import com.diane.manage.vo.good.CopyGoodVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface PictureMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PictureInfo companyInfo);
    int batchInsert(List<PictureInfo> pictureInfos);

    int insertSelective(PictureInfo companyInfo);

    PictureInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PictureInfo companyInfo);

    int updateByPrimaryKey(PictureInfo companyInfo);

    List<PictureInfo> findPage();

    List<PictureInfo> findAll();
    List<PictureInfo> listPictureByShop(@Param(value = "shopId")Long shopId);
    List<PictureInfo> listPictureByGood(@Param(value = "goodId")Long goodId);
    int deletePictureUrlById (@Param(value = "id") Long id);
    int copyGoodPicture(CopyGoodVo copyGoodVo);
    int movePictureByGood(@Param(value = "goodId")Long goodId);

}
