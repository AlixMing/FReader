package com.reader.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.reader.model.User;
import com.reader.service.impl.UserService;
import com.reader.service.interfaces.IUserService;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class AdminController extends Controller {

	private IUserService userService = new UserService();

	public void index() throws Exception {
	 	// freemarker 配置
	 
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");// 编码1
		cfg.setDirectoryForTemplateLoading(new File("src/com/reader/templates"));
		// cfg.setClassForTemplateLoading(test.class, "/com/reader/ftl");
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template template = cfg.getTemplate("index.ftl");
		template.setEncoding("UTF-8");// 编码2

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "abbbddd");

		List<String> strings = new ArrayList<String>();
		strings.add("1");
		strings.add("2");
		strings.add("3");

		map.put("strings", strings);

		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("WebRoot/admin/hello.html"), "UTF-8"));
		template.process(map, out);
	
		out.flush();
		out.close();
		render("hello.html");
	}

	/*
	 * 分页得到user列表url:/admin/getUser/pageNum>0
	 */
	public void getUser() {
		renderJson("userList", userService.getUsers(getParaToInt()).getList());
	}

	/*
	 * 删除user url:/admin/delUser/idNum
	 */
	public void delUser() {
		renderJson("{status:" + userService.delUser(getParaToInt()) + "}");
	}

	// TODO spring plugin 完成后修改是否可以直接通过ioc传入user
	/*
	 * 保存user url:/admin/saveUser/userName-userPassword
	 */
	public void saveUser() {
		User user = new User().set("name", getPara(0)).set("password",
				getPara(1));
		renderJson("{status:" + userService.saveUser(user) + "}");
	}

	/*
	 * 更新user信息 url:/admin/updateUser/userId-userName-userPassword
	 */
	public void updateUser() {
		User user = User.me.findById(getPara(0));
		user.set("name", getPara(1)).set("password", getPara(2));
		renderJson("{status:" + userService.updateUser(user) + "}");
	}
}
