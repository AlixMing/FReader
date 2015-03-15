package com.reader.controller;

import java.io.File;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.spring.Inject.BY_NAME;
import com.jfinal.upload.UploadFile;
import com.reader.interceptor.UserInterceptor;
import com.reader.model.Activity;
import com.reader.model.Book;
import com.reader.model.Type;
import com.reader.model.User;
import com.reader.service.interfaces.IActivityService;
import com.reader.service.interfaces.IActivityUsersService;
import com.reader.service.interfaces.IBookService;
import com.reader.service.interfaces.ICommentService;
import com.reader.service.interfaces.ITimelineService;
import com.reader.service.interfaces.ITypeService;
import com.reader.service.interfaces.IUserService;
import com.reader.util.MD5;

@Before(UserInterceptor.class)
public class AdminController extends Controller {

	@BY_NAME
	private IUserService userService;
	@BY_NAME
	private IActivityService activityService;
	@BY_NAME
	private IBookService bookService;
	@BY_NAME
	private ICommentService commentService;
	@BY_NAME
	private ITimelineService timelineService;
	@BY_NAME
	private IActivityUsersService activityUsersService;
	@BY_NAME
	private ITypeService typeService;

	public void index() {
		redirect("/admin/getUser/");
	}

	/*
	 * 登陆 URL：/admin/login
	 */
	@ClearInterceptor
	public void login() {
		User user = userService.login(1, getModel(User.class));
		setSessionAttr("user", user);
		if (user != null) {
			redirect("/admin/getUser/");
		}
	}

	/*
	 * 退出登录 URL：/admin/logout
	 */
	public void logout() {
		removeSessionAttr("user");
		redirect("/admin/login.html");
	}

	// TODO 如何实现页面下载数加1
	/*
	 * 下载书籍 url: /admin/download/IdNum
	 */
	public void download() {
		Book book = bookService.download(getParaToInt());
		renderFile(new File(JFinal.me().getServletContext().getRealPath("/")
				+ "/" + book.getStr("url").replace("\\", "/")));
		// redirect("/admin/getBookDetail/" + getParaToInt());
	}

