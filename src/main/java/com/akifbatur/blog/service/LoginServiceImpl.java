package com.akifbatur.blog.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import com.akifbatur.blog.dao.LoginDAO;
import com.akifbatur.blog.model.Author;
import com.akifbatur.blog.model.Role;

/**
 * 
 * @author Akif Batur
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService, UserDetailsService 
{
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	LoginDAO loginDAO;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
	{
		Author author = null;
		try
		{
			author = loginDAO.findByUserName(userName);
		}
		catch (Exception e) 
		{
			logger.error(e.toString());
			return null;
		}
		List<GrantedAuthority> authorities = buildUserAuthority(author.getRole());
		return buildUserForAuthentication(author, authorities);
	}

	private UserDetails buildUserForAuthentication(Author author, List<GrantedAuthority> authorities) 
	{
		return new User(author.getUserName(), author.getPassword(), author.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<Role> role) 
	{
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		for (Role roles : role) 
		{
			setAuths.add(new SimpleGrantedAuthority(roles.getRole()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
}
