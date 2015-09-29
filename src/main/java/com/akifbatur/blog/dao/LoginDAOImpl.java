package com.akifbatur.blog.dao;

import java.util.Date;

import org.hibernate.Query;
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
@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AuthorDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Author findByUserName(String userName)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Author where USERNAME = :userName");
		query.setString("userName", userName);
		Author author = (Author) query.list().get(0);
		return author;
	}
}
