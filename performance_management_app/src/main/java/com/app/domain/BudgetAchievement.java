package com.app.domain;

import lombok.Data;

@Data
public class BudgetAchievement {
	
	private Integer team_id;
	
	private String team_name;
	
	private Integer rep_id;
	
	private String rep_name;
	
	private Integer budget_sales;
	
	private Integer achivement_sales;
	
	private double sales_par;
	
	private Integer budget_profit;
	
	private Integer achivement_profit;
	
	private double profit_par;
	
	private Integer achievement_trial;
	
	private Integer achievement_contractor;
	
	private Integer budget_user_count;

	private Integer achievement_user_count;
	
	private Integer budget_total_use;

	private Integer achievement_total_use;
	

}
