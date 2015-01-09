package com.reader.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.spring.Inject.BY_NAME;
import com.jfinal.plugin.spring.IocInterceptor;
import com.reader.model.User;
import com.reader.service.interfaces.IUserService;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

@Before(IocInterceptor.class)
public class AdminController extends Controller {

	@BY_NAME
	private IUserService userService;
	Configuration cfg = new Configuration();

	public void index() throws Exception {
		cfg.setDefaultEncoding("UTF-8");// 编码1
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDirectoryForTemplateLoading(new File("src/com/reader/templates"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "abbbddd");

		List<String> strings = new ArrayList<String>();
		strings.add("1");
		strings.add("2");
		strings.add("3");

		map.put("strings", strings);

		// 加载模板
		Template template = cfg.getTemplate("index.ftl");
		template.setEncoding("UTF-8");// 编码2

		// 输出到response
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();

		template.process(map, out);

		out.flush();
		out.close();
	}

	/*
	 * 分页得到user列表url:/admin/getUser/pageNum>0
	 */
	public void getUser() {
		cfg.setDefaultEncoding("UTF-8");// 编码1
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> users = userService.getUsers(getPara() == null?1:getParaToInt()).getList();

		map.put("users", users);

		// 加载模板
		Template template;
		PrintWriter out = null;
		try {
			cfg.setDirectoryForTemplateLoading(new File("src/com/reader/templates"));
			template = cfg.getTemplate("user.ftl");
			template.setEncoding("UTF-8");// 编码2

			// 输出到response
			getResponse().setCharacterEncoding("UTF-8");
			out = getResponse().getWriter();

			template.process(map, out);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		// renderJson("userList",userService.getUsers(getParaToInt()).getList());
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
