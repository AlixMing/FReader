package com.reader.controller;
import com.jfinal.core.Controller;

public class FrontController extends Controller {
	public void index(){
		//setSessionAttr("user", "aaa");
		//setAttr("user", "aaa");
		getRequest().getSession().setAttribute("user", "aaa");
		//render("index.jsp");
		
	}
	
	public void hurry(){
		System.out.println(getSessionAttr("user"));
	}
}
