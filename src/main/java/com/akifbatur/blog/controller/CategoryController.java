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
import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.service.CategoryService;

/**
 * 
 * @author Akif Batur 
 *
 */
@Controller("categoryController")
@RequestMapping("/category")
public class CategoryController 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView post(Model categoryModel)
	{		
		return new ModelAndView("category", "categoryModel", categoryModel);
	}
}