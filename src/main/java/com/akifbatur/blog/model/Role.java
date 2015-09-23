package com.akifbatur.blog.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
public class Role implements Serializable
{
	public Role()
	{
		
	}
	
	public Role(int id, String role, List<Author> authors) {
		super();
		this.id = id;
		this.role = role;
		this.authors = authors;
	}

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Author.class);
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="ROLE", nullable = false, length=45)
	private String role = "ROLE_USER"; //New user role by default
	
	//Each role has many authors
	@ManyToMany(mappedBy="roles", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Author> authors;

	public int getId() {
		return id;
	}

	public String getRole()  {
		return role;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
}
