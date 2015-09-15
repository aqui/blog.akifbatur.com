package com.akifbatur.blog.service;

import java.util.List;

import com.akifbatur.blog.model.Category;

public interface CategoryService
{
	public int countCategories();

	public List<Category> getCategories();

	public void saveCategory(Category category);
}
