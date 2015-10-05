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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.akifbatur.blog.model.Category;
import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.model.Tag;
import com.akifbatur.blog.service.CategoryService;
import com.akifbatur.blog.service.PostService;
import com.akifbatur.blog.service.TagService;

/**
 * Update a post with given ID as a request parameter.
 *  
 * @author Akif Batur
 *
 */
@Controller("editPostController")
public class EditPostController 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(WritePostController.class);
	
	@Autowired
	PostService postService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	CategoryService categoryService;
	
	//Some data binding
	@ModelAttribute("categories")
	public List<Category> fetchAllCategories()
	{
		return categoryService.getCategories();
	}
		
	/**
	 * Populate the form with post values and send them to
	 * update the post in the updatePost method.
	 * @param editPost Model variable
	 * @param id Post ID
	 * */
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value="/post/edit/{id}", method = RequestMethod.GET)
	public ModelAndView createEmptyForm(Model editPost, @PathVariable int id)
	{	
		Post post = this.postService.getPostById(id);
		List<Tag> tagList = post.getTags();
		if(!tagList.isEmpty())
		{
			StringBuilder tagString = new StringBuilder();
			tagList.forEach(value->tagString.append(value.getTagText()).append(","));
			tagString.deleteCharAt(tagString.length()-1);
			editPost.addAttribute("tagString", tagString);
		}
		editPost.addAttribute("post", post);
		return new ModelAndView("editPost", "editPost", editPost);
	}
	
	/**
	 * Get the edited post values from the from and update the post.
	 * @param editPostSave Model variable
	 * @param id Post ID
	 * @param post Post model object
	 * @param result BindingResult object to catch form errors
	 * @param tagField Form variable for tags
	 * */
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value="/post/edit/{id}", method = RequestMethod.POST)
	public ModelAndView updatePost(
				Model editPostSave, 
				@PathVariable int id, 
				@ModelAttribute("post") @Valid Post post,
				BindingResult result, @RequestParam("tagField") String tagField)
	{	
		//TODO: If post author and editor is different or editor is not an admin then go to index
		if(result.hasErrors()) //If Post attributes are not validated
		{
			editPostSave.addAttribute("tagString", tagField);
			return new ModelAndView("editPost", "editPostSave", editPostSave);
		}
		//Get real post
		Post realPost = this.postService.getPostById(id);
		//If no tags given then clean it all from the post
		if(tagField.equals(""))
		{
			realPost.getTags().clear();
		}
		else
		{	
			//TODO: eger verilen tag'ler ve eldeki tag'ler aynıysa aşağıdaki işlemlere gerek yok.
			List<Tag> set2 = new ArrayList<Tag>();
			List<String> tagList = new ArrayList<String>(Arrays.asList(tagField.split(",")));
			for(String tagListElement : tagList)
			{
				//If given tag is empty then go for the next one
				if(tagListElement.equals("")||tagListElement.equals(" ")) 
					continue;
				//Check if tag in the database
				Tag tag = this.tagService.getTagByText(tagListElement);
				if(tag!=null)
				{
					set2.add(tag);
				}
				else
				{
					//If tag is not exist then create a new tag
					Tag tag2 = new Tag();
					tag2.setTagText(tagListElement);
					this.tagService.saveTag(tag2);
					set2.add(tag2);
				}
			}
			realPost.getTags().clear();
			realPost.getTags().addAll(set2);
		}
		realPost.setCategoryId(post.getCategoryId());
		realPost.setPostEditDate(new Date());
		realPost.setPostTitle(post.getPostTitle());
		realPost.setPostBody(post.getPostBody());
		this.postService.updatePost(realPost);
		return new ModelAndView("redirect:/post/title/"+realPost.getPostTitle());
	}
}
