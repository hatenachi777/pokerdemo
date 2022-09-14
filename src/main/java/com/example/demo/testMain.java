package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class testMain {
	
	Logger logger = LoggerFactory.getLogger(testMain.class);
	
	@Autowired	
	testMethod method ;
	@RequestMapping(value = "/test/index", method = RequestMethod.GET)
	public String Main(Model model) {
		/*
		testmthod t = new testmthod();
		int test = t.add();
		*/
		int test = method.add(2,3);
		String msg = "テストで作成したメソッドの答えは…" + test;
		
		logger.info( msg);
		 return "test/index.html";
	}
	
	

}
