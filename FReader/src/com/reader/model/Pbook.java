package com.reader.model;
/*
 * pbook model
 * mysql> desc pbook;
+----------+---------------------------+------+-----+---------+----------------+

| Field    | Type                      | Null | Key | Default | Extra          |
+----------+---------------------------+------+-----+---------+----------------+
| id       | int(11)                   | NO   | PRI | NULL    | auto_increment |
| userId   | int(11)                   | NO   | MUL | NULL    |                |
| bookId   | int(11)                   | YES  | MUL | NULL    |                |
| progress | int(10) unsigned zerofill | YES  |     | NULL    |                |
| isDelete | int(10) unsigned zerofill | YES  |     | NULL    |                |
| isLocal  | int(10) unsigned zerofill | YES  |     | NULL    |                |
| url      | varchar(255)              | YES  |     |         |                |
+----------+---------------------------+------+-----+---------+----------------+
 */
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Pbook extends Model<Pbook> {
	public static final Pbook me = new Pbook();
	
	public Book getBook(){
		return Book.me.findById(getInt("bookId"));
	}
}
