package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AccountInfo;
import com.example.demo.entity.SimpleResult;
import com.example.demo.logic.Judge;
import com.example.demo.logic.PokerLogic;

@Controller
public class PokerAccountController {

	Logger logger = LoggerFactory.getLogger(PokerAccountController.class);
	@Autowired AccountInfo accountInfo;
	@Autowired SimpleResult simpleResult;


	@RequestMapping("/")
	public String Start() {
		return "index.html";
	}
	
	@RequestMapping("/newAccount")
	public String newAccount() {
		return "newAccount.html";
	}
	
	@RequestMapping("/login")
	public String loginStart() {
		return "login.html";
	}

	
	@PostMapping("/loginOK")
	public String loginSearch(@RequestParam("name") String name, @RequestParam("password") String password, Model model) {
		accountInfo.setId(0);
		accountInfo.setName(name);
		accountInfo.setPassword(password);
		accountInfo.setCoin(1000);

		logger.info( name);
		logger.info( password);
		model.addAttribute(accountInfo);
		return "loginOK.html";
	}



	@GetMapping("/table")
	public String pokerPlay(Model model) {

		PokerLogic pokerlogic = new PokerLogic();
		pokerlogic.play(this.simpleResult);
		//ＢＥＴ用のlistを作成
		List<Integer> coins = new ArrayList<Integer>();
		int range = accountInfo.getCoin()/100;
		
		for (int i = 1 ; i < range+1 ; i++) {
			coins.add(i*100);		}

		logger.info( simpleResult.getPlayerRole());
		logger.info( simpleResult.getDealerRole());
		
		model.addAttribute("coins" ,coins);
		model.addAttribute(simpleResult);
		model.addAttribute(accountInfo);

		return "table.html";
	}

	@PostMapping("/result")
	public String pokerResult(@RequestParam("betCoin") String betCoin, Model model) {

		logger.info( accountInfo.getName());
		
		int bet = Integer.parseInt(betCoin);
		int payCoin = accountInfo.getCoin() - bet;
		accountInfo.setCoin(payCoin);
		
		// ベット処理の分岐 
		Judge judge = new Judge();
		boolean res = judge.judgeGame(simpleResult.getPlayerRole(), simpleResult.getDealerRole());
			
		if(res) {//勝った場合
			payCoin = accountInfo.getCoin() + (bet * 2) ;
			accountInfo.setCoin(payCoin);
		}else {//負けた場合
			
		}
		
		model.addAttribute(simpleResult);
		model.addAttribute(accountInfo);
		logger.info( simpleResult.getPlayerRole());
		logger.info( simpleResult.getDealerRole());

		return "result.html";
	}

}
