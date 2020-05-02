package com.diane.manage.service;

import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.model.ShopWithdraw;
import com.diane.manage.vo.shop.WithdrawVo;
import com.github.pagehelper.Page;

public interface ShopWithdrawService {
    int insertSelective(ShopWithdraw shopWithdraw);
    int updateByPrimaryKeySelective(ShopWithdraw shopWithdraw);
    Page<ShopWithdraw> findPageByCompany(WithdrawVo withdrawVo);
    PageResult findPage(PageRequest pageRequest);
    int checkWithdraw(Integer status,Long withdrawId);

}
