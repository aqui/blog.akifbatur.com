package com.akifbatur.blog;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author Akif Batur
 * 
 */
@SessionScoped
@ManagedBean(name = "indexBean")
public class IndexBean implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	public String printPrompt(String authorName)
	{
		return "aqui@aqui~$ "+authorName;
	}
}
