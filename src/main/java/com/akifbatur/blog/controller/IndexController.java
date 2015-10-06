package com.akifbatur.blog.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.service.PostService;

/**
 * When there is a request to the "/" it will be redirected
 * to the /WEB-INF/view/index.jsp by the DispatcherServlet
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
	
	private List<Post> posts;

	@PostConstruct
	public void init()
	{
		posts = new ArrayList<Post>();
		posts.addAll(postService.fetchAllPost());
	}
	
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public List<Post> getPosts() {
		return posts;
	}
}