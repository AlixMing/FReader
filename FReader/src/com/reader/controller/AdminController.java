package com.reader.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.spring.Inject.BY_NAME;
import com.jfinal.plugin.spring.IocInterceptor;
import com.reader.model.Activity;
import com.reader.model.Book;
import com.reader.model.User;
import com.reader.service.interfaces.IActivityService;
import com.reader.service.interfaces.IBookService;
import com.reader.service.interfaces.IUserService;

@Before(IocInterceptor.class)
public class AdminController extends Controller {

	@BY_NAME
	private IUserService userService;
	@BY_NAME
	private IActivityService activityService;
	@BY_NAME
	private IBookService bookService;

	public void index() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "abbbddd");

		List<String> strings = new ArrayList<String>();
		strings.add("1");
		strings.add("2");
		map.put("strings", strings);

		setSessionAttr("user", "aaa");
	}

	// TODO
	public void login() {
		//getSession().setAttribute("user", userService.login(getModel(User.class)));
		System.out.println(userService.login(getModel(User.class)).getStr("name") + "....." + userService.login(getModel(User.class)).getStr("password"));
		redirect("/admin/index");
	}

	/*
	 * user 分页得到user列表url:/admin/getUser/pageNum>0
	 */
	public void getUser() {
		Page<User> userPage = userService.getUsers(getPara() == null ? 1
				: getParaToInt());
		setAttr("users", userPage.getList());
		setAttr("totalPage", userPage.getTotalPage());
		setAttr("current", userPage.getPageNumber());
		setAttr("totalRaw", userPage.getTotalRow());

		render("user.html");
	}

	// TODO 最后统一改为json数据传输，即使用Ajax异步操作，实践！！！
	/*
	 * user 删除user url:/admin/delUser/idNum-pageNum
	 */
	public void delUser() {
		if (userService.delUser(getParaToInt(0))) {
			redirect("/admin/getUser/" + getPara(1));
		} else {
			renderJson("{status:false}");
		}
	}

	/*
	 * user 保存user url:/admin/saveUser/
	 */
	public void saveUser() {
		// User user = getModel(User.class);
		User user = new User().set("name", getPara(0)).set("password",
				getPara(1));
		renderJson("{status:" + userService.saveUser(user) + "}");
	}

	/*
	 * user 更新user信息 url:/admin/updateUser/current
	 */
	public void updateUser() {
		User user = User.me.findById(getModel(User.class).get("id"));
		user.set("name", getModel(User.class).get("name")).set("password",
				getModel(User.class).get("password"));
		if (userService.updateUser(user)) {
			redirect("/admin/getUser/" + getPara());
		} else {
			renderJson("{status:false}");
		}
	}

	//TODO 页面活动状态
	/*
	 * activity 分页得到activity列表
	 * url:/admin/getActivities/pageNum>0
	 */
	public void getActivities() {
		Page<Activity> activityPage = activityService
				.getActivities(getPara() == null ? 1 : getParaToInt());
		setAttr("activities", activityPage.getList());
		setAttr("totalPage", activityPage.getTotalPage());
		setAttr("current", activityPage.getPageNumber());
		setAttr("totalRaw", activityPage.getTotalRow());
		
		render("activity.html");
	}

	// TODO 最后统一改为json数据传输，即使用Ajax异步操作，实践！！！
	/*
	 * activity 删除activity 
	 * url:/admin/delActivity/idNum-pageNum
	 */
	public void delActivity() {
		if (activityService.delActivity(getParaToInt(0))) {
			redirect("/admin/getActivities/" + getPara(1));
		} else {
			renderJson("{status:false}");
		}
	}
	
	/*
	 * activity 添加activity 
	 * url:/admin/saveActivity/
	 */
	public void saveActivity(){
		Activity activity = getModel(Activity.class);
		if(activityService.saveActivity(activity)){
			redirect("/admin/getActivities/");
		}else{
			renderJson("{status:false}");
		}
	}
	
	/*
	 * book 分页得到book列表
	 * url:/admin/getBooks/typeNumId-pageNum pageNum>0,typeNumId输入0-所有 
	 */
	public void getBooks() {
		Page<Book> bookPage = bookService
				.getBooks(getParaToInt(0),getPara(1) == null ? 1 : getParaToInt(1));
		setAttr("books", bookPage.getList());
		setAttr("totalPage", bookPage.getTotalPage());
		setAttr("current", bookPage.getPageNumber());
		setAttr("totalRaw", bookPage.getTotalRow());

		render("book.html");
	}
	
	// TODO 最后统一改为json数据传输，即使用Ajax异步操作，实践！！！
	/*
	 * book 删除book 
	 * url:/admin/delBook/idNum-pageNum
	 */
	public void delBook() {
		if (bookService.delBook(getParaToInt(0))) {
			redirect("/admin/getBooks/" + getPara(1));
		} else {
			renderJson("{status:false}");
		}
	}
}
