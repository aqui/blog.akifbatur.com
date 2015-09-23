package com.akifbatur.blog.controller;

import java.util.Date;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.model.Category;
import com.akifbatur.blog.service.AuthorService;
import com.akifbatur.blog.service.CategoryService;

/**
 * 
 * @author Akif Batur
 *
 */
@Controller("writeCategoryController")
public class WriteCategoryController 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(WriteCategoryController.class);

	@Autowired
	CategoryService categoryService;

	@Autowired
	AuthorService authorService;
		
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value="/category/write", method = RequestMethod.GET)
	public ModelAndView createForm(Model categoryModel)
	{
		categoryModel.addAttribute("category", new Category());
		return new ModelAndView("category", "categoryModel", categoryModel);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value="/category/write", method = RequestMethod.POST)
	public ModelAndView saveCategory(Model saveCategoryModel, @ModelAttribute("category") @Valid Category category, BindingResult result) 
	{
		if (result.hasErrors()) 
		{
			return new ModelAndView("category", "saveCategoryModel", saveCategoryModel);
		}
		try
		{
			category.setCategoryTitle(category.getCategoryTitle().trim());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String userName = userDetails.getUsername();
			Author author = authorService.getAuthorByUserName(userName);
			category.setAuthorId(author);
			category.setCategoryDate(new Date());
			category.setCategoryEditDate(new Date());
			this.categoryService.saveCategory(category);
		} 
		catch (Exception e)
		{
			saveCategoryModel.addAttribute("message", new String("this category is exist"));
			return new ModelAndView("category", "saveCategoryModel", saveCategoryModel);
		}
		return new ModelAndView("index", "saveCategoryModel", saveCategoryModel);
	}
}