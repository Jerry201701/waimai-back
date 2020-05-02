package com.diane.manage.dao;

import com.diane.manage.model.NoticeInfo;
import com.diane.manage.model.ShopWithdraw;
import com.diane.manage.vo.shop.WithdrawVo;
import com.diane.manage.vo.withdrawal.WithdrawalRespVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ShopWithdrawMapper {
   int insertSelective(ShopWithdraw shopWithdraw);
   int updateByPrimaryKeySelective(ShopWithdraw shopWithdraw);
   Page<ShopWithdraw> findPageByCompany(WithdrawVo withdrawVo);
   Integer findWithdrawAmount(@Param(value = "companyId")Long companyId);
   List<WithdrawalRespVo> findPage();
   int checkWithdraw(@Param(value = "status")Integer status,@Param(value = "withdrawId")Long withdrawId);

}
