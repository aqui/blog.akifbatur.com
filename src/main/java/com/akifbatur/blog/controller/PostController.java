package com.akifbatur.blog.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.model.Category;
import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.service.AuthorService;
import com.akifbatur.blog.service.CategoryService;
import com.akifbatur.blog.service.PostService;

/**
 * 
 * @author Akif Batur 
 *
 */
@Controller("postController")
@RequestMapping("/post")
public class PostController 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView post(Model postModel)
	{		
		postModel.addAttribute("post",new Post());
		return new ModelAndView("post", "postModel", postModel);
	}
	
	//Some data binding
	@ModelAttribute("categories")
	public List<Category> fetchAllCategories()
	{
		return categoryService.getCategories();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView savePost(Model savePostModel, @ModelAttribute("post") @Valid Post post, BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("post", "savePostModel", savePostModel);
		}
		try 
		{
			post.setPostTitle(post.getPostTitle().trim());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String userName = userDetails.getUsername();
			Author author = authorService.getAuthorByUserName(userName);
			post.setAuthorId(author);
			post.setPostDate(new Date());
			post.setPostEditDate(new Date());
			this.postService.savePost(post);
		}
		catch(Exception e)
		{
			savePostModel.addAttribute("message",new String("this post title is exist"));
			return new ModelAndView("post", "savePostModel", savePostModel);
		}
		return new ModelAndView("index", "savePostModel", savePostModel);
	}
}