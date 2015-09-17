package com.akifbatur.blog.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="CATEGORY", catalog="BLOG")
public class Category 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Category.class);
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//Provided by the user
	@NotNull
	@Size(min=1,max=255)
	@Column(name="CATEGORY_TITLE", unique = true, nullable = false)
	private String categoryTitle;
	
	@Column(name="CATEGORY_DATE", nullable = false)
	private Date categoryDate;
	
	@Column(name="CATEGORY_EDIT_DATE", nullable = false)
	private Date categoryEditDate;
	
	//Each category has an author
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="AUTHOR_ID", nullable = false)
	private Author authorId;
	
	//Each category has many posts
	@OneToMany(mappedBy="categoryId")
	private Set<Post> post = new HashSet<Post>(0);

	public int getId() {
		return id;
	}

	public Author getAuthorId() {
		return authorId;
	}

	public Set<Post> getPost() {
		return post;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAuthorId(Author authorId) {
		this.authorId = authorId;
	}

	public void setPost(Set<Post> post) {
		this.post = post;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public Date getCategoryDate() {
		return categoryDate;
	}

	public Date getCategoryEditDate() {
		return categoryEditDate;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public void setCategoryDate(Date categoryDate) {
		this.categoryDate = categoryDate;
	}

	public void setCategoryEditDate(Date categoryEditDate) {
		this.categoryEditDate = categoryEditDate;
	}
}
