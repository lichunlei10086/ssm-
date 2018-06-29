package com.study.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.study.demo.model.User;
import com.study.demo.service.UserService;


@Controller
public class WebController {
	
	@Resource
	private UserService userService;

	
	@RequestMapping(value="/a",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String name(){
		User u = userService.selectById(1L);
		System.out.println("--------"+u.toString());
		return u.toString();
	}
	
	
	@RequestMapping(value="/main")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView("main");
		return mv;
	}
	
}
