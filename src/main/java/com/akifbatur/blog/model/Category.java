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
import javax.persistence.ManyToOne;
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
@Table(name="CATEGORY", catalog="BLOG")
public class Category 
{
	private static final Logger logger = LoggerFactory.getLogger(Category.class);
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//Each category has an author
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="AUTHOR_ID", nullable = false)
	private Author authorId;
	
	//Each category has many posts
	@OneToMany(mappedBy="categoryId", fetch = FetchType.LAZY)
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
}
