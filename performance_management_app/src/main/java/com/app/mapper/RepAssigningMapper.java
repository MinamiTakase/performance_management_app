package com.app.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;

import com.app.domain.TRN_rep_assigning;

@Mapper
public interface RepAssigningMapper {
	
	Collection<TRN_rep_assigning> findAll();
	
	TRN_rep_assigning findNewOne(Integer shop_id);
	
	void save(TRN_rep_assigning rep_assigning);
	
	void delete(Integer shop_id, Integer start_month_id);
}
