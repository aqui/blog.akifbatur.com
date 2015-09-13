package com.akifbatur.blog.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.model.Category;
import com.akifbatur.blog.model.Role;

/**
 * 
 * @author Akif Batur
 *
 */
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO 
{
	private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int countCategories() 
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Category");
		return query.list().size();
	}

	@Override
	public List<Category> getCategories() 
	{		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Category");
		return query.list();
	}
}
