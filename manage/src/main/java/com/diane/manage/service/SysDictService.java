package com.diane.manage.service;

import com.diane.manage.model.SysDict;


import java.util.List;

/**
 * 字典管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDictService extends CurdService<SysDict> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysDict> findByLable(String lable);
	List<SysDict> findDictionaryByConditions(Integer category);
}
