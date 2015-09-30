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
import com.akifbatur.blog.model.Role;

/**
 * 
 * @author Akif Batur
 *
 */
@Repository("authorDAO")
public class AuthorDAOImpl implements AuthorDAO 
{
	private static final Logger logger = LoggerFactory.getLogger(AuthorDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Author getAuthorById(int authorId) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		Author author = (Author) session.load(Author.class, new Integer(authorId));
		logger.info("Author loaded successfully. Author details: " + author);
		return author;
	}

	@Override
	public void saveAuthor(Author author) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		//BCrypt password
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		author.setPassword(encoder.encode(author.getPassword()));
		/*
		 * To persist a new author there should be predefined roles in the ROLE table.
		 * New authors will have ROLE_USER by default.
		 * */
		Query query = session.createQuery("from Role where ROLE = :role");
		//Set author role as ROLE_USER by default
		query.setString("role", "ROLE_USER");
		Role role = (Role) query.list().get(0);
		role.getAuthors().add(author);
		author.getRoles().add(role);
		author.setJoinDate(new Date());
		author.setLoginDate(new Date());
		session.persist(author);
		logger.info("Author saved successfully. Author details: " + author);
	}
	
	@Override
	public Author getAuthorByUserName(String userName) 
	{
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Author where USERNAME = :userName");
		query.setString("userName", userName);
		Author author = (Author) query.list().get(0);
		return author;
	}
}
