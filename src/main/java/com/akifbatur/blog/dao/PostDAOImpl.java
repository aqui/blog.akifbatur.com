package com.akifbatur.blog.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.model.Post;

/**
 * 
 * @author Akif Batur
 *
 */
@Repository("postDAO")
public class PostDAOImpl implements PostDAO 
{
	private static final Logger logger = LoggerFactory.getLogger(PostDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void savePost(Post post) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(post);
		logger.info("Posted");
	}

	@Override
	public List<Post> fetchAllPost() 
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Post");
		List<Post> posts = query.list();
		return posts;
	}

	@Override
	public Post getPostByTitle(String postTitle) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Post where POST_TITLE = :postTitle");
		query.setString("postTitle", postTitle);
		Post post = (Post) query.list().get(0);
		return post;
	}
}
