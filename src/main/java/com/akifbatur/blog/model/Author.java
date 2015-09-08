package com.akifbatur.blog.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Akif Batur
 *
 */
@Entity
@Table(name="AUTHOR", catalog="BLOG")
public class Author 
{
	private static final Logger logger = LoggerFactory.getLogger(Author.class);
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="USERNAME", unique = true, nullable = false, length = 45)
	private String userName;
	
	@Column(name="PASSWORD", nullable = false, length = 60)
	private String password;
	
	@Column(name="NAME", nullable = false, length = 45)
	private String name;
	
	@Column(name="EMAIL", nullable = false)
	private String email;
	
	@Column(name="ENABLED", nullable = false)
	private boolean enabled;
	
	//Each author has many roles
	@OneToMany(mappedBy="authorId", fetch = FetchType.LAZY)
	private Set<Role> role = new HashSet<Role>(0);
	
	//Each author has many posts
	@OneToMany(mappedBy="authorId", fetch = FetchType.LAZY)
	private Set<Post> post = new HashSet<Post>(0);
	
	//Each author has many categories
	@OneToMany(mappedBy="authorId", fetch = FetchType.LAZY)
	private Set<Category> category = new HashSet<Category>(0);
	
	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNick(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	public Set<Post> getPost() {
		return post;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPost(Set<Post> post) {
		this.post = post;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	@Override
	public String toString()
	{
		return "Author ID = "+id+", USERNAME = "+userName+", NAME = "+name+", ENABLED = "+enabled;
	}
}
