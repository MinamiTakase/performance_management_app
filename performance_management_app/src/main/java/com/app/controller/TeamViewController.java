package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.domain.TRN_shop_achievement;
import com.app.domain.TRN_shop_budget;
import com.app.domain.TRN_team;
import com.app.service.CommonService;
import com.app.service.DropdownService;
import com.app.service.TeamService;

@Controller
@RequestMapping("team")
public class TeamViewController {
	
	@Autowired DropdownService dropdownService;
	@Autowired TeamService teamService;
	@Autowired CommonService commonService;
	
	
	@GetMapping
	public String showTeamGet(Model model) {
		Integer todayId = dropdownService.getTodayId();
		//50音最初のチームを初期表示とする
		TRN_team topTeam = teamService.findTopTeam();
		Integer topTeamId = topTeam.getTeam_id();
		
		commonService.selectList(model);
		//チームリストを入れる
		model.addAttribute("teamList", commonService.outPutTeamList());
		
		//該当の予算と実績を呼び出して値を定義(達成率計算で使用するため)
		TRN_shop_achievement achievement = teamService.checkAchievementByTeam(todayId, topTeamId);
		TRN_shop_budget budget = teamService.checkBudgetByTeam(todayId, topTeamId);
		model.addAttribute("achievement", achievement);
		model.addAttribute("budget",budget);
		
		//達成率を計算して格納
		model.addAttribute("salesPar", commonService.calcPar(achievement.getShop_achievement_sales(), budget.getShop_budget_sales())); 
		model.addAttribute("profitPar",commonService.calcPar(achievement.getShop_achievement_profit(), budget.getShop_budget_profit())); 
	
		//ログインユーザー名を表示させる
		
		//担当別一覧を表示
		model.addAttribute("repAchievementList", teamService.checkAchievementEveryRep(todayId, topTeamId));
		
		return "team";
	}
	
	@PostMapping
	public String showTeamPost(@RequestParam Integer monthId, @RequestParam Integer teamId, @RequestParam Integer selectPeriod, 
			@RequestParam Integer selectMonth, @RequestParam Integer selectTeam, Model model) {
		//年月リスト
		commonService.selectList(model);
		model.addAttribute("selectPeriod", selectPeriod);
		model.addAttribute("selectMonth", selectMonth);
		//チームリスト
		model.addAttribute("teamList", commonService.outPutTeamList());
		model.addAttribute("selectTeam", selectTeam);
		
		//該当の予算と実績を呼び出して値を定義(達成率計算で使用するため)
		TRN_shop_achievement achievement = teamService.checkAchievementByTeam(monthId, teamId);
		TRN_shop_budget budget = teamService.checkBudgetByTeam(monthId, teamId);
		model.addAttribute("achievement", achievement);
		model.addAttribute("budget",budget);
		
		//達成率を計算して格納
		model.addAttribute("salesPar", commonService.calcPar(achievement.getShop_achievement_sales(), budget.getShop_budget_sales())); 
		model.addAttribute("profitPar",commonService.calcPar(achievement.getShop_achievement_profit(), budget.getShop_budget_profit())); 
	
		//ログインユーザー名を表示させる
		
		//担当別一覧を表示
		model.addAttribute("repAchievementList", teamService.checkAchievementEveryRep(monthId, teamId));
		
		return "team";
	}
	
	
		
}
