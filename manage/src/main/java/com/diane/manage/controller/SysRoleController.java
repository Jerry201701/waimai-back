package com.diane.manage.controller;

import com.diane.manage.constants.SysConstants;
import com.diane.manage.dao.SysRoleMapper;
import com.diane.manage.model.SysRole;
import com.diane.manage.model.SysRoleMenu;
import com.diane.manage.service.SysRoleService;
import com.diane.common.http.HttpResult;
import com.diane.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysRole record) {
		SysRole role = sysRoleService.findById(record.getId());
		if(role != null) {
			if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
				return HttpResult.error("超级管理员不允许修改!");
			}
		}
		// 新增角色
		if((record.getId() == null || record.getId() ==0) && !sysRoleService.findByName(record.getName()).isEmpty()) {
			return HttpResult.error("角色名已存在!");
		}
		return HttpResult.ok(sysRoleService.save(record));
	}

	@PreAuthorize("hasAuthority('sys:role:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysRole> records) {
		return HttpResult.ok(sysRoleService.delete(records));
	}

	@PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysRoleService.findPage(pageRequest));
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@GetMapping(value="/findAll")
	public HttpResult findAll() {
		return HttpResult.ok(sysRoleService.findAll());
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@GetMapping(value="/findRoleMenus")
	public HttpResult findRoleMenus(@RequestParam Long roleId) {
		return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/saveRoleMenus")
	public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
		for(SysRoleMenu record:records) {
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
			if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
				// 如果是超级管理员，不允许修改
				return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
			}
		}
		return HttpResult.ok(sysRoleService.saveRoleMenus(records));
	}
}
