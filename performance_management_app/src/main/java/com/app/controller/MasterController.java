package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.domain.TRN_rep_assigning;
import com.app.domain.TRN_shop;
import com.app.service.CommonService;
import com.app.service.MasterService;

@Controller
@RequestMapping("master")
public class MasterController {
	
	@Autowired CommonService commonService;
	@Autowired MasterService masterService;
	
	@GetMapping("shop")
	public String showShopMasterGet(Model model) {	
		model.addAttribute("shopList", masterService.findshopList());
		return "master";
	}
		
	@GetMapping("shop/{id}")
	public String showShopUpdateGet(@PathVariable Integer id, @ModelAttribute TRN_shop shop, Model model) {
		
		model.addAttribute("category", "update");
		model.addAttribute("shop", masterService.findShop(id));
		model.addAttribute("repList", masterService.findRepList());
		commonService.selectMonthListAll(model);
		
		return "shopentry";
	}
	
	@GetMapping("shop/new")
	public String showShopInsertGet(@ModelAttribute("shop") TRN_shop shop, Model model) {
		
		commonService.selectMonthListAll(model);
		model.addAttribute("category", "new");		
		model.addAttribute("repList", masterService.findRepList());
		return "shopentry";
	}
	
	@PostMapping("shop/new")
	public String shopInsert(@ModelAttribute("shop") TRN_shop shop, @RequestParam Integer startMonthId, @RequestParam Integer repId, Model model) {

		masterService.shopInsert(shop);
		
		//担当リストを作成する必要がある
		TRN_rep_assigning rep_assigning = new TRN_rep_assigning();
		rep_assigning.setRep_id(repId);
		rep_assigning.setStart_month_id(startMonthId);
		//ここは自動で採番させる
		rep_assigning.setShop_id(shop.getShop_id());
		rep_assigning.setRep_assigning_status("使用中");
		masterService.repInsert(rep_assigning);
		
		return "redirect:/master/shop";
	}
	
	
	@PostMapping("shop/update")
	public String shopUpdate(@ModelAttribute("shop") TRN_shop shop, @RequestParam Integer startMonthId, Model model) {
		masterService.shopUpdate(shop);

		masterService.repUpdate(shop, startMonthId);
		
		return "redirect:/master/shop";
	}
	

}
