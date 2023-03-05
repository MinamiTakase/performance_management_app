package com.app.domain;

import lombok.Data;

@Data
public class TRN_shop_budget {
	Integer shop_id;
	
	Integer month_id;
	
	Integer shop_budget_sales;
	
	Integer shop_budget_profit;
		
	Integer shop_budget_trial;
	
	Integer shop_budget_contractor;
	
	Integer shop_budget_user_count;
	
	Integer shop_budget_total_use;
}
