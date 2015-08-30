package com.akifbatur.blog;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.akifbatur.blog.dao.AuthorDao;
import com.akifbatur.blog.model.Author;

/**
 * @author Akif Batur
 * 
 */
@SessionScoped
@ManagedBean(name = "testBean")
public class TestBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//An example method
	public String printName(int authorId)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		AuthorDao authorDao = (AuthorDao) context.getBean("authorDao");
		Author author = authorDao.getAuthorName(authorId);
		if(author!=null)
		{
			return String.valueOf(author.getAuthorName());
		}
		else
		{
			return "Not found.";
		}
	}
}
