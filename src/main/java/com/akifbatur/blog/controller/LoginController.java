package com.akifbatur.blog.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author Akif Batur
 *
 */
@ManagedBean(name="loginController")
public class LoginController {
     
    private String userName;
    
    private String password;
    
    public String doLogin() throws ServletException, IOException {
    	FacesContext facesContext = FacesContext.getCurrentInstance();
    	ExternalContext externalContext = facesContext.getExternalContext();
    	RequestDispatcher dispatcher = ((ServletRequest)externalContext.getRequest()).getRequestDispatcher("/login");
    	dispatcher.forward((ServletRequest)externalContext.getRequest(), (ServletResponse)externalContext.getResponse());
    	facesContext.responseComplete();
    	return null;
    }
    
    public void loginError(String error)
    {
    	FacesContext context = FacesContext.getCurrentInstance(); 
    	if(error.equals("incorrect"))
    	{    		
    		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong username or password.", null);
    		context.addMessage(null, facesMessage);
    	}    		
    }
    
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}