package com.diane.manage.service.impl;

import com.diane.manage.dao.DeliveryRegionMapper;
import com.diane.manage.dao.SysDeptMapper;
import com.diane.manage.model.DeliveryRegion;
import com.diane.manage.model.SysDept;
import com.diane.manage.service.SysDeptService;
import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {

	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Autowired
	private DeliveryRegionMapper deliveryRegionMapper;

	@Override
	public int save(SysDept record) {
		/*
		if(record.getId() == null || record.getId() == 0) {
			return sysDeptMapper.insertSelective(record);
		}
		return sysDeptMapper.updateByPrimaryKeySelective(record);
		 */
		DeliveryRegion deliveryRegion= new DeliveryRegion();
		deliveryRegion.setCreateTime(new Timestamp(System.currentTimeMillis()));
		deliveryRegion.setRegionName(record.getName());
		if(record.getId() == null || record.getId() == 0) {
			return deliveryRegionMapper.insertSelective(deliveryRegion);
		}
		return deliveryRegionMapper.updateByPrimaryKeySelective(deliveryRegion);

	}

	@Override
	public int delete(SysDept record) {
		return sysDeptMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysDept> records) {
		for(SysDept record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysDept findById(Long id) {
		return sysDeptMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysDeptMapper);
	}

	@Override
	public List<SysDept> findTree() {
		List<SysDept> sysDepts = new ArrayList<>();
		List<SysDept> depts = sysDeptMapper.findAll();
		for (SysDept dept : depts) {
			if (dept.getParentId() == null || dept.getParentId() == 0) {
				dept.setLevel(0);
				sysDepts.add(dept);
			}
		}
		findChildren(sysDepts, depts);
		return sysDepts;
	}

	private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
		for (SysDept sysDept : sysDepts) {
			List<SysDept> children = new ArrayList<>();
			for (SysDept dept : depts) {
				if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
					dept.setParentName(dept.getName());
					dept.setLevel(sysDept.getLevel() + 1);
					children.add(dept);
				}
			}
			sysDept.setChildren(children);
			findChildren(children, depts);
		}
	}

}
