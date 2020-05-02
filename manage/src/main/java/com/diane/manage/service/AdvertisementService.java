package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.Advertisement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertisementService {
    int saveAdvertisement(Advertisement advertisement);
    int updateAdvertisement(Advertisement advertisement);
    PageResult findPage(PageRequest pageRequest);
    int deleteAdvertisement(List<Advertisement> list);
    List<Advertisement> listAdvertisement(Long regionId);
}
