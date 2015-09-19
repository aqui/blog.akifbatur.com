package com.akifbatur.blog.service;

import java.util.List;

import com.akifbatur.blog.model.Tag;

public interface TagService
{
	public void saveTag(Tag tag);
	
	public Tag checkTag(String tagText);

	public List<Tag> fetchAllTags();
}
