package com.reader.interceptor;

import javax.servlet.http.HttpSession;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class UserInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		HttpSession session = ai.getController().getSession();
		if(session.getAttribute("user") != null){
			ai.invoke();
			//System.out.println(ai.getActionKey() + "..........execute........." + ai.getMethodName() + "....." + session.getAttribute("user"));
		}else{
			ai.getController().redirect("/admin/login.html");
		}
		//System.out.println("hello end");
	}
}
