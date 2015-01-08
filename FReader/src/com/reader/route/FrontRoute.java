package com.reader.route;

import com.jfinal.config.Routes;
import com.reader.controller.FrontController;

public class FrontRoute extends Routes {

	@Override
	public void config() {
		add("/", FrontController.class);
	}

}
