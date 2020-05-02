package com.diane.manage.service.impl;

import com.diane.common.http.HttpResult;
import com.diane.manage.dao.*;
import com.diane.manage.model.SysMenu;
import com.diane.manage.model.SysRole;
import com.diane.manage.model.SysUser;
import com.diane.manage.model.SysUserRole;
import com.diane.manage.service.SysMenuService;
import com.diane.manage.service.SysUserService;
import com.diane.common.page.ColumnFilter;
import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.util.PasswordUtils;
import com.diane.manage.vo.LoginVo;
import com.diane.manage.vo.PasswordVo;
import com.diane.manage.vo.PwdCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl  implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private DeliveryMapper deliveryMapper;
	@Autowired
	private SubAdminMapper subAdminMapper;

	@Transactional
	@Override
	public int save(SysUser record) {
		Long id = null;
		if(record.getId() == null || record.getId() == 0) {
			// 新增用户
			sysUserMapper.insertSelective(record);
			id = record.getId();
		} else {
			// 更新用户信息
			sysUserMapper.updateByPrimaryKeySelective(record);
		}
		// 更新用户角色
		if(id != null && id == 0) {
			return 1;
		}
		if(id != null) {
			for(SysUserRole sysUserRole:record.getUserRoles()) {
				sysUserRole.setUserId(id);
			}
		} else {
			sysUserRoleMapper.deleteByUserId(record.getId());
		}
		for(SysUserRole sysUserRole:record.getUserRoles()) {
			sysUserRoleMapper.insertSelective(sysUserRole);
		}
		return 1;
	}

	@Override
	public int delete(SysUser record) {
		return sysUserMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysUser> records) {
		for(SysUser record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysUser findById(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public SysUser findByName(String name) {

		return sysUserMapper.findByName(name);
	}
	
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		PageResult pageResult = null;
		String name = getColumnFilterValue(pageRequest, "name");
		String email = getColumnFilterValue(pageRequest, "email");
		if(name != null) {
			if(email != null) {
				pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByNameAndEmail", name, email);
			} else {
				pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByName", name);
			}
		} else {
			pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper);
		}
		// 加载用户角色信息
		findUserRoles(pageResult);
		return pageResult;
	}

	/**
	 * 获取过滤字段的值
	 * @param filterName
	 * @return
	 */
	public String getColumnFilterValue(PageRequest pageRequest, String filterName) {
		String value = null;
		ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
		if(columnFilter != null) {
			value = columnFilter.getValue();
		}
		return value;
	}
	
	/**
	 * 加载用户角色
	 * @param pageResult
	 */
	private void findUserRoles(PageResult pageResult) {
		List<?> content = pageResult.getContent();
		for(Object object:content) {
			SysUser sysUser = (SysUser) object;
			List<SysUserRole> userRoles = findUserRoles(sysUser.getId());
			sysUser.setUserRoles(userRoles);
			sysUser.setRoleNames(getRoleNames(userRoles));
		}
	}

	private String getRoleNames(List<SysUserRole> userRoles) {
		StringBuilder sb = new StringBuilder();
		for(Iterator<SysUserRole> iter=userRoles.iterator(); iter.hasNext();) {
			SysUserRole userRole = iter.next();
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(userRole.getRoleId());
			if(sysRole == null) {
				continue ;
			}
			sb.append(sysRole.getRemark());
			if(iter.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	@Override
	public Set<String> findPermissions(String userName) {	
		Set<String> perms = new HashSet<>();
		List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
		for(SysMenu sysMenu:sysMenus) {
			if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
				perms.add(sysMenu.getPerms());
			}
		}
		return perms;
	}

	@Override
	public List<SysUserRole> findUserRoles(Long userId) {
		return sysUserRoleMapper.findUserRoles(userId);
	}

	@Override
	public Long addUserInfo(SysUser sysUser) {
		if (sysUser.getId()==null ||sysUser.getId()==0){
			sysUserMapper.insertSelective(sysUser);
			Long id = sysUser.getId();
			for(SysUserRole sysUserRole:sysUser.getUserRoles()) {
				sysUserRole.setUserId(id);
				sysUserRoleMapper.insertSelective(sysUserRole);
			}
			return id;

		}
		return null;
	}


	@Override
	public Long findRegionByUser(Long userId) {
		return sysUserMapper.findRegionByUser(userId);
	}

	@Override
	public Long findUserId(PasswordVo passwordVo) {

		if (passwordVo.getCompanyId()!=null){
		return  companyMapper.findUserIdByCompany(passwordVo.getCompanyId());

		}
		if (passwordVo.getDeliveryId()!=null){
			return deliveryMapper.findUserIdByDelivery(passwordVo.getDeliveryId());

		}
		if (passwordVo.getShopId()!=null){
			return subAdminMapper.findUserIdByShop(passwordVo.getShopId());
		}

		return null;
	}

	@Override
	public int changePassword(String password, Long userId) {
		String salt = PasswordUtils.getSalt();
		String newPassword=PasswordUtils.encode(password,salt);
		return sysUserMapper.changePassword(newPassword,salt,userId);
	}

	@Override
	public String findPasswordById(Long userId) {
		return sysUserMapper.findPasswordById(userId);
	}

	@Override
	public SysUser selectByPrimaryKey(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public PwdCheckVo findPasswordInfo(Long userId) {
		return sysUserMapper.findPasswordInfo(userId);
	}

	@Override
	public SysUser findByMobilAndType(String mobile, Integer registerType) {
		return sysUserMapper.findByMobilAndType(mobile,registerType);
	}

	@Override
	public List<Long> findDeliveryByKeyWord(String keyWord) {
		return sysUserMapper.findDeliveryByKeyWord(keyWord);
	}

	@Override
	public LoginVo showLoginInfo(String userName) {
		return sysUserMapper.showLoginInfo(userName);
	}
}
