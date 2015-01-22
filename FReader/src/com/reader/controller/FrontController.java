package com.reader.controller;
import com.jfinal.core.Controller;

public class FrontController extends Controller {
	public void index(){
		//setSessionAttr("user", "aaa");
		//setAttr("user", "aaa");
		getRequest().getSession().setAttribute("useriii", "aaa");
		
		//render("index.jsp");
		
	}
	
	public void hurry(){
		redirect("/admin/login.html	");
	}
	
	//TODO 使用用户名+密码作为明文进行MD5加密
	public void sign(){
	}
}
