package com.akifbatur.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAuthor(Model indexModel)
	{
		return "index";
	}
}