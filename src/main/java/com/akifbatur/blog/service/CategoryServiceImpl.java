package com.akifbatur.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akifbatur.blog.dao.CategoryDAO;
import com.akifbatur.blog.model.Category;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	CategoryDAO categoryDAO;
	
	@Override
	@Transactional
	public int countCategories() 
	{
		return this.categoryDAO.countCategories();
	}

	@Override
	@Transactional
	public List<Category> getCategories() 
	{
		return categoryDAO.getCategories();
	}

	@Override
	@Transactional
	public void saveCategory(Category category) 
	{
		this.categoryDAO.saveCategory(category);
	}

	@Override
	@Transactional
	public Category getCategoryByTitle(String categoryTitle) {
		return this.categoryDAO.getCategoryByTitle(categoryTitle);
	}	
}
