package com.akifbatur.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Akif Batur
 *
 */
@Entity
@Table(name="ROLE", catalog="BLOG")
public class Role 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Author.class);
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="ROLE", nullable = false, length=45)
	private String role;
	
	//Each role has an author
	@ManyToOne
	@JoinColumn(name="AUTHOR_ID", nullable = false)
	private Author authorId;

	public int getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public Author getAuthorId() {
		return authorId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setAuthorId(Author authorId) {
		this.authorId = authorId;
	}
}
