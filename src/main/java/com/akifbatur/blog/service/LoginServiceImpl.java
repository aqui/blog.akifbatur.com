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
		try
		{
			Author author = loginDAO.findByUserName(userName);
			logger.info("Author is logged-in: "+author);
			List<GrantedAuthority> authorities = buildUserAuthority(author.getRoles());
			return buildUserForAuthentication(author, authorities);
		}
		catch (Exception e) 
		{
			logger.error(e.toString());
			return null;
		}
	}

	private UserDetails buildUserForAuthentication(Author author, List<GrantedAuthority> authorities) 
	{
		return new User(author.getUserName(), author.getPassword(), author.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<Role> roles) 
	{
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		for (Role role : roles) 
		{
			setAuths.add(new SimpleGrantedAuthority(role.getRole()));
		}
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
		return result;
	}
}
