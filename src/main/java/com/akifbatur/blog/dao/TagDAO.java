package com.akifbatur.blog.dao;

import java.util.List;

import com.akifbatur.blog.model.Tag;

/**
 * 
 * @author Akif Batur
 *
 */
public interface TagDAO 
{
	public void saveTag(Tag tag);

	public Tag checkTag(String tagText);

	public List<Tag> fetchAllTags();
}
