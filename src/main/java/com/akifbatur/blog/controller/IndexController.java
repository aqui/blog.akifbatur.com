package com.akifbatur.blog.controller;
/**
 * @author Akif Batur
 * 
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * When there is a request to the "/" it will be
 * redirected to the /WEB-INF/view/index.xhtml 
 * by the DispatcherServlet
 * */

@Controller
@RequestMapping("/")
public class IndexController 
{
	@RequestMapping(method=RequestMethod.GET)
	public String showIndex(ModelMap model)
	{
		return "index";
	}
}