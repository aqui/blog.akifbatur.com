package com.akifbatur.blog.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Akif Batur
 * 
 */
@Repository
public class AuthorDao
{
	@Autowired
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
