package com.akifbatur.blog.service;

import java.util.List;

import com.akifbatur.blog.model.Post;

public interface PostService
{
	public void savePost(Post post);
	
	public List<Post> fetchAllPost();
}