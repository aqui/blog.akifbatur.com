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
import com.akifbatur.blog.model.Category;
import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.model.Tag;

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
		System.out.println(post.getPostBody());
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(post);
		logger.info("Posted");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Post> fetchAllPost() 
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Post post order by post.postDate desc");
		List<Post> posts = query.list();
		return posts;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Post> getPostsByTitle(String postTitle) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Post where POST_TITLE = :postTitle");
		query.setString("postTitle", postTitle);
		List<Post> posts = query.list();
		return posts;
	}

	@Override
	public List<Post> getPostsByUserName(String userName) 
	{
		Query query = null;
		Session session = this.sessionFactory.getCurrentSession();
		query = session.createQuery("from Author where USERNAME = :userName");
		query.setString("userName", userName);
		Author author = (Author) query.list().get(0);
		List<Post> posts = new ArrayList<Post>();
		posts.addAll(author.getPost());
		return posts;
	}

	@Override
	public List<Post> getPostsByTag(String tagText) 
	{
		Query query = null;
		Session session = this.sessionFactory.getCurrentSession();
		query = session.createQuery("from Tag where TAG_TEXT = :tagText");
		query.setString("tagText", tagText);
		Tag tag = (Tag) query.list().get(0);
		List<Post> posts = new ArrayList<Post>();
		posts.addAll(tag.getPosts());
		return posts;
	}

	@Override
	public List<Post> getPostsByCategory(String categoryTitle) 
	{
		Query query = null;
		Session session = this.sessionFactory.getCurrentSession();
		query = session.createQuery("from Category where CATEGORY_TITLE = :categoryTitle");
		query.setString("categoryTitle", categoryTitle);
		Category category = (Category) query.list().get(0);
		List<Post> posts = new ArrayList<Post>();
		posts.addAll(category.getPost());
		return posts;
	}

	@Override
	public void deletePost(Post post) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(post);
	}

	@Override
	public Post getPostById(int id) 
	{
		Query query = null;
		Session session = this.sessionFactory.getCurrentSession();
		query = session.createQuery("from Post where ID = :id");
		query.setInteger("id", id);
		Post post = (Post) query.list().get(0);
		return post;
	}

	@Override
	public void updatePost(Post post) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		session.update(post);
		System.out.println("Post is updated");
	}
}
