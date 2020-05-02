package com.diane.manage.dao;

import com.diane.manage.model.RevenueStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RevenueStatisticsMapper {
    int addRevenueStatistics(RevenueStatistics revenueStatistics);
}
