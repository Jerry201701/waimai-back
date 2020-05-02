package com.diane.manage.service;

import com.diane.manage.model.BargainRecord;

import java.util.List;

public interface BargainRecordService {
    Integer   addBargainRecord(BargainRecord bargainRecord);
    List<BargainRecord> findBargainRecordById(Long bargainId);
}
