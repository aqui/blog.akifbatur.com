package com.akifbatur.blog.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.akifbatur.blog.model.Author;
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
@ManagedBean(name="writePostController")
@RequestScoped
public class WritePostController implements Serializable
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(WritePostController.class);
	
	@ManagedProperty("#{categoryService}")
	private CategoryService categoryService;
	
	@ManagedProperty("#{postService}")
	private PostService postService;
	
	@ManagedProperty("#{authorService}")
	private AuthorService authorService;
	
	@ManagedProperty("#{tagService}")
	private TagService tagService;

	private String postTitle;
	private String postBody;
	private String tagField = "";
	private List<String> categories = new ArrayList<String>();
	private String category;
	private Set<String> tagFieldSet = new HashSet<String>();
	private List<String> tagFieldList = new ArrayList<String>();
	
	@PostConstruct
	public void init() {
		categoryService.getCategories().forEach(category->categories.add(category.getCategoryTitle()));
	}
	 
	public String savePost()
	{
		Post post = new Post();

		try 
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String userName = auth.getName();
			Author author = authorService.getAuthorByUserName(userName);
			if(!tagField.equals(""))
			{				
				for (String string : tagField.split(",")) {
					String str = string.replaceAll("\\s+", " ").trim();
					if(str.equals("") || str.equals(" "))
						continue;
					tagFieldList.add(str);
				}
				tagFieldSet.addAll(tagFieldList);
				tagFieldSet.forEach(tagFieldSetElement ->
				{
					Tag tag = tagService.getTagByText(tagFieldSetElement);
					if(tag!=null)
					{
						post.getTags().add(tag);
					}
					else
					{
						tag = new Tag();
						tag.setTagText(tagFieldSetElement);
						tagService.saveTag(tag);
						post.getTags().add(tag);
					}
				});
			}
			post.setCategory(categoryService.getCategoryByTitle(category));
			post.setPostTitle(postTitle);
			post.setPostBody(postBody);
			post.setPostDate(new Date());
			post.setPostEditDate(new Date());
			post.setAuthorId(author);
			this.postService.savePost(post);
			return "index.xhtml?faces-redirect=true";
		} 
		catch (Exception e) 
		{
			FacesContext context = FacesContext.getCurrentInstance(); 
	    	FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "This title is exist.", null);
	    	context.addMessage(null, facesMessage);
	    	System.out.println(e.getMessage());
			return "";
		}
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
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

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public PostService getPostService() {
		return postService;
	}

	public AuthorService getAuthorService() {
		return authorService;
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public void setAuthorService(AuthorService authorService) {
		this.authorService = authorService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public Set<String> getTagFieldSet() {
		return tagFieldSet;
	}

	public List<String> getTagFieldList() {
		return tagFieldList;
	}

	public void setTagFieldSet(Set<String> tagFieldSet) {
		this.tagFieldSet = tagFieldSet;
	}

	public void setTagFieldList(List<String> tagFieldList) {
		this.tagFieldList = tagFieldList;
	}
}