package com.app.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.TRN_rep;
import com.app.domain.TRN_rep_assigning;
import com.app.domain.TRN_shop;
import com.app.mapper.RepAssigningMapper;
import com.app.mapper.RepMapper;
import com.app.mapper.ShopMapper;
import com.app.mapper.TeamMapper;

@Service
public class MasterService {
	
	@Autowired RepMapper repMapper;
	@Autowired TeamMapper teamMapper;
	@Autowired ShopMapper shopMapper;
	@Autowired RepAssigningMapper repAssigningMapper;
	@Autowired CommonService commonService;
		
		
	//店舗一覧を引っ張る
	public Collection<TRN_shop> findshopList() {
		return shopMapper.findAll();
	}
	
	//該当店舗の編集画面のため、店舗情報を引っ張る
	public TRN_shop findShop(Integer id) {
		return shopMapper.findOne(id);
	}

	//担当リスト
	public Collection<TRN_rep> findRepList(){
		return repMapper.findAll();
	}
	
	//店舗新規登録
	public void shopInsert(TRN_shop shop) {
		shopMapper.save(shop);
	}
	
	//店舗更新登録
	public void shopUpdate(TRN_shop shop) {
		shopMapper.update(shop);
	}
	
	//店舗担当登録（最新の担当者とイコールならば何もしない、異なる場合はinsert処理を行う）
	public void repUpdate(TRN_shop shop, Integer start_month_id) {
		Integer RepId = shop.getRep_id();
		Integer shopId = shop.getShop_id();
		TRN_rep_assigning currentRepAssigning =  repAssigningMapper.findNewOne(shopId);
		
		if(RepId != currentRepAssigning.getRep_id()) {
			
			TRN_rep_assigning rep_assigning = new TRN_rep_assigning();
			rep_assigning.setRep_id(RepId);
			rep_assigning.setShop_id(shopId);
			rep_assigning.setStart_month_id(start_month_id);
			rep_assigning.setRep_assigning_status("使用中");
		
			repAssigningMapper.save(rep_assigning);	
		}		
	}
	
	//店舗担当登録（新規）
	public void repInsert(TRN_rep_assigning rep_assigning) {
		repAssigningMapper.save(rep_assigning);
	}
	
	//店舗ID自動採番
	private Integer shopIdMake(TRN_shop shop) {
		LocalDate open_date = shop.getOpen_date();
		Integer year = open_date.getYear() % 100;
		
		//この年の最新の店の順番をSQLで探してきてもらう必要がある。仮で３を入れてある。
		Integer shopOrder = 3;
		
		return year*100 + shopOrder;
	}
}
