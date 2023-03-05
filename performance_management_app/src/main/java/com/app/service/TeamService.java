package com.app.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.BudgetAchievement;
import com.app.domain.TRN_rep;
import com.app.domain.TRN_shop_achievement;
import com.app.domain.TRN_shop_budget;
import com.app.domain.TRN_team;
import com.app.mapper.RepMapper;
import com.app.mapper.ShopAchievementMapper;
import com.app.mapper.ShopBudgetMapper;
import com.app.mapper.TeamMapper;

@Service
public class TeamService {
	
	@Autowired ShopBudgetMapper budgetMapper;
	@Autowired ShopAchievementMapper achievementMapper;
	@Autowired RepMapper repMapper;
	@Autowired TeamMapper teamMapper;
	@Autowired CommonService commonService;
	
	//チーム合計予算を引っ張る
	public TRN_shop_budget checkBudgetByTeam(Integer month_id, Integer team_id) {

		return commonService.sumBudget(budgetMapper.findBudgetByTeam(month_id, team_id)) ; 
		
	}
	
	//チーム合計実績を引っ張る
	public TRN_shop_achievement checkAchievementByTeam(Integer month_id, Integer team_id) {

		return commonService.sumAchievement(achievementMapper.findAchievementByTeam(month_id, team_id));
		
	}
	
	//担当別の合計を引っ張る　
	public Collection<BudgetAchievement> checkAchievementEveryRep(Integer month_id, Integer team_id) {
		//返すための型を用意
		Collection<BudgetAchievement> achievementEveryRep = new ArrayList<>();
		
		//担当リストを取り出す
		Collection<TRN_rep> repList = repMapper.outputRepList(team_id, "使用中");

		for(TRN_rep rep : repList) {
			//値を格納するクラス
			BudgetAchievement salesProfit = new BudgetAchievement();
			//チームidを定義
			Integer repId = rep.getRep_id();
			
			//チーム予算	
			TRN_shop_budget repBudget = commonService.sumBudget(budgetMapper.findBudgetByRep(month_id, repId));
			
			//チーム実績
			TRN_shop_achievement teamAchievement = commonService.sumAchievement(achievementMapper.findAchievementByRep(month_id, repId));
			
			//達成率
			Double teamSalesPar = (double)teamAchievement.getShop_achievement_sales() / (double)repBudget.getShop_budget_sales() *100;
			Double teamProfitPar = (double)teamAchievement.getShop_achievement_profit() / (double)repBudget.getShop_budget_profit() *100;
			
			//値を格納
			salesProfit.setRep_id(repId);
			salesProfit.setRep_name(rep.getRep_name());
			salesProfit.setBudget_sales(repBudget.getShop_budget_sales());
			salesProfit.setAchivement_sales(teamAchievement.getShop_achievement_sales());
			salesProfit.setSales_par(teamSalesPar);
			salesProfit.setBudget_profit(repBudget.getShop_budget_profit());
			salesProfit.setAchivement_profit(teamAchievement.getShop_achievement_profit());
			salesProfit.setProfit_par(teamProfitPar);
			
			achievementEveryRep.add(salesProfit);
			
		}
		
		return achievementEveryRep;
		
	}
		
	//50音で最初のチームを引っ張る
	public TRN_team findTopTeam() {
		return teamMapper.findTeam("使用中");
	}
	
	

}
