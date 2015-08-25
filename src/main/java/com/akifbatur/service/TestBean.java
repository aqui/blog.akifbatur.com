package com.akifbatur.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "testBean")
public class TestBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public String printMsg()
	{
		return "Blog Module JSF Test";
	}
}
