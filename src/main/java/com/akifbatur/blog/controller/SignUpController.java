package com.akifbatur.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.service.AuthorService;

/**
 * 
 * @author Akif Batur 
 *
 */
@Controller("signUpController")
@RequestMapping("/signup")
public class SignUpController 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView signup(Model signupModel, @RequestParam(value = "error", required = false) String error)
	{
		String message = "";
		if(error!=null)
		{
			if (error.equals("name"))
			{
				message = "incorrect name";
			}
			else if(error.equals("userName"))
			{
				message = "incorrect username";
			}
			else if(error.equals("email"))
			{
				message = "incorrect email";
			}
			else if(error.equals("password"))
			{
				message = "incorrect password";
			}
			else if(error.equals("exist"))
			{
				message = "this username is exist";
			}
		}
		signupModel.addAttribute("message", message);
		return new ModelAndView("signup", "signupModel", signupModel);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String saveAuthor(Author author)
	{
		//Author fields and form fields must be matching
		
		//One last check before persist the user
		if(author.getName().equals(""))
		{
			return "redirect:/signup?error=name";
		}
		else if(author.getUserName().equals(""))
		{
			return "redirect:/signup?error=userName";
		}
		else if(author.getEmail().equals(""))
		{
			return "redirect:/signup?error=email";
		}
		else if(author.getPassword().equals(""))
		{
			return "redirect:/signup?error=password";
		}

		try 
		{
			this.authorService.saveAuthor(author);
		}
		catch(Exception ex)
		{
			return "redirect:/signup?error=exist";
		}
		
		return "/login";
	}
}