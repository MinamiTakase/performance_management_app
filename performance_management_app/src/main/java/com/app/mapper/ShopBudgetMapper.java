package com.app.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;

import com.app.domain.TRN_shop_budget;

@Mapper
public interface ShopBudgetMapper {
	
	Collection<TRN_shop_budget> findAll();
	
	Collection<TRN_shop_budget> findBudget(Integer month_id);
	
	Collection<TRN_shop_budget> findBudgetByTeam(Integer month_id,Integer team_id);

	Collection<TRN_shop_budget> findBudgetByRep(Integer month_id,Integer rep_id);
	
}
