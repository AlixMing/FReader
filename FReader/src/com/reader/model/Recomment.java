package com.reader.model;
/*
 * recomment model
 * mysql> desc recomment;
+--------+--------------+------+-----+---------+----------------+
| Field  | Type         | Null | Key | Default | Extra          |
+--------+--------------+------+-----+---------+----------------+
| id     | int(11)      | NO   | PRI | NULL    | auto_increment |
| userId | int(11)      | YES  | MUL | NULL    |                |
| bookId | int(11)      | YES  | MUL | NULL    |                |
| reason | varchar(256) | YES  |     | NULL    |                |
+--------+--------------+------+-----+---------+----------------+
 */
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Recomment extends Model<Recomment> {
	public static final Recomment me = new Recomment();
}
