package com.diane.manage.dao;

import com.diane.manage.model.ComplaintDelivery;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ComplaintDeliveryMapper {
   int addComplaintDelivery(ComplaintDelivery complaintDelivery);
   int updateComplaintDelivery(ComplaintDelivery complaintDelivery);
   Page<ComplaintDelivery> findComplaintDeliveryByPage();
}
