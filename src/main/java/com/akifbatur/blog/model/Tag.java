package com.akifbatur.blog.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	public Tag()
	{
		
	}
	
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
	@ManyToMany(mappedBy="tagId", fetch = FetchType.EAGER)
	private Set<Post> postId = new LinkedHashSet<Post>(0);

	public int getId() {
		return id;
	}

	public Set<Post> getPostId() {
		return postId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPostId(Set<Post> postId) {
		this.postId = postId;
	}

	public String getTagText() {
		return tagText;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}
}
