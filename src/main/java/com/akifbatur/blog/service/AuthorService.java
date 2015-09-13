package com.akifbatur.blog.service;


import com.akifbatur.blog.model.Author;

/**
 * 
 * @author Akif Batur
 *
 */
public interface AuthorService 
{
	public Author getAuthorById(int authorId);
	
	public void saveAuthor(Author author);
	
	public Author getAuthorByUserName(String userName);
}
