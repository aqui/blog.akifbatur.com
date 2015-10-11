package com.akifbatur.blog.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.akifbatur.blog.model.Category;
import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.model.Tag;
import com.akifbatur.blog.service.CategoryService;
import com.akifbatur.blog.service.PostService;
import com.akifbatur.blog.service.TagService;

/**
 * 
 * @author Akif Batur 
 *
 */
@ManagedBean(name="editPostController")
@ViewScoped
public class EditPostController implements Serializable
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EditPostController.class);
	
	@ManagedProperty("#{postService}")
	private PostService postService;

	@ManagedProperty("#{categoryService}")
	private CategoryService categoryService;
	
	@ManagedProperty("#{tagService}")
	private TagService tagService;
	
	private String category = "";
	private String postTitle = "";
	private String postBody = "";
	private String tagField = "";
	private int postId;
	private Post post = null;
	
	private List<String> categories = new ArrayList<String>();

	public void initPost(int id) throws IOException
	{
		try
		{
			post = this.postService.getPostById(id);
			categoryService.getCategories().forEach(category->categories.add(category.getCategoryTitle()));
			category = post.getCategory().getCategoryTitle();
			postId = post.getId();
			postTitle = post.getPostTitle();
			postBody = post.getPostBody();
			post.getTags().forEach(tag -> {
				if(tag!=null)
					tagField+=tag.getTagText()+",";
			});
			if(!tagField.equals(""))
				tagField = tagField.substring(0, tagField.length() - 1);
		} 
		catch (Exception e) 
		{
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			ec.redirect("index.xhtml");
		}
	}
	
	public String editPost()
	{
		//If category changed
		if(!post.getCategory().getCategoryTitle().equals(category))
		{
			Category cat = this.categoryService.getCategoryByTitle(category);
			post.setCategory(cat);
		}
		
		//Tag processes
		if(tagField.equals(""))
		{
			post.getTags().clear();
		}
		else
		{
			Set<String> stringSet = new HashSet<String>();
			Set<Tag> tagSet = new HashSet<Tag>();
			for (String string : tagField.split(",")) 
			{
				String str = string.replaceAll("\\s+", " ").trim();
				if(str.equals(""))
					continue;
				stringSet.add(str);
			}
			stringSet.forEach(stringSetElement->{
				Tag existTag = tagService.getTagByText(stringSetElement);
				if(existTag!=null)
				{
					tagSet.add(existTag);
				}
				else
				{
					Tag newTag = new Tag();
					newTag.setTagText(stringSetElement);
					this.tagService.saveTag(newTag);
					tagSet.add(newTag);
				}
			});
			post.getTags().clear();
			post.getTags().addAll(tagSet);
		}
		post.setPostEditDate(new Date());
		post.setPostBody(postBody);
		post.setPostTitle(postTitle);
		this.postService.updatePost(post);
		return "index.xhtml?faces-redirect=true";
	}
	
	public String getPostTitle() {
		return postTitle;
	}

	public String getPostBody() {
		return postBody;
	}

	public String getTagField() {
		return tagField;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	public void setTagField(String tagField) {
		this.tagField = tagField;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getCategories() {
		categoryService.getCategories().forEach(category->categories.add(category.getCategoryTitle()));
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}	
	
	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
}