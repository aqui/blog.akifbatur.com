package com.akifbatur.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.service.AuthorService;

/**
 * When there is a request to the "/" it will be redirected
 * to the /WEB-INF/view/index.jsp by the DispatcherServlet
 * 
 * @author Akif Batur 
 *
 */
@Controller("indexController")
public class IndexController 
{
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private AuthorService authorService;

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView getAuthor(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			username = userDetail.getUsername();
		}
		return new ModelAndView("index", "username", username);
	}
}