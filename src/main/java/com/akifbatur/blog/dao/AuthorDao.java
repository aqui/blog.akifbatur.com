package com.akifbatur.blog.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

/**
 * @author Akif Batur
 * 
 */
public class AuthorDao extends NamedParameterJdbcDaoSupport
{
	//An example method
	public String getAuthorName(int id)
	{
		String sql = "select name from Student where id = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[] {id}, String.class);
	}
}
