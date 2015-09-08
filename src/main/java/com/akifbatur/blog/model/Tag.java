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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="TAG", catalog="BLOG")
public class Tag 
{
	private static final Logger logger = LoggerFactory.getLogger(Tag.class);
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//Each tag has many posts
	@ManyToMany
	@JoinTable(name="TAG_POST", joinColumns=@JoinColumn(name="TAG_ID"),inverseJoinColumns=@JoinColumn(name="POST_ID"))
	private Set<Post> postId = new HashSet<Post>(0);

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
}
