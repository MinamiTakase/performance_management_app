package com.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.MST_month;
import com.app.mapper.MonthMapper;

@Service
public class DropdownService {
	
	@Autowired
	MonthMapper mapper;
	
	//今の年を取得
	public Integer getTodayYear() {
		LocalDate date = LocalDate.now();
		Integer year = date.getYear();
		return year;
	}
	
	//今の月を取得
	public Integer getTodayMonth() {
		LocalDate date = LocalDate.now();
		Integer month = date.getMonthValue();
		return month;
	}
	
	//今の月のIDを取得
	public Integer getTodayId() {
		return getTodayYear() *100 + getTodayMonth();
	}
	
	//現在の期の年月の選択肢を絞り込む
	public List<MST_month> getTodayPeriodMonth() {
		Integer todayId = getTodayId();
		MST_month today = mapper.findMonthById(todayId);
		return mapper.findMonthByPeriod(today.getMonth_period(),todayId);
	}
	
	//期から年月の選択肢を絞り込む
	public List<MST_month> updateMonth(Integer period) {
		return mapper.findMonthByPeriod(period, getTodayId());
	}
	
	//現在以前の年度のリストを取得
	public List<Integer> findPeriod() {
		//今日現在のidを設定
		return mapper.findPeriod(getTodayId());
	}
	
	//当月の年度と年月を取得して表示
	public MST_month findMonth() {
		return mapper.findMonthById(getTodayId());
	}
	

}
