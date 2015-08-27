package com.akifbatur.blog.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * @author Akif Batur
 * 
 */
public class AuthorDao
{
	private SessionFactory sessionFactory;

	//An example method
	public int getAuthorName(int id)
	{
		String hql = "select id from Author where id = "+id;
		Query query = getSessionFactory().openSession().createQuery(hql);
		return (int)query.uniqueResult();
	}

	public SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
}
