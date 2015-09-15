package com.akifbatur.blog.dao;

import java.util.List;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.model.Category;

/**
 * 
 * @author Akif Batur
 *
 */
public interface CategoryDAO 
{
	public int countCategories();

	public List<Category> getCategories();

	public void saveCategory(Category category);
}
