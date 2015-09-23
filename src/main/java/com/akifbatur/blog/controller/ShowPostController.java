package com.akifbatur.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.service.PostService;

/**
 * 
 * @author Akif Batur 
 *
 */
@Controller("showPostController")
public class ShowPostController 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ShowPostController.class);
	
	@Autowired
	PostService postService;
			
	//Get the post by postTitle
	@RequestMapping(value="/post/title/{postTitle}", method = RequestMethod.GET)
	public ModelAndView showPostByTitle(Model showPostByTitle, @PathVariable String postTitle)
	{	
		try 
		{
			List<Post> posts = this.postService.getPostsByTitle(postTitle);
			if(posts.size()==0)
				return new ModelAndView("redirect:/");
			showPostByTitle.addAttribute("posts", posts);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("showPost", "showPostByTitle", showPostByTitle);
	}
	
	//Get the posts by userName
	@RequestMapping(value="/post/author/{userName}", method = RequestMethod.GET)
	public ModelAndView showPostByUserName(Model showPostByUserName, @PathVariable String userName)
	{	
		List<Post> posts = null;
		try 
		{
			posts = this.postService.getPostsByUserName(userName);
			if(posts.size()==0)
				return new ModelAndView("redirect:/");
			showPostByUserName.addAttribute("posts", posts);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("showPost", "showPostByUserName", showPostByUserName);
	}
	
	//Get the posts by tag
	@RequestMapping(value="/post/tag/{tagText}", method = RequestMethod.GET)
	public ModelAndView showPostByTag(Model showPostByTag, @PathVariable String tagText)
	{	
		List<Post> posts = null;
		try 
		{
			posts = this.postService.getPostsByTag(tagText);
			if(posts.size()==0)
				return new ModelAndView("redirect:/");
			showPostByTag.addAttribute("posts", posts);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("showPost", "showPostByTag", showPostByTag);
	}
	
	//Get the posts by category
	@RequestMapping(value="/post/category/{categoryTitle}", method = RequestMethod.GET)
	public ModelAndView showPostByCategory(Model showPostByCategory, @PathVariable String categoryTitle)
	{	
		List<Post> posts = null;
		try 
		{
			posts = this.postService.getPostsByCategory(categoryTitle);
			if(posts.size()==0)
				return new ModelAndView("redirect:/");
			showPostByCategory.addAttribute("posts", posts);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("showPost", "showPostByCategory", showPostByCategory);
	}
}