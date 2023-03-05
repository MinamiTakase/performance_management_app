package com.app.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.BudgetAchievement;
import com.app.domain.TRN_shop_achievement;
import com.app.domain.TRN_shop_budget;
import com.app.domain.TRN_team;
import com.app.mapper.ShopAchievementMapper;
import com.app.mapper.ShopBudgetMapper;
import com.app.mapper.TeamMapper;

@Service
public class DashboadService {
	
	@Autowired ShopBudgetMapper budgetMapper;
	@Autowired ShopAchievementMapper achievementMapper;
	@Autowired TeamMapper teamMapper;
	@Autowired CommonService commonService;
	
	
	//全店合計予算を引っ張る
	public TRN_shop_budget checkBudget(Integer month_id) {

		return commonService.sumBudget(budgetMapper.findBudget(month_id)) ; 
		
	}
	
	//全店合計実績を引っ張る
	public TRN_shop_achievement checkAchievement(Integer month_id) {

		return commonService.sumAchievement(achievementMapper.findAchievement(month_id));
		
	}

	//チーム別の合計を引っ張る　
	public Collection<BudgetAchievement> checkAchievementEveryTeam(Integer month_id) {
		//返すための型を用意
		Collection<BudgetAchievement> achievementEveryTeam = new ArrayList<>();
		
		//チームリストを取り出す
		Collection<TRN_team> teamList = commonService.outPutTeamList();

		for(TRN_team team : teamList) {
			//値を格納するクラス
			BudgetAchievement salesProfit = new BudgetAchievement();
			//チームidを定義
			Integer teamId = team.getTeam_id();
			
			//チーム予算	
			TRN_shop_budget teamBudget = commonService.sumBudget(budgetMapper.findBudgetByTeam(month_id, teamId));
			
			//チーム実績
			TRN_shop_achievement teamAchievement = commonService.sumAchievement(achievementMapper.findAchievementByTeam(month_id, teamId));
			
			//達成率
			Double teamSalesPar = (double)teamAchievement.getShop_achievement_sales() / (double)teamBudget.getShop_budget_sales() *100;
			Double teamProfitPar = (double)teamAchievement.getShop_achievement_profit() / (double)teamBudget.getShop_budget_profit() *100;
			
			//値を格納
			salesProfit.setTeam_id(teamId);
			salesProfit.setTeam_name(team.getTeam_name());
			salesProfit.setBudget_sales(teamBudget.getShop_budget_sales());
			salesProfit.setAchivement_sales(teamAchievement.getShop_achievement_sales());
			salesProfit.setSales_par(teamSalesPar);
			salesProfit.setBudget_profit(teamBudget.getShop_budget_profit());
			salesProfit.setAchivement_profit(teamAchievement.getShop_achievement_profit());
			salesProfit.setProfit_par(teamProfitPar);
			
			achievementEveryTeam.add(salesProfit);
			
		}
		
		return achievementEveryTeam;
		
	}
	

	//体験ランキングを引っ張る
	public Collection<TRN_shop_achievement> checkTrialRanking(Integer month_id){
		
		return achievementMapper.findShopRank(month_id, "shop_achievement_trial");
	}
	//契約ランキングを引っ張る
	public Collection<TRN_shop_achievement> checkContractorRanking(Integer month_id){
		
		return achievementMapper.findShopRank(month_id, "shop_achievement_contractor");
	}
	//稼働人数ランキングを引っ張る
	public Collection<TRN_shop_achievement> checkUserCountRanking(Integer month_id){
		
		return achievementMapper.findShopRank(month_id, "shop_achievement_user_count");
	}
	//延べ利用人数ランキングを引っ張る
	public Collection<TRN_shop_achievement> checkTotalUseRanking(Integer month_id){
		
		return achievementMapper.findShopRank(month_id, "shop_achievement_total_use");
	}

}
