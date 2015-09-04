package com.akifbatur.blog.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Author findByUserName(String userName)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Author where USERNAME = ?");
		query.setString(0, userName);
		Author author = (Author) query.list().get(0);
		return author;
	}
}
