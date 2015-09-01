package com.akifbatur.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Akif Batur
 *
 */
@Entity
@Table(name="AUTHOR") //Table name is AUTHOR
public class Author 
{
	@Id
	@Column(name="AUTHOR_ID") //Column name is AUTHOR_ID
	@GeneratedValue(strategy=GenerationType.AUTO) //Value will be generated automatically
	private int authorId;
	
	@Column(name="AUTHOR_NAME") //Column name is AUTHOR_NAME 
	private String authorName;

	public int getAuthorId() 
	{
		return authorId;
	}

	public void setAuthorId(int authorId) 
	{
		this.authorId = authorId;
	}

	public String getAuthorName() 
	{
		return authorName;
	}

	public void setAuthorName(String authorName) 
	{
		this.authorName = authorName;
	}

	@Override
	public String toString()
	{
		return "ID = "+authorId+", Name = "+authorName;
	}
}
