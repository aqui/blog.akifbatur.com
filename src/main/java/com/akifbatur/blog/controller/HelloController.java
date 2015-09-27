package com.akifbatur.blog.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.akifbatur.blog.model.Category;
import com.akifbatur.blog.service.CategoryService;

@ManagedBean(name="helloController")
public class HelloController 
{
	@ManagedProperty("#{categoryService}")
	private CategoryService categorService;
	
	public String showHello()
	{
		List<Category> categories = this.categorService.getCategories();
		return categories.get(0).getCategoryTitle();
	}

	public CategoryService getCategorService() {
		return categorService;
	}

	public void setCategorService(CategoryService categorService) {
		this.categorService = categorService;
	}
	
	
}
