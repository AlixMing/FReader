package com.reader.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.spring.Inject.BY_NAME;
import com.reader.model.Activity;
import com.reader.model.Book;
import com.reader.model.BookUsers;
import com.reader.model.Comments;
import com.reader.model.Timeline;
import com.reader.model.User;
import com.reader.service.interfaces.IActivityService;
import com.reader.service.interfaces.IActivityUsersService;
import com.reader.service.interfaces.IBookService;
import com.reader.service.interfaces.IBookUsersService;
import com.reader.service.interfaces.ICommentService;
import com.reader.service.interfaces.ITimelineService;
import com.reader.service.interfaces.IUserService;
import com.reader.util.MD5;

public class AppController extends Controller {
	@BY_NAME
	private ICommentService commentService;
	@BY_NAME
	private IUserService userService;
	@BY_NAME
	private IActivityService activityService;
	@BY_NAME
	private IActivityUsersService activityUsersService;
	@BY_NAME
	private ITimelineService timelineService;
	@BY_NAME
	private IBookUsersService bookUsersService;
	@BY_NAME
	private IBookService bookService;
	
	//评论
	/*
	 * 评论comment
	 * 根据书籍id按评论点赞数获取评论
	 * url:/app/getComment/bookId
	 * 返回值：List<Comments> comments //可为空
	 */
	public void getComment(){
		List<Comments> commentList = commentService.getCommentByBookId(getParaToInt());
		renderJson("comments", commentList);
	}
	
	/*
	 * 评论comment
	 * 添加评论
	 * url:/app/saveComment/Comments.class
	 * 返回值：Boolean status  //false 添加失败，true 添加成功
	 */
	//TODO setUserid
	public void saveComments(){
		Comments comment = getModel(Comments.class).set("createTime", new Date());
		renderJson("status", commentService.saveComment(comment));
	}
	
	/*
	 * 评论comment
	 * 评论点赞
	 * url:/app/praiseComment/commentId
	 * 返回值：Boolean status //false 点赞失败，true 点赞成功
	 */
	public void praiseComment(){
		renderJson("status", commentService.praiseComment(getParaToInt()));
	}
	//评论结束
	
	//用户
	/*
	 * 用户user
	 * 用户注册
	 * url:/app/saveUser/User.class
	 * 返回值：Boolean status //false 注册失败，true 注册成功
	 */
	//TODO 是否把用户保存session，头像保存本地
	public void saveUser(){
		User user = getModel(User.class);
		user.set("picture", "upload\\user\\4.jpg"); //设置默认头像
		user.set("password", MD5.UseMD5(user.getStr("name") + user.getStr("password")));
		renderJson("status", userService.saveUser(user));
	}
	
	/*
	 * 用户user
	 * 用户登录
	 * url:/app/login/User.class
	 * 返回值：Boolean status //false 登录失败，true 登录成功
	 */
	//TODO 用户信息如何保存
	public void login(){
		User user = userService.login(2, getModel(User.class));
		setSessionAttr("user", user);
		if(user == null)
			renderJson("status", false);
		else 
			renderJson("status", true);
	}
	//用户结束
	
	/*
	 * 活动activity
	 * 发起活动
	 * url:/app/saveActivity/Activity.class
	 * 返回值：Boolean status //false 发起失败，true 发起成功
	 */
	public void saveActivity(){
		renderJson("status", activityService.saveActivity(getModel(Activity.class)));
	}
	
	/*
	 * 活动activity
	 * 删除活动
	 * url:/app/delActivity/activityId
	 * 返回值：Boolean status //false 删除失败，true 删除成功
	 */
	public void delActivity(){
		renderJson("status", activityService.delActivity(getParaToInt()));
	}
	
	/*
	 * 活动activity
	 *	 活动报名
	 * url:/app/signUp/activityId
	 * 返回值：Boolean status //false 报名失败，true 报名成功
	 */
	public void signUp(){
		User user = getSessionAttr("user"); //从session中直接获取该报名用户信息
		renderJson("status", activityUsersService.saveActivityUsers(user.getInt("id"), getParaToInt()));
	}
	
	/*
	 * 活动activity
	 * 查看活动参与人
	 * url:/app/getActivityMenber/activityId
	 * 返回值：List<User> Users //得到用户名activityUser.getUser().name
	 */
	public void getActivityMenber(){
		renderJson("Users", activityUsersService.getUsersByActivity(getParaToInt()));
	}
	
