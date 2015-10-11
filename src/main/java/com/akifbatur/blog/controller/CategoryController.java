package com.akifbatur.blog.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.model.Category;
import com.akifbatur.blog.service.AuthorService;
import com.akifbatur.blog.service.CategoryService;
import com.akifbatur.blog.service.PostService;

@ManagedBean(name="categoryController")
public class CategoryController implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@ManagedProperty("#{categoryService}")
	private CategoryService categoryService;
	
	@ManagedProperty("#{authorService}")
	private AuthorService authorService;
	
	private String categoryTitle;
	
	public void addCategory() 
	{
		try 
		{
			Category category = new Category();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String userName = userDetails.getUsername();
			Author author = authorService.getAuthorByUserName(userName);
			category.setAuthor(author);
			category.setCategoryTitle(categoryTitle);
			category.setCategoryDate(new Date());
			category.setCategoryEditDate(new Date());
			categoryService.saveCategory(category);
			FacesContext context = FacesContext.getCurrentInstance(); 
	    	FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category added.", null);
	    	context.addMessage(null, facesMessage);
		} 
		catch (Exception e) 
		{
			FacesContext context = FacesContext.getCurrentInstance(); 
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category not added.", null);
	    	context.addMessage(null, facesMessage);
		}
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public AuthorService getAuthorService() {
		return authorService;
	}

	public void setAuthorService(AuthorService authorService) {
		this.authorService = authorService;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
}
