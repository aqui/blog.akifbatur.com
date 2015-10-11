package com.akifbatur.blog.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.service.PostService;

/**
 * 
 * @author Akif Batur 
 *
 */
@ManagedBean(name="indexController")
@ViewScoped
public class IndexController implements Serializable
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@ManagedProperty("#{postService}")
	private PostService postService;
	
	private List<Post> posts = new ArrayList<Post>();
	
	@PostConstruct
	public void init()
	{
		posts = postService.fetchAllPost();
	}
	
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void deletePost(int id)
	{
		Post post = this.postService.getPostById(id);
		this.postService.deletePost(post);
		posts = postService.fetchAllPost();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Post deleted", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
}