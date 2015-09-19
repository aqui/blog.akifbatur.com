package com.akifbatur.blog.dao;

import java.util.List;

import com.akifbatur.blog.model.Post;

/**
 * 
 * @author Akif Batur
 *
 */
public interface PostDAO 
{
	public void savePost(Post post);
	
	public List<Post> fetchAllPost();

	public List<Post> getPostsByTitle(String postTitle);

	public List<Post> getPostsByUserName(String userName);

	public List<Post> getPostsByTag(String tagText);

	public List<Post> getPostsByCategory(String categoryTitle);

	public void deletePost(int id);

	public Post getPostById(int id);

	public void updatePost(Post post);
}
