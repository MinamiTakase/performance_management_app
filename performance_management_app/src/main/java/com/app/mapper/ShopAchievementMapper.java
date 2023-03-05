package com.app.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;

import com.app.domain.TRN_shop_achievement;

@Mapper
public interface ShopAchievementMapper {
	
	Collection<TRN_shop_achievement> findAll();
	
	Collection<TRN_shop_achievement> findAchievement(Integer month_id);
	
	Collection<TRN_shop_achievement> findAchievementByTeam(Integer month_id, Integer team_id);
	
	Collection<TRN_shop_achievement> findAchievementByRep(Integer month_id, Integer rep_id);
	
	Collection<TRN_shop_achievement> findShopRank(Integer month_id, String item);
	
	
}
