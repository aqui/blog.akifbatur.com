package com.akifbatur.blog.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
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
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Author.class);
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//Provided by the user at signup
	@NotNull
	@Size(max=20,min=3)
	@Column(name="USERNAME", unique = true, nullable = false)
	private String userName;
	
	//Provided by the user at signup
	@NotNull
	@Size(max=240,min=8)
	@Column(name="PASSWORD", nullable = false)
	private String password;
	
	//Provided by the user at signup
	@NotNull
	@Size(max=20,min=3)
	@Column(name="NAME", nullable = false)
	private String name;
	
	//Provided by the user at signup
	@NotNull
	@Size(max=240,min=5)
	@Email
	@Column(name="EMAIL", nullable = false)
	private String email;
	
	@Column(name="ENABLED", nullable = false)
	private boolean enabled = true;
	
	@Column(name="JOIN_DATE", nullable = false)
	private Date joinDate;
	
	@Column(name="LOGIN_DATE", nullable = false)
	private Date loginDate;
	
	//Each author has many roles
	@OneToMany(mappedBy="authorId", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Role> role = new HashSet<Role>(0);
	
	//Each author has many posts
	@OneToMany(mappedBy="authorId", fetch = FetchType.EAGER)
	private Set<Post> post = new HashSet<Post>(0);
	
	//Each author has many categories
	@OneToMany(mappedBy="authorId", fetch = FetchType.EAGER)
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

	public Date getJoinDate() {
		return joinDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Override
	public String toString()
	{
		return String.format("ID = %s, NAME = %s, USERNAME = %s, EMAIL = %s, ENABLED = %s, "
				+ "JOIN_DATE = %s, LOGIN_DATE = %s", id, name, userName, email, enabled, joinDate, loginDate);
	}
}
