package com.reader.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.reader.model.Blog;
import com.reader.model.Book;
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
		me.add(new FrontRoute());
		me.add(new AdminRoute());
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
		
		//添加spring插件
		//me.add(new SpringPlugin());
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new TxByRegex(".*save.*"));
		me.add(new TxByRegex(".*update.*"));
		//me.add(new TxByActionMethods("save", "update"));
		//me.add(new AdminInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
	}
	
	public void afterJFinalStart() {
		System.out.println("ddddd");
	}
	
	public void beforeJFinalStop() {
		System.out.println("fffff");
	}
}
