package com.akifbatur.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.akifbatur.blog.model.Tag;

/**
 * @author Akif Batur
 * 
 */
public class TagMapper implements RowMapper<Tag> 
{
	@Override
	public Tag mapRow(ResultSet resultSet, int rowNumber) throws SQLException 
	{
		return null;
	}
}
