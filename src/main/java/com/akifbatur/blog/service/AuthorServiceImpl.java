package com.akifbatur.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class AuthorServiceImpl implements AuthorService, UserDetailsService
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
		Author author = this.authorDAO.getAuthorById(id);
		return author;
	}

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
