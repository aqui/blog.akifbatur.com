package com.akifbatur.blog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akifbatur.blog.model.Author;

/**
 * 
 * @author Akif Batur
 *
 */
@Repository
public class AuthorDAOImpl implements AuthorDAO 
{	
	private static final Logger logger = LoggerFactory.getLogger(AuthorDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public Author getAuthorById(int authorId) 
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
		finally
		{
			session.close();
		}
		logger.info("Author loaded successfully. Author details = "+author);
		return author;
	}
}
