package com.akifbatur.blog.dao;

import java.util.Date;

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
		//Set author role as ROLE_USER by default
		Role role = new Role();
		role.setRole("ROLE_USER");
		role.setAuthorId(author);
		author.getRole().add(role);
		author.setJoinDate(new Date());
		author.setLoginDate(new Date());
		session.persist(author);
		logger.info("Author saved successfully. Author details: " + author);
	}
}
