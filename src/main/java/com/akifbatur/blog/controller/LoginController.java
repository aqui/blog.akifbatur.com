package com.akifbatur.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Akif Batur
 *
 */
@Controller("loginController")
public class LoginController 
{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	public ModelAndView getLoginForm(Model loginModel, @RequestParam(value = "error", required = false) String error)
	{
		String message = "";
		if (error != null)
		{
			message = "incorrect username or password";
		}
		loginModel.addAttribute("message",message);
		return new ModelAndView("login", "loginModel", loginModel);
	}
}
