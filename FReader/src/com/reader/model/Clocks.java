package com.reader.model;
/*
 * clocks model
 * mysql> desc clocks;
+--------+---------------------------+------+-----+---------+-------+
| Field  | Type                      | Null | Key | Default | Extra |
+--------+---------------------------+------+-----+---------+-------+
| id     | int(10) unsigned zerofill | NO   | PRI | NULL    |       |
| userId | int(11)                   | YES  | MUL | NULL    |       |
| time   | datetime                  | YES  |     | NULL    |       |
| isUse  | int(10) unsigned zerofill | YES  |     | NULL    |       |
+--------+---------------------------+------+-----+---------+-------+
 */
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Clocks extends Model<Clocks> {
	public static final Clocks me = new Clocks();
}
