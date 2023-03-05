package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.domain.MST_month;
import com.app.domain.TRN_shop_achievement;
import com.app.domain.TRN_shop_budget;
import com.app.service.CommonService;
import com.app.service.DashboadService;
import com.app.service.DropdownService;

@Controller
@RequestMapping("dashboad")
public class DashboadController {
	
	@Autowired DropdownService dropdownService;
	@Autowired DashboadService dashboadService;
	@Autowired CommonService commonService;
	
	//何も選択していない場合　→当月を表示（未対応・５日までは前月を表示したい）
	@GetMapping
	public String showDashboadGet(Model model) {
		//当月の年度と年月を取得して表示
		Integer todayId = dropdownService.getTodayId();
		
		commonService.selectList(model);
		
		//該当の予算と実績を呼び出して値を定義(達成率計算で使用するため)
		TRN_shop_achievement achievement =dashboadService.checkAchievement(todayId);
		TRN_shop_budget budget = dashboadService.checkBudget(todayId);
		model.addAttribute("achievement", achievement);
		model.addAttribute("budget",budget);
		
		//達成率を計算して格納
		model.addAttribute("salesPar", commonService.calcPar(achievement.getShop_achievement_sales(), budget.getShop_budget_sales())); 
		model.addAttribute("profitPar",commonService.calcPar(achievement.getShop_achievement_profit(), budget.getShop_budget_profit())); 
	
		//ログインユーザー名を表示させる
		
		//チーム別一覧を表示
		model.addAttribute("teamAchievementList", dashboadService.checkAchievementEveryTeam(todayId));
		
		//店舗ランキングを表示
		model.addAttribute("trialRankList", dashboadService.checkTrialRanking(todayId));
		model.addAttribute("contractorRankList", dashboadService.checkContractorRanking(todayId));
		model.addAttribute("UserCountRankList", dashboadService.checkUserCountRanking(todayId));
		model.addAttribute("totalUseRankList", dashboadService.checkTotalUseRanking(todayId));
		
		return "dashboad";
	}
		
	//未（Ajaxを使うので最後にやる）・該当年度を選択したら、年月のドロップダウン候補が更新される
	@GetMapping("period")
	public String uodateMonthOption(@ModelAttribute("period") Integer period, Model model) {
		Iterable<MST_month> monthList = dropdownService.updateMonth(period);
		model.addAttribute("month", monthList);
		return "dashboad";		
		
	}
	
	//該当年月を選択した場合
	@PostMapping
	public String showDashboadPost(@RequestParam Integer monthId, @RequestParam Integer selectPeriod, @RequestParam Integer selectMonth ,Model model) {
		
		//年月ドロップダウンリスト
		commonService.selectList(model);
		model.addAttribute("selectPeriod", selectPeriod);
		model.addAttribute("selectMonth", selectMonth);
		
		//該当の予算と実績を呼び出して値を定義(達成率計算で使用するため) 
		TRN_shop_achievement achievement =dashboadService.checkAchievement(monthId);
		TRN_shop_budget budget = dashboadService.checkBudget(monthId);
		model.addAttribute("achievement", achievement);
		model.addAttribute("budget",budget);
		
		//達成率を計算して格納
		model.addAttribute("salesPar", commonService.calcPar(achievement.getShop_achievement_sales(), budget.getShop_budget_sales())); 
		model.addAttribute("profitPar",commonService.calcPar(achievement.getShop_achievement_profit(), budget.getShop_budget_profit())); 
		
		//チーム別一覧を表示
		model.addAttribute("teamAchievementList", dashboadService.checkAchievementEveryTeam(monthId));
		
		//店舗ランキングを表示
		model.addAttribute("trialRankList", dashboadService.checkTrialRanking(monthId));
		model.addAttribute("contractorRankList", dashboadService.checkContractorRanking(monthId));
		model.addAttribute("UserCountRankList", dashboadService.checkUserCountRanking(monthId));
		model.addAttribute("totalUseRankList", dashboadService.checkTotalUseRanking(monthId));
		
		return "dashboad";		
	}
	

}
