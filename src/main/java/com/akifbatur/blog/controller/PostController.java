package com.akifbatur.blog.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.model.Category;
import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.model.Tag;
import com.akifbatur.blog.service.AuthorService;
import com.akifbatur.blog.service.CategoryService;
import com.akifbatur.blog.service.PostService;
import com.akifbatur.blog.service.TagService;

/**
 * 
 * @author Akif Batur 
 *
 */
@Controller("postController")
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
	
	@Autowired
	TagService tagService;
	
	@RequestMapping(value="/post", method = RequestMethod.GET)
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
	
	@RequestMapping(value="/post", method = RequestMethod.POST)
	public ModelAndView savePost(Model savePostModel, @ModelAttribute("post") @Valid Post post, 
			BindingResult result, @RequestParam("tags") String tags)
	{
		if(result.hasErrors()) //If Post attributes are not validated
		{
			return new ModelAndView("post", "savePostModel", savePostModel);
		}
		try 
		{			
			//If tags are given
			if(!tags.equals(""))
			{
				//Split tags
				Set<String> tagSet = new HashSet<String>(Arrays.asList(tags.split(",")));
				//For every given tag:
				for(String tagSetElement : tagSet)
				{
					//Check if tag in the database
					Tag tag = this.tagService.checkTag(tagSetElement);
					if(tag!=null)
					{
						//If the tag is exist then use it
						post.getTagId().add(tag);
						tag.getPostId().add(post);
					}
					else
					{
						//If tag is not exist then create a new tag
						Tag tag2 = new Tag();
						tag2.setTagText(tagSetElement);
						this.tagService.saveTag(tag2);
						post.getTagId().add(tag2);
						tag2.getPostId().add(post);
					}
				}
			}
			//Trim post title
			post.setPostTitle(post.getPostTitle().trim());
			//Get userName
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String userName = userDetails.getUsername();
			//Get author by userName
			Author author = authorService.getAuthorByUserName(userName);
			//Save post
			post.setAuthorId(author);
			post.setPostDate(new Date());
			post.setPostEditDate(new Date());
			this.postService.savePost(post);
		}
		catch(Exception e)
		{
			savePostModel.addAttribute("message",new String("there was an error"));
			return new ModelAndView("post", "savePostModel", savePostModel);
		}
		return new ModelAndView("index", "savePostModel", savePostModel);
	}
	
	@RequestMapping(value="/post/{postTitle}", method = RequestMethod.GET)
	public ModelAndView showPost(Model showPostModel, @PathVariable String postTitle)
	{	
		Post post = null;
		try 
		{
			post = this.postService.getPostByTitle(postTitle);
			showPostModel.addAttribute("post", post);
		}
		catch (Exception e)
		{
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("showPost", "showPostModel", showPostModel);
	}
}