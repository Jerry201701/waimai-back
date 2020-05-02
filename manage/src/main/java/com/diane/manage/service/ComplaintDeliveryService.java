package com.diane.manage.service;

import com.diane.manage.model.ComplaintDelivery;
import com.github.pagehelper.Page;

public interface ComplaintDeliveryService {
    int addComplaintDelivery(ComplaintDelivery complaintDelivery);
    int updateComplaintDelivery(ComplaintDelivery complaintDelivery);
    Page<ComplaintDelivery> findComplaintDeliveryByPage();
}
