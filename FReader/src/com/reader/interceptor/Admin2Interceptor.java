package com.reader.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class Admin2Interceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		System.out.println("sorry begin");
		ai.invoke();
		System.out.println("sorry end");
	}

}
