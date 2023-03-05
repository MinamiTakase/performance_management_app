package com.app.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.app.domain.TRN_shop_achievement;
import com.app.domain.TRN_shop_budget;
import com.app.domain.TRN_team;
import com.app.mapper.MonthMapper;
import com.app.mapper.ShopAchievementMapper;
import com.app.mapper.ShopBudgetMapper;
import com.app.mapper.TeamMapper;

@Service
public class CommonService {
	
	@Autowired	ShopBudgetMapper budgetMapper;
	@Autowired	ShopAchievementMapper achievementMapper;
	@Autowired  TeamMapper teamMapper;
	@Autowired	MonthMapper monthMapper;
	@Autowired 	DropdownService dropdownService;
	
	//ドロップダウンリストに候補を格納
	public void selectList(Model model) {
		model.addAttribute("periodList", dropdownService.findPeriod());
		model.addAttribute("monthList",dropdownService.getTodayPeriodMonth());
	}
	
	//年月すべてのドロップダウンリスト格納
	public void selectMonthListAll(Model model) {
		model.addAttribute("monthListAll", monthMapper.findAll());
	}
	
	//予算合計を計算する
	public TRN_shop_budget sumBudget(Collection<TRN_shop_budget> budgetList) {
		
		TRN_shop_budget sum = new TRN_shop_budget();
		
		Integer sales = 0;
		Integer profit = 0;
		Integer trial = 0;	
		Integer contractor = 0;
		Integer user_count = 0;
		Integer total_use = 0;
		
		for(TRN_shop_budget budget : budgetList) {
			sales += budget.getShop_budget_sales();
			profit += budget.getShop_budget_profit();
			trial += budget.getShop_budget_trial();
			contractor += budget.getShop_budget_contractor();
			user_count += budget.getShop_budget_user_count();
			total_use += budget.getShop_budget_total_use();
		}
		
		sum.setShop_budget_sales(sales);
		sum.setShop_budget_profit(profit);
		sum.setShop_budget_trial(trial);
		sum.setShop_budget_contractor(contractor);
		sum.setShop_budget_user_count(user_count);
		sum.setShop_budget_total_use(total_use);
		
		return sum;	
		
	}

	//実績合計を計算する
	public TRN_shop_achievement sumAchievement(Collection<TRN_shop_achievement> achievementList) {

		TRN_shop_achievement sum = new TRN_shop_achievement();
		
		Integer sales = 0;
		Integer profit = 0;
		Integer trial = 0;
		Integer contractor = 0;
		Integer user_count = 0;
		Integer total_use = 0;
		
		
		for(TRN_shop_achievement achievement : achievementList) {
			sales += achievement.getShop_achievement_sales();
			profit += achievement.getShop_achievement_profit();
			trial += achievement.getShop_achievement_trial();
			contractor += achievement.getShop_achievement_contractor();
			user_count += achievement.getShop_achievement_user_count();
			total_use += achievement.getShop_achievement_total_use();
		}
		
		sum.setShop_achievement_sales(sales);
		sum.setShop_achievement_profit(profit);
		sum.setShop_achievement_trial(trial);
		sum.setShop_achievement_contractor(contractor);
		sum.setShop_achievement_user_count(user_count);
		sum.setShop_achievement_total_use(total_use);
		
		return sum;

	}
	
	//達成率を計算する
	public double calcPar(int achievement, int budget) {
		//桁が大きすぎて割り算の結果が四捨五入されてしまう→片方を小数点にして渡すしかない。
		return (double)achievement / (double)budget *100;
	}

	//現在存在するチームのリストを作成
	public Collection<TRN_team> outPutTeamList(){
		return teamMapper.outputTeamList("使用中");
	}
	
}
