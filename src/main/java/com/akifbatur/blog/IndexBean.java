package com.akifbatur.blog;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.akifbatur.blog.model.Author;

/**
 * @author Akif Batur
 * 
 */
@SessionScoped
@ManagedBean(name = "indexBean")
public class IndexBean implements Serializable
{		
	private static final long serialVersionUID = 1L;
	
	private Author author;
	
	public Author getAuthor() 
	{
		return author;
	}
	
	public void setAuthor(Author author) 
	{
		this.author = author;
	}

	public String printPrompt(Author author)
	{
		return "aqui@aqui~$ "+getAuthor().getAuthorName();
	}
}
