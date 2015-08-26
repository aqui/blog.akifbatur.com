package com.akifbatur.blog.dao;
/**
 * @author Akif Batur
 * 
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.akifbatur.blog.model.Post;

public class PostMapper implements RowMapper<Post> 
{
	@Override
	public Post mapRow(ResultSet resultSet, int rowNumber) throws SQLException 
	{
		return null;
	}
}
