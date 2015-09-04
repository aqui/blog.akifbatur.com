package com.akifbatur.blog.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.akifbatur.blog.model.Author;

/**
 * 
 * @author Akif Batur
 *
 */
@Controller("loginController")
public class LoginController 
{
	@RequestMapping("/login")
	public ModelAndView getLoginForm(@ModelAttribute Author author, @RequestParam(value = "error", required = false) String error)
	{
		String message = "";
		if (error != null)
		{
			message = "incorrect username or password";
		}
		return new ModelAndView("login", "message", message);
	}

	@RequestMapping("/admin**")
	public ModelAndView getAdminProfile() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			username = userDetail.getUsername();
		}
		return new ModelAndView("admin", "username", username);
	}

	@RequestMapping("/author**")
	public ModelAndView getAuthorProfile() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			username = userDetail.getUsername();
		}
		return new ModelAndView("author", "username", username);
	}

	@RequestMapping("/403")
	public ModelAndView getAccessDenied() 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			username = userDetail.getUsername();
		}
		return new ModelAndView("403", "username", username);
	}
}
