package com.akifbatur.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Akif Batur
 *
 */
@Controller("userController")
public class UserController 
{
	@SuppressWarnings("unused")
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
