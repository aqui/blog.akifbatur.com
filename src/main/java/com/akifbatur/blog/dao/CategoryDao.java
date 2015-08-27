package com.akifbatur.blog.dao;

import org.hibernate.SessionFactory;

/**
 * @author Akif Batur
 * 
 */
public class CategoryDao
{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
}
