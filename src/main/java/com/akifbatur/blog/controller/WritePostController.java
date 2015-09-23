package com.akifbatur.blog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
@Controller("writePostController")
public class WritePostController 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(WritePostController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	TagService tagService;
	
	//Some data binding
	@ModelAttribute("categories")
	public List<Category> getAllCategories()
	{
		return categoryService.getCategories();
	}
		
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value="/post/write", method = RequestMethod.GET)
	public ModelAndView createForm(Model postModel)
	{		
		postModel.addAttribute("post",new Post());
		return new ModelAndView("writePost", "postModel", postModel);
	}
	
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value="/post/write", method = RequestMethod.POST)
	public ModelAndView savePost(Model savePostModel, @ModelAttribute("post") @Valid Post post, 
			BindingResult result, @RequestParam("tagField") String tagField)
	{
		if(result.hasErrors()) //If Post attributes are not validated
		{
			savePostModel.addAttribute("tagField", tagField);
			return new ModelAndView("writePost", "savePostModel", savePostModel);
		}
		try 
		{			
			//If tags are given
			if(!tagField.equals(""))
			{
				//Split tags
				List<String> tagList = new ArrayList<String>(Arrays.asList(tagField.split(",")));
				//For every given tag:
				for(String tagListElement : tagList)
				{
					if(tagListElement.equals("")||tagListElement.equals(" "))
						continue;
					//Check if tag in the database
					Tag tag = this.tagService.checkTag(tagListElement);
					if(tag!=null)
					{
						//If the tag is exist then use it
						post.getTags().add(tag);
						tag.getPosts().add(post);
					}
					else
					{
						//If tag is not exist then create a new tag
						Tag tag2 = new Tag();
						tag2.setTagText(tagListElement);
						this.tagService.saveTag(tag2);
						post.getTags().add(tag2);
						tag2.getPosts().add(post);
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
			System.out.println(e);
			savePostModel.addAttribute("message",new String("there was an error"));
			return new ModelAndView("writePost", "savePostModel", savePostModel);
		}
		return new ModelAndView("redirect:/post/title/"+post.getPostTitle());
	}
}