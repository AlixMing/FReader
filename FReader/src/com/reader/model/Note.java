package com.reader.model;
/*
 * note model
 * mysql> desc note;
+---------+---------------------------+------+-----+---------+----------------+
| Field   | Type                      | Null | Key | Default | Extra          |
+---------+---------------------------+------+-----+---------+----------------+
| id      | int(11)                   | NO   | PRI | NULL    | auto_increment |
| pbookId | int(11)                   | YES  | MUL | NULL    |                |
| mark    | int(10) unsigned zerofill | YES  |     | NULL    |                |
| content | varchar(256)              | YES  |     | NULL    |                |
+---------+---------------------------+------+-----+---------+----------------+
 */
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Note extends Model<Note> {
	public static final Note me = new Note();
}
