package com.diane.manage.service;

import com.diane.manage.model.SysUser;
import com.diane.manage.model.SysUserRole;
import com.diane.manage.vo.LoginVo;
import com.diane.manage.vo.PasswordVo;
import com.diane.manage.vo.PwdCheckVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysUserService extends CurdService<SysUser> {

	SysUser findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 查找用户的角色集合
	 * @return
	 */
	List<SysUserRole> findUserRoles(Long userId);
	/*
	商户和配送员注册
	 */
	Long addUserInfo(SysUser sysUser);
	/*
	根据userid查找区域id
	 */
	Long findRegionByUser(Long userId);
	/*
	根据商户id，配送员id，门店管理员id查找用户id
	 */
	Long findUserId(PasswordVo passwordVo);
	/*
	修改密码
	 */
	int changePassword(String password,Long userId);
/*
查找原来的密码
 */
	String findPasswordById(Long userId);
	SysUser selectByPrimaryKey(Long id);
	PwdCheckVo findPasswordInfo(Long userId);

	SysUser findByMobilAndType(String mobile,Integer registerType);

	List<Long> findDeliveryByKeyWord(String keyWord);

	LoginVo showLoginInfo(String userName);



}
