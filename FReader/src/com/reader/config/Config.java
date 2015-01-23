package com.reader.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.spring.IocInterceptor;
import com.jfinal.plugin.spring.SpringPlugin;
import com.reader.controller.AppController;
import com.reader.controller.DemoController;
import com.reader.model.Activity;
import com.reader.model.ActivityUsers;
import com.reader.model.Blog;
import com.reader.model.Book;
import com.reader.model.Clocks;
import com.reader.model.Comments;
import com.reader.model.Note;
import com.reader.model.Pbook;
import com.reader.model.Recomment;
import com.reader.model.Timeline;
import com.reader.model.Type;
import com.reader.model.User;
import com.reader.route.AdminRoute;
import com.reader.route.FrontRoute;

public class Config extends JFinalConfig{
	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("config_file.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/demo", DemoController.class);
		me.add(new FrontRoute());
		me.add(new AdminRoute());
		me.add("/app", AppController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		//添加数据库插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("Blog", Blog.class);//实现ORmapping
		arp.addMapping("User", User.class);
		arp.addMapping("Book", Book.class);
		arp.addMapping("Type", Type.class);
		arp.addMapping("Activity", Activity.class);
		arp.addMapping("Clocks", Clocks.class);
		arp.addMapping("Comments", Comments.class);
		arp.addMapping("Note", Note.class);
		arp.addMapping("Pbook", Pbook.class);
		arp.addMapping("Recomment", Recomment.class);
		arp.addMapping("Timeline", Timeline.class);
		arp.addMapping("Activity_user", ActivityUsers.class);
		
		//添加spring插件
		me.add(new SpringPlugin());
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new TxByRegex(".*save.*"));
		me.add(new TxByRegex(".*update.*"));
		me.add(new SessionInViewInterceptor());
		me.add(new IocInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
	}
	
	public void afterJFinalStart() {
		System.out.println("JFinal start!");
	}
	
	public void beforeJFinalStop() {
		System.out.println("JFinal stop!");
	}
}