	/*
	 * 搜索 url:/admin/search/pageNum-type?search=*** //type=1 user, type=2
	 * activity, type=3 book
	 */
	public void search() {
		String searchString = getPara("search");
		int pageNum = getParaToInt(0) == null ? 1 : getParaToInt(0);
		// renderJson("{a:b}");
		switch (getParaToInt(1)) {
		case 1:// user
			Page<User> userPage = userService.findByName(searchString, pageNum);
			System.out.println(userPage.toString());
			setAttr("type", 1);
			setAttr("search", searchString);
			setAttr("users", userPage.getList());
			setAttr("totalPage", userPage.getTotalPage());
			setAttr("current", userPage.getPageNumber());
			setAttr("totalRaw", userPage.getTotalRow());
			break;
		case 2:// activity
			Page<Activity> activityPage = activityService.findByName(
					searchString, pageNum);
			setAttr("type", 2);
			setAttr("search", searchString);
			setAttr("activities", activityPage.getList());
			setAttr("totalPage", activityPage.getTotalPage());
			setAttr("current", activityPage.getPageNumber());
			setAttr("totalRaw", activityPage.getTotalRow());
			break;
		case 3:// book
			Page<Book> bookPage = bookService.findByName(searchString, pageNum);
			setAttr("type", 3);
			setAttr("search", searchString);
			setAttr("books", bookPage.getList());
			setAttr("totalPage", bookPage.getTotalPage());
			setAttr("current", bookPage.getPageNumber());
			setAttr("totalRaw", bookPage.getTotalRow());
			break;
		default:
			break;
		}
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
	@ClearInterceptor
	public void saveUser() {
		// User user = getModel(User.class);
		/*User user = new User().set("name", getPara(0)).set("password",
				getPara(1)).set("pictureUrl", "upload\\user\\44.jpg");
		renderJson("{status:" + userService.saveUser(user) + "}");*/
		renderJson("{status:true}");
	}

	/*
	 * user 更新user信息 url:/admin/updateUser/current
	 */
	public void updateUser() {
		User user = User.me.findById(getModel(User.class).get("id"));
		user.set("name", getModel(User.class).get("name")).set(
				"password",
				MD5.UseMD5(getModel(User.class).getStr("name")
						+ getModel(User.class).getStr("password")));
		if (userService.updateUser(user)) {
			redirect("/admin/getUser/" + getPara());
		} else {
			renderJson("{status:false}");
		}
	}

	/*
	 * user 得到user的读书记录、活动记录和时间线信息 url:/admin/getUserDetail/idNum
	 */
	public void getUserDetail() {
		User user = User.me.findById(getParaToInt());
		setAttr("userName", user.get("name"));
		setAttr("books", user.getPBooks());
		setAttr("myActivities", user.getActivities());
		setAttr("activities",
				activityUsersService.getActivityUsersByUser(getParaToInt()));
		setAttr("timeline", timelineService.getTimelines(user.getInt("id")));
		render("userDetail.html");
	}

	/*
	 * activity 分页得到activity列表 url:/admin/getActivities/pageNum>0
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
	 * activity 删除activity url:/admin/delActivity/idNum-pageNum
	 */
	public void delActivity() {
		if (activityService.delActivity(getParaToInt(0))) {
			redirect("/admin/getActivities/" + getPara(1));
		} else {
			renderJson("{status:false}");
		}
	}

	/*
	 * activity 新增活动 url:/admin/newActivity
	 */
	public void newActivity() {
	}

	/*
	 * activity 添加activity url:/admin/saveActivity/
	 */
	public void saveActivity() {
		Activity activity = getModel(Activity.class);
		if (activityService.saveActivity(activity)) {
			redirect("/admin/getActivities/");
		} else {
			renderJson("{status:false}");
		}
	}

	/*
	 * activity 得到activity详情 url:/admin/getActivityDetail/
	 */
	public void getActivityDetail() {
		setAttr("activity", activityService.getActivity(getParaToInt()));
		setAttr("activityUsers",
				activityUsersService.getUsersByActivity(getParaToInt()));
		render("activityDetail.html");
	}

	/*
	 * book 分页得到book列表 url:/admin/getBooks/pageNum-typeNumId
	 * pageNum>0,typeNumId输入0-所有
	 */
	public void getBooks() {
		Page<Book> bookPage = bookService.getBooks(getPara(1) == null ? 0
				: getParaToInt(1), getPara(0) == null ? 1 : getParaToInt(0));
		setAttr("books", bookPage.getList());
		setAttr("totalPage", bookPage.getTotalPage());
		setAttr("current", bookPage.getPageNumber());
		setAttr("totalRaw", bookPage.getTotalRow());
		render("book.html");
	}

	// TODO 最后统一改为json数据传输，即使用Ajax异步操作，实践！！！
	/*
	 * book 删除book url:/admin/delBook/idNum-pageNum
	 */
	public void delBook() {
		if (bookService.delBook(getParaToInt(0))) {
			redirect("/admin/getBooks/" + getPara(1));
		} else {
			renderJson("{status:false}");
		}
	}

	/*
	 * book 新增图书 url:/admin/newBook
	 */
	public void newBook() {
		setAttr("types", bookService.getAllTypes());
	}

	/*
	 * book 保存图书 url:/admin/saveBook
	 */
	public void saveBook() {
		UploadFile file = getFile("book.picture", "book", 1024 * 1024 * 2);
		UploadFile file2 = getFile("book.url", "book", 1024 * 1024 * 5);
		Book book = getModel(Book.class);
		book.set("picture", "upload\\book\\" + file.getFileName());
		book.set("url", "upload\\book\\" + file2.getFileName());
		book.set("descri", book.getStr("descri").replaceAll("\r\n", "<br>"));
		book.save();
		redirect("/admin/getBooks/");
	}

	/*
	 * book 查看book基本信息 url:/admin/getBookDetail/idNum
	 */
	public void getBookDetail() {
		Book book = bookService.getBook(getParaToInt());
		setAttr("book", book);
		setAttr("comments", commentService.getCommentByBookId(getParaToInt()));
		render("bookDetail.html");
	}

	/*
	 * type 查看所有书籍类型 url:/admin/getTypes
	 */
	public void getTypes() {
		List<Type> typeList = typeService.getTypes();
		setAttr("types", typeList);
		render("type.html");
	}

	/*
	 * type 根据id删除类型 url:/admin/delType/idNum
	 */
	public void delType() {
		try {
			typeService.delType(getParaToInt());
		} catch (Exception e) {
			renderText("1");
			return;
		}
		// redirect("/admin/getTypes");
	}

	/*
	 * type 添加类型 url:/admin/saveType/TypeStr
	 */
	public void saveType() {
		Type type = getModel(Type.class);
		if (typeService.saveType(type)) {
			renderText("1");
		} else {
			renderText("0");
		}
	}
}
