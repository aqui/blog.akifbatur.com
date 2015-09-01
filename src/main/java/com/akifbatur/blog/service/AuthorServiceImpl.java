package com.akifbatur.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akifbatur.blog.dao.AuthorDAO;
import com.akifbatur.blog.model.Author;

/**
 * 
 * @author Akif Batur
 *
 */
@Service
public class AuthorServiceImpl implements AuthorService 
{
	@Autowired
	private AuthorDAO authorDAO;

	public void setAuthorDAO(AuthorDAO authorDAO) 
	{
		this.authorDAO = authorDAO;
	}

	@Override
	@Transactional
	public Author getAuthorById(int id) 
	{
		return this.authorDAO.getAuthorById(id);
	}
}
