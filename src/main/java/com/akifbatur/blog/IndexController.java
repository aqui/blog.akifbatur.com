package com.akifbatur.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.akifbatur.blog.service.AuthorService;

/**
 * @author Akif Batur
 * When there is a request to the "/" it will be
 * redirected to the /WEB-INF/view/index.xhtml 
 * by the DispatcherServlet
 */
@Controller
public class IndexController 
{	
	@Autowired
	private AuthorService authorService;
	
	public void setPersonService(AuthorService authorService)
	{
		this.authorService = authorService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAuthor(Model model) {
		model.addAttribute("author", this.authorService.getAuthorById(1));
		return "index";
	}
}