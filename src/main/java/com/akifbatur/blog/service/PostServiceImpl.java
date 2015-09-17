package com.akifbatur.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akifbatur.blog.dao.PostDAO;
import com.akifbatur.blog.model.Post;

@Service("postService")
public class PostServiceImpl implements PostService
{
	@Autowired
	PostDAO postDAO;
	
	@Override
	@Transactional
	public void savePost(Post post)
	{
		this.postDAO.savePost(post);
	}

	@Override
	@Transactional
	public List<Post> fetchAllPost() 
	{
		return this.postDAO.fetchAllPost();
	}	
}
