package com.akifbatur.blog.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Akif Batur
 * 
 */
@Entity
public class Tag 
{
	@Id
	private int id;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}
}
