package com.reader.model;
/*
 * timeline model
 * mysql> desc timeline;
+---------+--------------+------+-----+---------+----------------+
| Field   | Type         | Null | Key | Default | Extra          |
+---------+--------------+------+-----+---------+----------------+
| id      | int(10)      | NO   | PRI | NULL    | auto_increment |
| userId  | int(11)      | YES  | MUL | NULL    |                |
| time    | datetime     | YES  |     | NULL    |                |
| content | varchar(256) | YES  |     | NULL    |                |
+---------+--------------+------+-----+---------+----------------+
 */
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Timeline extends Model<Timeline> {
	public static final Timeline me = new Timeline();
}
