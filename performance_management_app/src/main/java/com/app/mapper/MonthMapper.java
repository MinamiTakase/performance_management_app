package com.app.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.app.domain.MST_month;

@Mapper
public interface MonthMapper {
    Collection<MST_month> findAll();

    //todayと期を引数に渡して、該当の期のtoday以前の年月をリストに表示
    List<MST_month> findMonthByPeriod(Integer month_period, Integer today);
    
    //todayを引数に渡して、today以前の年度のみをリストで出したい
    List<Integer> findPeriod(Integer today_id);
    
    MST_month findMonthById(Integer month_id);
    
}
