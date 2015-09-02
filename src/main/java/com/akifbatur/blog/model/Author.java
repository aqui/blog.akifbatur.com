package com.akifbatur.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Akif Batur
 *
 */
@Entity
@Table(name="AUTHOR")
public class Author 
{
	@Id
	@Column(name="AUTHOR_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int authorId;
	
	@Column(name="AUTHOR_NAME")
	private String authorName;
	
	@Column(name="AUTHOR_PASSWD")
	private String authorPassword;
	
	@Column(name="AUTHOR_EMAIL")
	private String authorEmail;
	
	@Column(name="AUTHOR_STATUS")
	private boolean authorStatus; // Account enabled or disabled
	
	public int getAuthorId() 
	{
		return authorId;
	}

	public void setAuthorId(int authorId) 
	{
		this.authorId = authorId;
	}

	public String getAuthorName() 
	{
		return authorName;
	}

	public void setAuthorName(String authorName) 
	{
		this.authorName = authorName;
	}

	@Override
	public String toString()
	{
		return "ID = "+authorId+", Name = "+authorName;
	}

	public String getAuthorPassword() {
		return authorPassword;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public boolean isAuthorStatus() {
		return authorStatus;
	}

	public void setAuthorPassword(String authorPassword) {
		this.authorPassword = authorPassword;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public void setAuthorStatus(boolean authorStatus) {
		this.authorStatus = authorStatus;
	}
}
