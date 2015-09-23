package com.akifbatur.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service("authorService")
public class AuthorServiceImpl implements AuthorService
{
	private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	@Autowired
	private AuthorDAO authorDAO;

	@Override
	@Transactional
	public Author getAuthorById(int authorId) 
	{
		Author author = null;
		try 
		{
			author = this.authorDAO.getAuthorById(authorId);
		} 
		catch (Exception e) 
		{
			logger.error(e.toString());
		}
		return author;
	}

	@Override
	@Transactional
	public void saveAuthor(Author author)
	{
		try 
		{
			this.authorDAO.saveAuthor(author);
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

	@Override
	@Transactional
	public Author getAuthorByUserName(String userName) 
	{	
		Author author = null;
		try 
		{
			author = this.authorDAO.getAuthorByUserName(userName);
		} 
		catch (Exception e) 
		{
			logger.error(e.toString());
		}
		return author;
	}
}
