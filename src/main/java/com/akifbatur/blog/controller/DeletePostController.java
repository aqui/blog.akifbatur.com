package com.akifbatur.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.service.PostService;

/**
 * 
 * @author Akif Batur
 * 
 * Deletes a given post by ID
 *
 */
@Controller("deletePostController")
public class DeletePostController 
{
	private static final Logger logger = LoggerFactory.getLogger(DeletePostController.class);
	
	@Autowired
	PostService postService;
	/**
	 * @param id: Post ID
	 * */
	//Delete post by id
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value="/post/delete/{id}", method = RequestMethod.GET)
	public String deletePost(@PathVariable int id)
	{	
		//TODO: Check if the author is an admin or the owner
		try
		{
			//Try to delete post
			Post post = this.postService.getPostById(id);
			this.postService.deletePost(post);
		}
		catch(Exception e)
		{
			logger.error("Error while deleting Post with ID = "+id+".");
			logger.error(e.toString());
			return "redirect:/";
		}
		logger.info("Post with ID = "+id+" deleted successfully.");
		return "redirect:/";
	}
}
