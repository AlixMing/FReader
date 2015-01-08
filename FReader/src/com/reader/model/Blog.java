package com.reader.model;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/*
 * blog model;
  mysql> desc blog;
+---------+--------------+------+-----+---------+----------------+
| Field   | Type         | Null | Key | Default | Extra          |
+---------+--------------+------+-----+---------+----------------+
| id      | int(11)      | NO   | PRI | NULL    | auto_increment |
| title   | varchar(200) | NO   |     | NULL    |                |
| content | mediumtext   | NO   |     | NULL    |                |
+---------+--------------+------+-----+---------+----------------+
 */
@SuppressWarnings("serial")
public class Blog extends Model<Blog> {
	public static final Blog me = new Blog();
	
	public User getUser() {
		return User.me.findById(get("userId"));
	}
	
	public Page<Blog> paginate(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select *", "from blog order by id asc");
	}
}
