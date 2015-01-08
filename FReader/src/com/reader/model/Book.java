package com.reader.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/*
 * book model
 mysql> desc book;
+--------+--------------+------+-----+---------+----------------+
| Field  | Type         | Null | Key | Default | Extra          |
+--------+--------------+------+-----+---------+----------------+
| id     | int(11)      | NO   | PRI | NULL    | auto_increment |
| name   | varchar(20)  | NO   |     | NULL    |                |
| author | varchar(20)  | YES  |     | NULL    |                |
| desc   | varchar(256) | YES  |     | NULL    |                |
| typeId | int(11)      | YES  | MUL | NULL    |                |
+--------+--------------+------+-----+---------+----------------+
 */
@SuppressWarnings("serial")
public class Book extends Model<Book> {
	public static final Book me = new Book();
	
	public Type getType(){
		return Type.me.findById(get("typeId"));
	}
	
	public Page<Book> paginate(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select *", "from book order by id asc");
	}
}
