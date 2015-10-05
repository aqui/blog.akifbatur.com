package com.akifbatur.blog.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akifbatur.blog.model.Category;
import com.akifbatur.blog.model.Tag;

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
	@SuppressWarnings("unchecked")
	public List<Category> getCategories() 
	{		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Category");
		return query.list();
	}

	@Override
	public void saveCategory(Category category) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(category);
		logger.info("Category saved");
	}

	@Override
	public Category getCategoryByTitle(String categoryTitle) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Category where CATEGORY_TITLE = :categoryTitle");
		query.setString("categoryTitle", categoryTitle);
		if(!query.list().isEmpty())
		{
			Category category = (Category) query.list().get(0);
			return category;
		}
		else
		{
			return null;
		}
	}
}
