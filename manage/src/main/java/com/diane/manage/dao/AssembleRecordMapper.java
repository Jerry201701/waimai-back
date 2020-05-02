package com.diane.manage.dao;

import com.diane.manage.model.AssembleRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AssembleRecordMapper {
   int addAssembleRecord(AssembleRecord assembleRecord);
}
