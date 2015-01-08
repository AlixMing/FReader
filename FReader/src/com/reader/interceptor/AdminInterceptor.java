package com.reader.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class AdminInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		System.out.println("hello begin");
		ai.invoke();
		ai.getController().redirect("/");
		System.out.println("hello end");
	}
}
