package com.hyt.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 測試用controller，可提供Rest Service使用
 * Title: TestController
 * Description: 
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/25
 */
@Controller
@RequestMapping("/")
public class TestController {
	
	  /**
	   * Test request
	   * @param model
	   * @return
	   */
	  @RequestMapping(method = RequestMethod.GET)
	  public String listAll(Model model) {
	    return "home";
	  }
}
