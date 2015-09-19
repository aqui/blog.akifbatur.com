package com.akifbatur.blog.service;

import java.util.List;

import com.akifbatur.blog.model.Post;

public interface PostService
{
	public void savePost(Post post);
	
	public List<Post> fetchAllPost();

	public List<Post> getPostsByTitle(String postTitle);

	public List<Post> getPostsByUserName(String userName);

	public List<Post> getPostsByTag(String tagText);

	public List<Post> getPostsByCategory(String categoryTitle);

	public void deletePost(int id);
}
