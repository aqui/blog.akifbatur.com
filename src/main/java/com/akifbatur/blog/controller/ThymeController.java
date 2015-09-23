package com.akifbatur.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.akifbatur.blog.model.Post;
import com.akifbatur.blog.service.PostService;

@Controller("thymeController")
@RequestMapping(value="/thyme")
public class ThymeController 
{
	@Autowired
	PostService postService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView test(Model thymeModel, Post post)
	{
		List<Post> posts = this.postService.fetchAllPost();
		thymeModel.addAttribute("posts", posts);
		return new ModelAndView("thyme","thymeModel",thymeModel);
	}
}
