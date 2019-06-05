package com.ility.customconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ility.customconfig.services.JspService;

@RestController
public class JSPController {

	@Autowired
	private JspService jspService;
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView=new ModelAndView("index");
		return modelAndView;
	}
	
	@PostMapping("/userlogin")
	public ModelAndView login(@RequestParam("username")String username,@RequestParam("password")String password) {
		
		jspService.getAccessToken(username, password);
		System.out.print(username);
		return null;
	}
}
