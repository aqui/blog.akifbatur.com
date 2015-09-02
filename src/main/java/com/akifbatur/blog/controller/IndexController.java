package com.akifbatur.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.service.AuthorService;

/**
 * When there is a request to the "/" it will be redirected
 * to the /WEB-INF/view/index.jsp by the DispatcherServlet
 * 
 * @author Akif Batur 
 *
 */
@Controller
@RequestMapping("/")
public class IndexController 
{
	@Autowired
	private AuthorService authorService;

	public void setAuthorService(AuthorService authorService)
	{
		this.authorService = authorService;
	}

	public AuthorService getAuthorService() 
	{
		return authorService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAuthor(Model model) 
	{
		Author author = null;
		try 
		{
			author = this.authorService.getAuthorById(1);
		} 
		catch (Exception e) 
		{

		}
		model.addAttribute("author", author);
		return "index";
	}
}