package com.reader.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/*
 * user model
mysql> desc user;
+----------+-------------+------+-----+---------+----------------+
| Field    | Type        | Null | Key | Default | Extra          |
+----------+-------------+------+-----+---------+----------------+
| id       | int(11)     | NO   | PRI | NULL    | auto_increment |
| name     | varchar(20) | NO   |     | NULL    |                |
| password | varchar(64) | NO   |     | NULL    |                |
| picture  | varchar(64) | YES  |     | NULL    |                |
+----------+-------------+------+-----+---------+----------------+
 */
@SuppressWarnings("serial")
public class User extends Model<User> {
	public static final User me = new User();
	
	public List<Blog> getBlogs(){
		return Blog.me.find("select * from blog where userId = ?",get("id"));
	}
	
	public List<Pbook> getPBooks(){
		return Pbook.me.find("select * from pbook where userId = ?",get("id"));
	}
	
	public List<Activity> getActivities(){
		return Activity.me.find("select * from activity where userId = ?", get("id"));
	}
	
	public Page<User> paginate(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select *", "from user where level = 2 order by id asc");
	}
	
	public List<User> getByName(String name){
		return User.me.find("select * from user where name = ?", name);
	}
}
