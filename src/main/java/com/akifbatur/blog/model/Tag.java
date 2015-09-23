package com.akifbatur.blog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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
@Table(name="TAG", catalog="BLOG")
public class Tag implements Serializable
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Tag.class);
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="TAG_TEXT", unique=true, nullable=true)
	private String tagText;
	
	//Each tag has many posts
	@ManyToMany(mappedBy="tags", fetch = FetchType.EAGER)
	private List<Post> posts = new ArrayList<Post>();
	
	public Tag()
	{
		
	}
	
	public Tag(int id, String tagText, List<Post> posts) {
		super();
		this.id = id;
		this.tagText = tagText;
		this.posts = posts;
	}

	public int getId() {
		return id;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String getTagText() {
		return tagText;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}
}
