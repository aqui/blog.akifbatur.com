package com.akifbatur.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.akifbatur.blog.model.Category;

/**
 * @author Akif Batur
 * 
 */
public class CategoryMapper implements RowMapper<Category> 
{
	@Override
	public Category mapRow(ResultSet resultSet, int rowNumber) throws SQLException 
	{
		return null;
	}
}
