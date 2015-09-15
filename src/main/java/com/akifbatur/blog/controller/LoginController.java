package com.akifbatur.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * When there is a request on /login, login.jsp page
 * will be displayed. Form on login page is will be
 * posted to the spring security. That's why userName
 * and password field names on login form must be
 * matching with the spring security fields defined in
 * spring-security.xml
 * 
 * @author Akif Batur
 *
 */
@Controller("loginController")
public class LoginController 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	public ModelAndView getLoginForm(Model loginModel, @RequestParam(value = "error", required = false) String error)
	{
		/**
		 * If userName is not exist or userName and password does not matches
		 * then Spring Security will redirect to index.jsp with "error" named
		 * request parameter. That parameter is defined in the spring-securit.xml
		 * */
		String message = "";
		if (error != null)
		{
			message = "incorrectCredentials";
		}
		loginModel.addAttribute("message",message);
		return new ModelAndView("login", "loginModel", loginModel);
	}
}
