package com.reader.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
/*
 * book model
mysql> desc book;
+----------+---------------------------+------+-----+---------+----------------+
| Field    | Type                      | Null | Key | Default | Extra          |
+----------+---------------------------+------+-----+---------+----------------+
| id       | int(11)                   | NO   | PRI | NULL    | auto_increment |
| name     | varchar(20)               | NO   |     | NULL    |                |
| author   | varchar(20)               | YES  |     | NULL    |                |
| picture  | varchar(64)               | YES  |     | NULL    |                |
| descri   | varchar(256)              | YES  |     | NULL    |                |
| download | int(10) unsigned zerofill | YES  |     | NULL    |                |
| praise   | int(10) unsigned zerofill | YES  |     | NULL    |                |
| typeId   | int(11)                   | YES  | MUL | NULL    |                |
+----------+---------------------------+------+-----+---------+----------------+
 */
@SuppressWarnings("serial")
public class Book extends Model<Book> {
	public static final Book me = new Book();
	
	public Type getType(){
		return Type.me.findById(get("typeId"));
	}
	
	public Page<Book> paginate(int typeId, int pageNumber, int pageSize){
		if (typeId == 0) {
			return paginate(pageNumber, pageSize, "select *", "from book order by id asc");
		}else {
			return paginate(pageNumber, pageSize, "select *", "from book where typeId = ? order by id asc", typeId);
		}
	}
}
