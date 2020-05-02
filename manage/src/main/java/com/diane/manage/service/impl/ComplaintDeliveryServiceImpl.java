package com.diane.manage.service.impl;

import com.diane.manage.dao.ComplaintDeliveryMapper;
import com.diane.manage.model.ComplaintDelivery;
import com.diane.manage.service.ComplaintDeliveryService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintDeliveryServiceImpl implements ComplaintDeliveryService {
    @Autowired
    private ComplaintDeliveryMapper complaintDeliveryMapper;
    @Override
    public int addComplaintDelivery(ComplaintDelivery complaintDelivery) {
        return complaintDeliveryMapper.addComplaintDelivery(complaintDelivery);
    }

    @Override
    public int updateComplaintDelivery(ComplaintDelivery complaintDelivery) {
        return complaintDeliveryMapper.updateComplaintDelivery(complaintDelivery);
    }

    @Override
    public Page<ComplaintDelivery> findComplaintDeliveryByPage() {
        return complaintDeliveryMapper.findComplaintDeliveryByPage();
    }
}
