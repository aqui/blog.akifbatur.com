package com.akifbatur.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Akif Batur
 * 
 */
@Entity
@Table(name="AUTHOR") //Table name is AUTHOR
public class Author 
{
	@Id
	@Column(name="AUTHOR_ID") //Column name is AUTHOR_ID
	private int id;
	
	@Column(name="AUTHOR_NAME") //Column name is AUTHOR_NAME 
	private String authorName;
	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getAuthorName() 
	{
		return authorName;
	}

	public void setAuthorName(String authorName) 
	{
		this.authorName = authorName;
	}
}
