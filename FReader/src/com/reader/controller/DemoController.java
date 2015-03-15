package com.reader.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.ext.render.excel.PoiRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.spring.Inject.BY_NAME;
import com.jfinal.plugin.spring.IocInterceptor;
import com.jfinal.upload.UploadFile;
import com.reader.model.Blog;
import com.reader.model.Book;
import com.reader.model.Type;
import com.reader.model.User;
import com.reader.service.interfaces.IBookService;

@Before(IocInterceptor.class)
public class DemoController extends Controller {
	@BY_NAME
	private IBookService bookService;

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
	/*
	 * 导出excel表格
	 */
	public void excelGen(){
		List<Type> conList = bookService.getAllTypes();
		PoiRender excel = new PoiRender(conList);
		String[] columns = {"id","name"};
		String[] headers = {"编号","姓名"};
		render(excel.sheetName("表1").headers(headers).columns(columns).fileName("类型表格.xls"));
	}
	
	/*
	 * 导入Excel表格
	 */
	public void excelRead(){
		List<Object> list = new ArrayList<Object>();
		  HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(new  File(JFinal.me().getServletContext().getRealPath("/") + "/" + "upload/bbb.xls")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HSSFSheet sheet = workbook.getSheet("aaa");
		  
		int row = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < row; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			HSSFRow row2 = sheet.getRow(i);
			map.put("id", row2.getCell(0).getStringCellValue());
			map.put("name", row2.getCell(1).getStringCellValue());
			System.out.println(map.get("id").toString() + map.get("name").toString());
			list.add(map);
		}
		renderJson(list);
	}
}
