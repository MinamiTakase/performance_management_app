package com.app.domain;

import lombok.Data;

@Data
public class TRN_shop_achievement {

	Integer shop_id;
	
	Integer month_id;
	
	Integer shop_achievement_sales;
	
	Integer shop_achievement_profit;
		
	Integer shop_achievement_trial;
	
	Integer shop_achievement_contractor;
	
	Integer shop_achievement_user_count;
	
	Integer shop_achievement_total_use;
	
	Integer rank;
	
	//ランキング用
	String shop_name;
	
}
