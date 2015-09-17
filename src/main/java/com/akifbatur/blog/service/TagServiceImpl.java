package com.akifbatur.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akifbatur.blog.dao.TagDAO;
import com.akifbatur.blog.model.Tag;

@Service("tagService")
public class TagServiceImpl implements TagService
{
	@Autowired
	TagDAO tagDAO;
	
	@Override
	@Transactional
	public void saveTag(Tag tag) 
	{
		this.tagDAO.saveTag(tag);
	}

	@Override
	@Transactional
	public Tag checkTag(String tagText) 
	{
		return this.tagDAO.checkTag(tagText);	
	}	
}
