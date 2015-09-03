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
	
	@Column(name="NICK", unique = true, nullable = false, length = 45)
	private String nick;
	
	@Column(name="PASSWORD", nullable = false, length = 45)
	private String password;
	
	@Column(name="NAME", nullable = false, length = 45)
	private String name;
	
	@Column(name="EMAIL", nullable = false)
	private String email;
	
	@Column(name="STATUS", nullable = false)
	private boolean status = false; // Account disabled default
	
	@OneToMany(mappedBy="authorId", fetch = FetchType.LAZY)
	@Column(name="ROLE", nullable = false)
	private Set<Role> role = new HashSet<Role>(0);

	public int getId() {
		return id;
	}

	public String getNick() {
		return nick;
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

	public boolean isStatus() {
		return status;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNick(String nick) {
		this.nick = nick;
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

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	@Override
	public String toString()
	{
		return "Author ID = "+id+" NICK = "+nick+" NAME = "+name+" STATUS = "+status;
	}
}
