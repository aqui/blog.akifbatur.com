package com.akifbatur.blog.dao;
/**
 * @author Akif Batur
 * 
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.akifbatur.blog.model.Category;

public class CategoryMapper implements RowMapper<Category> 
{
	@Override
	public Category mapRow(ResultSet resultSet, int rowNumber) throws SQLException 
	{
		return null;
	}
}
