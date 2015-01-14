package com.reader.interceptor;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class AdminInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		System.out.println("hello begin");
		ai.getController().redirect("/admin/login");
		ai.invoke();
		System.out.println("hello end");
	}
}
