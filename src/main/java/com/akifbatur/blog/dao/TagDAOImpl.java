package com.akifbatur.blog.dao;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.model.Role;
import com.akifbatur.blog.model.Tag;

/**
 * 
 * @author Akif Batur
 *
 */
@Repository("tagDAO")
public class TagDAOImpl implements TagDAO 
{
	private static final Logger logger = LoggerFactory.getLogger(TagDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveTag(Tag tag) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(tag);
		logger.info("Tag saved");
	}

	@Override
	public Tag checkTag(String tagText) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Tag where TAG_TEXT = :tagText");
		query.setString("tagText", tagText);
		if(!query.list().isEmpty())
		{
			Tag tag = (Tag) query.list().get(0);
			return tag;
		}
		else
		{
			return null;
		}
	}
}
