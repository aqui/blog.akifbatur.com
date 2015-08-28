package com.akifbatur.blog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akifbatur.blog.model.Author;

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
	public Author getAuthorName(int authorId)
	{
		Session session = getSessionFactory().openSession();
		Author author = null;
		try 
		{			
			session.beginTransaction();
			author = (Author) session.get(Author.class, authorId);
			session.getTransaction().commit();
		}
		catch(Exception ex)
		{
			session.getTransaction().rollback();
		}
		
		return author;
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
