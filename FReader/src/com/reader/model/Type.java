package com.reader.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
/*
 * type model
 mysql> desc type;
+-------+-------------+------+-----+---------+----------------+
| Field | Type        | Null | Key | Default | Extra          |
+-------+-------------+------+-----+---------+----------------+
| id    | int(11)     | NO   | PRI | NULL    | auto_increment |
| name  | varchar(20) | NO   |     | NULL    |                |
+-------+-------------+------+-----+---------+----------------+
 */
@SuppressWarnings("serial")
public class Type extends Model<Type> {
	public static final Type me = new Type();
	
	public List<Book> getBooks(){
		return Book.me.find("select * from book where typeId = ? order by praise desc", get("id"));
	}
	
	public List<Type> getAllTypes(){
		return Type.me.find("select * from type");
	}
}
