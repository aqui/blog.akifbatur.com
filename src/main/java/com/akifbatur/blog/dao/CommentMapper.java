package com.akifbatur.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.akifbatur.blog.model.Comment;

/**
 * @author Akif Batur
 * 
 */
public class CommentMapper implements RowMapper<Comment> 
{
	@Override
	public Comment mapRow(ResultSet resultSet, int rowNumber) throws SQLException 
	{
		return null;
	}
}
