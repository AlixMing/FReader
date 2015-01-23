package com.reader.model;
/*
 * comments model
 * mysql> desc comments;
+------------+---------------------------+------+-----+---------+---------------
| Field      | Type                      | Null | Key | Default | Extra|
+------------+---------------------------+------+-----+---------+----------------+
| id         | int(11)                   | NO   | PRI | NULL    | auto_increment|
| userId     | int(11)                   | YES  | MUL | NULL    |
| bookId     | int(11)                   | YES  | MUL | NULL    |
| content    | varchar(256)              | YES  |     | NULL    |
| praise     | int(10) unsigned zerofill | YES  |     | NULL    |
| isDelete   | int(10) unsigned zerofill | YES  |     | NULL    |
| createTime | datetime                  | YES  |     | NULL    |
+------------+---------------------------+------+-----+---------+---------------
 */
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Comments extends Model<Comments> {
	public static final Comments me = new Comments();
	
	public User getUser() {
		return User.me.findById(get("userId"));
	}
}
