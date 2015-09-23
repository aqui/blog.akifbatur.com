package com.akifbatur.blog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Akif Batur
 *
 */
@Entity
@Table(name="POST", catalog="BLOG")
public class Post implements Serializable
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Post.class);
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//Provided by the user
	@NotNull
	@Size(min=1, max=255, message="can not be empty")
	@Column(name="POST_TITLE", unique = true, nullable = false)
	private String postTitle;
	
	//Provided by the user
	@Lob
	@NotNull
	@Size(min=1, message="can not be empty")
	@Column(name="POST_BODY", nullable = false)
	private String postBody;
	
	@Column(name="POST_DATE", nullable = false)
	private Date postDate;
	
	@Column(name="POST_EDIT_DATE", nullable = false)
	private Date postEditDate;
	
	//Each post has an author
	@ManyToOne
	@JoinColumn(name="AUTHOR_ID", nullable = false)
	private Author authorId;
	
	//Each post has a category
	//Choosen by the user
	@ManyToOne
	@NotNull(message="can not be empty")
	@JoinColumn(name="CATEGORY_ID", nullable = false)
	private Category categoryId;
	
	//Each post has many tags
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="TAG_POST", joinColumns=@JoinColumn(name="POST_ID"),inverseJoinColumns=@JoinColumn(name="TAG_ID"))
	private List<Tag> tags = new ArrayList<Tag>();
	
	public Post()
	{
		
	}
	
	public Post(int id, String postTitle, String postBody, Date postDate,
			Date postEditDate, Author authorId, Category categoryId,
			List<Tag> tags) {
		super();
		this.id = id;
		this.postTitle = postTitle;
		this.postBody = postBody;
		this.postDate = postDate;
		this.postEditDate = postEditDate;
		this.authorId = authorId;
		this.categoryId = categoryId;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public Author getAuthorId() {
		return authorId;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAuthorId(Author authorId) {
		this.authorId = authorId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public String getPostBody() {
		return postBody;
	}

	public Date getPostDate() {
		return postDate;
	}

	public Date getPostEditDate() {
		return postEditDate;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public void setPostEditDate(Date postEditDate) {
		this.postEditDate = postEditDate;
	}
}
