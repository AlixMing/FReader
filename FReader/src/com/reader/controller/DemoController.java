package com.reader.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.spring.IocInterceptor;
import com.jfinal.upload.UploadFile;
import com.reader.model.Blog;
import com.reader.model.Book;
import com.reader.model.User;

@Before(IocInterceptor.class)
public class DemoController extends Controller {

	@ClearInterceptor(ClearLayer.ALL)
	public void index() {
		Blog blog1 = new Blog().set("title", "test")
				.set("content", "test_content").set("userId", 4);
		Blog blog2 = new Blog().set("title", "hello")
				.set("content", "hello_content").set("userId", 4);
		List<Blog> blogList = new ArrayList<Blog>();
		blogList.add(blog1);
		blogList.add(blog2);
		renderJson(blogList);
		render("upload.html");
	}

	@ClearInterceptor
	public void saveBlog() {
		Record blog = new Record().set("title", "blog2").set("content",
				"this is save in record way");
		Db.save("blog", blog);
		renderText("............");
	}

	@ClearInterceptor(ClearLayer.ALL)
	public void delBlog() {
		List<Blog> blogs = Blog.me
				.find("select * from blog order by id desc limit 3");
		Blog.me.deleteById(blogs.get(0).get("id"));
		renderText("............");
	}

	@ClearInterceptor
	public void selectBlog() {
		List<Blog> blogs = Blog.me
				.find("select * from blog order by id limit 3");
		String string = "";
		for (Blog blog : blogs) {
			string += blog.getStr("title");
			string += " and ";
		}
		renderText("............" + string);
	}

	// save update一般使用事务管理
	@ClearInterceptor
	@Before(Tx.class)
	public void updateBlog() {
		Record blogRecord = Db.findById("blog", 1).set("title", "update one");
		renderText("............" + blogRecord.getStr("title"));
		Db.update("blog", blogRecord);
	}

	// 上传user文件测试
	public void saveFile() {
		UploadFile file = getFile("user.picture", "user", 1024 * 1024 * 2);
		User user = getModel(User.class);
		user.set("picture", "upload\\user\\" + file.getFileName());
		user.save();
		renderText(file.getFileName() + " ..... " + file.getSaveDirectory()
				+ "--------" + user.getStr("name"));
	}
	
	// 上传book文件测试
	public void saveBookFile() {
		UploadFile file = getFile("book.picture", "book", 1024 * 1024 * 2);
		UploadFile file2 = getFile("book.url", "book", 1024 * 1024 * 5);
		Book book = getModel(Book.class);
		book.set("picture", "upload\\book\\" + file.getFileName());
		book.set("url", "upload\\book\\" + file2.getFileName());
		book.save();
		renderText(file.getFileName() + " - " + file.getSaveDirectory()
				+ "......." + book.getStr("name") + "......" + file2.getFileName() + " ..... " + file2.getSaveDirectory());
	}
	
	// 读取文件测试
	public void readFile() throws Exception {
		User user = User.me.findById(20);
		//ok1
		//OutputStream out = new FileOutputStream(new File("C:\\Users\\y\\Desktop\\temp\\pictures\\1.jpg"));
		//renderFile(new File("C:\\Users\\y\\Desktop\\temp\\pictures\\1.jpg"));
		//ok2
		//OutputStream out = new FileOutputStream(new File(getRequest().getSession().getServletContext().getRealPath("/") + "/" + user.getStr("picture").replace("\\", "/")));
		//renderFile(new File(getRequest().getSession().getServletContext().getRealPath("/") + "/" + user.getStr("picture").replace("\\", "/")));
		//ok3
		OutputStream out = new FileOutputStream(new File(JFinal.me().getServletContext().getRealPath("/") + "/" + user.getStr("picture").replace("\\", "/")));
		renderFile(new File(JFinal.me().getServletContext().getRealPath("/") + "/" + user.getStr("picture").replace("\\", "/")));
	}
	
	public void download(){
		Book  book = Book.me.findById(getParaToInt());
		renderFile(new File(JFinal.me().getServletContext().getRealPath("/") + "/" + book.getStr("url").replace("\\", "/")));
	}
}
