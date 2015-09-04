package com.akifbatur.blog.dao;

import com.akifbatur.blog.model.Author;

public interface LoginDAO 
{  
	public Author findByUserName(String userName);
}  