	/*
	 * 活动activity
	 * 查看发起的所有活动
	 * url:/app/getMyActivity/
	 * 返回值：List<Activity> activities
	 */
	public void getMyActivity(){
		User user = getSessionAttr("user");
		renderJson("activities", activityService.getActivityByUser(user.getInt("id")));
	}
	
	/*
	 * 活动activity
	 * 查看报名的所有活动
	 * url:/app/getJoinedActivity/
	 * 返回值：List<Activity> activities //得到用户名activities.getUser().name
	 */
	public void getJoinedActivity(){
		List<Activity> activities = new ArrayList<Activity>();
		User user = getSessionAttr("user");
		activities = activityUsersService.getActivityUsersByUser(user.getInt("id"));
		System.out.println(activities.size());
		renderJson("activities", activities);
	}
	
	/*
	 * 活动activity
	 * 所有活动列表(分页)
	 * url:/app/getAllActivity/pageNum 
	 * 返回值：List<Activity> activities
	 */
	public void getAllActivity(){
		renderJson("activities", activityService.getActivities(getParaToInt()).getList());
	}
	
	/*
	 * 活动activity
	 * 修改活动
	 * url:/app/updateActivity/Activity.class
	 * 返回值：Boolean status //false 修改失败，true 修改成功
	 */
	public void updateActivtiy(){
		Activity activity = getModel(Activity.class);
		renderJson("status", activityService.updateActivity(activity));
	}
	//活动结束
	
	//时间线
	/*
	 * 时间线timeline
	 * 查看时间线
	 * url:/app/viewTimeline/
	 * 返回值：List<Timeline> timelines
	 */
	public void viewTimeline(){
		User user = getSessionAttr("user");
		renderJson("timelines", timelineService.getTimelines(user.getInt("id")));
	}
	
	/*
	 * 时间线timeline
	 * 添加时间线
	 * url：/app/saveTimeline/timeline.class(timeline.time=xxx&timeline.content=xxx)
	 * 返回值：Boolean status //false 添加失败，true 添加成功
	 */
	public void saveTimeline() {
		User user = getSessionAttr("user");
		Timeline timeline = getModel(Timeline.class);
		timeline.set("userId", user.getInt("id"));
		renderJson("status", timelineService.saveTimeline(timeline));
	}
	//时间线结束
	
	//书籍管理
	/*
	 * 书籍book
	 * 添加书籍
	 * url：/app/saveUserBook/bookId
	 * 返回值：Boolean status
	 */
	public void saveUserBook() {
		User user = getSessionAttr("user");
		Book book = bookService.getBook(getParaToInt());
		BookUsers bookUsers = new BookUsers().set("userId", user.getInt("id")).set("bookId", getParaToInt()).set("url", book.getStr("url"));
		renderJson("status", bookUsersService.saveBookUsers(bookUsers));
	}
	
	/*
	 * 书籍book
	 * 更新书籍信息(读书进度，是否本地删除)
	 * url:/app/updateUserBook/BookUser.class //或者json数据
	 * 返回值：Boolean status
	 */
	public void updateUserBook() {
		BookUsers bookUsers = getModel(BookUsers.class);
		renderJson("status", bookUsersService.updateBookUsers(bookUsers));
	}
	
	/*
	 * 书籍book
	 * 获取下载书籍列表
	 * url：/app/getDownloadBooks
	 * 返回值：{bookList:List<Book>}
	 */
	public void getDownloadBooks() {
		List<Book> bookList = new ArrayList<Book>();
		User user = getSessionAttr("user");
		System.out.println(user.getStr("name"));
		bookList = bookUsersService.getBooks(user.getInt("id"));
		System.out.println(bookList.size());
		renderJson("bookList", bookList);
	}
	
	/*
	 * 书籍book
	 * 分页查看可下载的所有书籍
	 * url：/app/getAllBooks/pageNum
	 * 返回值：{bookList:List<Book>}
	 */
	public void getAllBooks() {
		renderJson("bookList", bookService.getBooks(0, getParaToInt()).getList());
	}
	/*
	 * 书籍book
	 * 查看推荐书籍
	 */
	//书籍管理结束
	//推荐
	/*
	 * 
	 */
	//推荐结束
}
