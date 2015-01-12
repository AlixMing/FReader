package com.reader.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.spring.Inject.BY_NAME;
import com.jfinal.plugin.spring.IocInterceptor;
import com.reader.config.Config;
import com.reader.model.User;
import com.reader.service.interfaces.IUserService;
import freemarker.template.Template;

@Before(IocInterceptor.class)
public class AdminController extends Controller {

	@BY_NAME
	private IUserService userService;

	public void index() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "abbbddd");

		List<String> strings = new ArrayList<String>();
		strings.add("1");
		strings.add("2");
		strings.add("3");
		map.put("strings", strings);

		// 加载模板
		Template template = Config.cfg.getTemplate("index.ftl");
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
		Map<String, Object> map = new HashMap<String, Object>();
		Page<User> userPage = userService.getUsers(getPara() == null ? 1
				: getParaToInt());
		map.put("users", userPage.getList());
		map.put("totalPage", userPage.getTotalPage());
		map.put("current", userPage.getPageNumber());
		map.put("totalRaw", userPage.getTotalRow());

		// 加载模板
		Template template;
		PrintWriter out = null;
		try {
			template = Config.cfg.getTemplate("user.ftl");
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

	//TODO 最后统一改为json数据传输
	/*
	 * 删除user url:/admin/delUser/idNum
	 */
	public void delUser() {
		if (userService.delUser(getParaToInt(0))) {
			//forwardAction("/admin/getUser");
			redirect("/admin/getUser/" + getPara(1));
		} else {
			renderJson("{status:false}");
		}
	}

	/*
	 * 保存user url:/admin/saveUser/
	 */
	public void saveUser() {
		// User user = getModel(User.class);
		User user = new User().set("name", getPara(0)).set("password",
				getPara(1));
		renderJson("{status:" + userService.saveUser(user) + "}");
	}

	/*
	 * 更新user信息 url:/admin/updateUser/userId-userName-userPassword
	 */
	public void updateUser() {
		User user = User.me.findById(getModel(User.class).get("id"));
		user.set("name", getModel(User.class).get("name")).set("password",
				getModel(User.class).get("password"));
		renderJson("{status:" + userService.updateUser(user) + "}");
	}

	public void login() {
		User user = getModel(User.class);
		System.out.println("login success" + user.getStr("name") + "...."
				+ user.getStr("password"));
	}
}
