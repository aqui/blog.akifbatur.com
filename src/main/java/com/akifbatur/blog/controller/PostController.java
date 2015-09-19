package com.akifbatur.blog.controller;

import java.util.ArrayList;
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
			System.out.println(e);
			savePostModel.addAttribute("message",new String("there was an error"));
			return new ModelAndView("post", "savePostModel", savePostModel);
		}
		return new ModelAndView("index", "savePostModel", savePostModel);
	}
	
	//Get the post by postTitle
	@RequestMapping(value="/post/title/{postTitle}", method = RequestMethod.GET)
	public ModelAndView showPostByTitle(Model showPostByTitle, @PathVariable String postTitle)
	{	
		List<Post> posts = null;
		try 
		{
			posts = this.postService.getPostsByTitle(postTitle);
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
	
	//Delete post by id
	@RequestMapping(value="/post/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deletePost(Model deletePost, @PathVariable int id)
	{	
		try
		{
			this.postService.deletePost(id);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("index", "deletePost", deletePost);
	}
	
	//Edit post by id
	@RequestMapping(value="/post/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editPost(Model editPost, @PathVariable int id)
	{	
		Post post = this.postService.getPostById(id);
		//TODO: add tags to the initial form
		Set<Tag> tagSet = post.getTagId();
		StringBuilder tagString = new StringBuilder();
		tagSet.forEach(value->tagString.append(value.getTagText()).append(","));
		tagString.deleteCharAt(tagString.length()-1);
		editPost.addAttribute("tagString", tagString);
		editPost.addAttribute("post", post);
		return new ModelAndView("editPost", "editPost", editPost);
	}
	
	//Edit post by id
	@RequestMapping(value="/post/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editPostSave(Model editPostSave, @PathVariable int id, @ModelAttribute("post") @Valid Post post
			,BindingResult result, @RequestParam("tags") String tags)
	{	
		//TODO: If post author and editor is different or editor is not an admin then go to index
		if(result.hasErrors()) //If Post attributes are not validated
		{
			editPostSave.addAttribute("tagString", tags);
			return new ModelAndView("editPost", "editPostSave", editPostSave);
		}
		//Get real post
		Post realPost = this.postService.getPostById(id);
		//Get tags of the real post
//		Set<Tag> tagSet = realPost.getTagId();
//		List<Tag> hede = new ArrayList<Tag>();
//		hede.addAll(tagSet);
//		hede.remove(0);
//		tagSet.clear();
//		tagSet.addAll(hede);
//		realPost.setTagId(tagSet);
		realPost.setCategoryId(post.getCategoryId());
		realPost.setPostEditDate(new Date());
		realPost.setPostTitle(post.getPostTitle());
		realPost.setPostBody(post.getPostBody());
		this.postService.updatePost(realPost);
		return new ModelAndView("editPost", "editPostSave", editPostSave);
	}
}