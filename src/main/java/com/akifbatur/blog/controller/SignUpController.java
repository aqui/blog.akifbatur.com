package com.akifbatur.blog.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.service.AuthorService;

/**
 * When there is a request on /signup, signup.jsp page
 * will be displayed. POST and GET requests will be
 * handled in this controller.
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
	public ModelAndView signup(Model signupModel)
	{
		/**
		 * If it's first time that user open the signup page
		 * then there should be no errors displayed. That's why we create
		 * a new Author object.
		 * */
		signupModel.addAttribute("author",new Author());
		return new ModelAndView("signup", "signupModel", signupModel);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveAuthor(Model saveAuthorModel, @ModelAttribute("author") @Valid Author author, BindingResult result)
	{
		/**
		 * Author fields and form fields must be matching.
		 * Errors on posted data or persisting
		 * exceptions will be handled in here.
		 * */
		
		if(result.hasErrors())
		{
			//If there is an error on any form field return to signup page
			return new ModelAndView("signup", "signupModel", saveAuthorModel);
		}
		
		try 
		{
			//Try to save the author
			this.authorService.saveAuthor(author);
		}
		catch(Exception ex)
		{
			//If there is an error return to signup page
			return new ModelAndView("signup", "signupModel", saveAuthorModel);
		}
		
		//If there is no error return to login page
		return new ModelAndView("login", "signupModel", saveAuthorModel);
	}
}