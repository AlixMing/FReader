package com.reader.route;

import com.jfinal.config.Routes;
import com.reader.controller.AdminController;

public class AdminRoute extends Routes {

	@Override
	public void config() {
		add("/admin", AdminController.class);
	}
}
