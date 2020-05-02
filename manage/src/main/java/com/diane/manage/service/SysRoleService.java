package com.diane.manage.service;

import com.diane.manage.model.SysMenu;
import com.diane.manage.model.SysRole;
import com.diane.manage.model.SysRoleMenu;

import java.util.List;

/**
 * 角色管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysRoleService extends CurdService<SysRole> {

	/**
	 * 查询全部
	 * @return
	 */
	List<SysRole> findAll();

	/**
	 * 查询角色菜单集合
	 * @return
	 */
	List<SysMenu> findRoleMenus(Long roleId);

	/**
	 * 保存角色菜单
	 * @param records
	 * @return
	 */
	int saveRoleMenus(List<SysRoleMenu> records);

	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	List<SysRole> findByName(String name);

}
