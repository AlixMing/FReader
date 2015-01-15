package com.reader.interceptor;

import javax.servlet.http.HttpSession;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class AdminInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		HttpSession session = ai.getController().getSession();
		if(session.getAttribute("user") != null){
			ai.invoke();
		}else{
			ai.getController().redirect("/admin/login.html");
		}
		System.out.println("hello end");
	}
}
