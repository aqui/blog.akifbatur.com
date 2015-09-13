package com.akifbatur.blog.dao;

import com.akifbatur.blog.model.Author;

/**
 * 
 * @author Akif Batur
 *
 */
public interface AuthorDAO 
{
	public Author getAuthorById(int authorId);

	public void saveAuthor(Author author);

	public Author getAuthorByUserName(String userName);
}
