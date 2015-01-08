package com.reader.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.reader.model.Blog;

public class DemoController extends Controller {

	@ClearInterceptor(ClearLayer.ALL)
	public void index(){
		Blog blog1 = new Blog().set("title", "test").set("content", "test_content").set("userId", 4);
		Blog blog2 = new Blog().set("title", "hello").set("content", "hello_content").set("userId", 4);
		List<Blog> blogList = new ArrayList<Blog>();
		blogList.add(blog1);
		blogList.add(blog2);
		renderJson(blogList);
}
	
	@ClearInterceptor
	public void saveBlog(){
		Record blog = new Record().set("title", "blog2").set("content", "this is save in record way");
		Db.save("blog", blog);
		renderText("............");
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void delBlog(){
		List<Blog> blogs = Blog.me.find("select * from blog order by id desc limit 3");
		Blog.me.deleteById(blogs.get(0).get("id"));
		renderText("............");
	}
	
	@ClearInterceptor
	public void selectBlog(){
		List<Blog> blogs = Blog.me.find("select * from blog order by id limit 3");
		String string = "";
		for (Blog blog : blogs) {
			string += blog.getStr("title");
			string += " and ";
		}
		renderText("............" + string);
	}
	
	//save update一般使用事务管理
	@ClearInterceptor
	@Before(Tx.class) 
	public void updateBlog(){
		Record blogRecord = Db.findById("blog", 1).set("title", "update one");
		renderText("............" + blogRecord.getStr("title"));
		Db.update("blog", blogRecord);
	}
	
}
