package com.akifbatur.blog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Akif Batur
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name="AUTHOR", catalog="BLOG")
public class Author implements Serializable
{
	public Author()
	{
		
	}
	
	public Author(int id, String userName, String password, String name,
			String email, boolean enabled, Date joinDate, Date loginDate,
			List<Role> roles, Set<Post> post, Set<Category> category) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.email = email;
		this.enabled = enabled;
		this.joinDate = joinDate;
		this.loginDate = loginDate;
		this.roles = roles;
		this.post = post;
		this.category = category;
	}

	private static final long serialVersionUID = 1L;

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
	@ManyToMany
	@JoinTable(name="AUTHOR_ROLE", joinColumns=@JoinColumn(name="AUTHOR_ID"), inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
	private List<Role> roles = new ArrayList<Role>();
	
	//Each author has many posts
	@OneToMany(mappedBy="authorId")
	private Set<Post> post = new HashSet<Post>(0);
	
	//Each author has many categories
	@OneToMany(mappedBy="authorId")
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
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString()
	{
		return String.format("ID = %s, NAME = %s, USERNAME = %s, EMAIL = %s, ENABLED = %s, "
				+ "JOIN_DATE = %s, LOGIN_DATE = %s", id, name, userName, email, enabled, joinDate, loginDate);
	}
}
