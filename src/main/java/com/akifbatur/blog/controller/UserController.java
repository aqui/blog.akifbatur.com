package com.akifbatur.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Controller("userController")
public class UserController 
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/admin**")
	public String getAdminProfile() 
	{
		return "admin";
	}

	@RequestMapping("/author**")
	public String getAuthorProfile() 
	{
		return "author";
	}

	@RequestMapping("/403")
	public String getAccessDenied() 
	{
		return "403";
	}
}
