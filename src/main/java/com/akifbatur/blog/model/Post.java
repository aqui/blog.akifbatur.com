package com.akifbatur.blog.model;

import java.util.HashSet;
import java.util.Set;

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
@Table(name="POST", catalog="BLOG")
public class Post 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Post.class);
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//Each post has an author
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="AUTHOR_ID", nullable = false)
	private Author authorId;
	
	//Each post has a category
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CATEGORY_ID", nullable = false)
	private Category categoryId;
	
	//Each post has many tags
	@ManyToMany(mappedBy="postId", fetch = FetchType.LAZY)
	private Set<Tag> tagId = new HashSet<Tag>(0);

	public int getId() {
		return id;
	}

	public Author getAuthorId() {
		return authorId;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public Set<Tag> getTagId() {
		return tagId;
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

	public void setTagId(Set<Tag> tagId) {
		this.tagId = tagId;
	}
}
