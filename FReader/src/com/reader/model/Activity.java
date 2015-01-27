package com.reader.model;

/*
 * activity model;
 * mysql> desc activity;
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| id        | int(11)      | NO   | PRI | NULL    | auto_increment |
| name      | varchar(64)  | YES  |     | NULL    |                |
| begintime | datetime     | YES  |     | NULL    |                |
| endtime   | datetime     | YES  |     | NULL    |                |
| content   | varchar(256) | YES  |     | NULL    |                |
| address   | varchar(256) | YES  |     | NULL    |                |
| userId    | int(11)      | YES  | MUL | NULL    |                |
+-----------+--------------+------+-----+---------+----------------+
 */
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Activity extends Model<Activity> {
	public static final Activity me = new Activity();
	
	public User getUser() {
		return User.me.findById(get("userId"));
	}
	
	public Page<Activity> paginate(int pageNumber, int pageSize){
		return paginate(pageNumber, pageSize, "select *", "from activity order by id asc");
	}
	
	public Page<Activity> paginate(int pageNumber, int pageSize, String search) {
		return Activity.me.paginate(pageNumber, 8, "select *", "from activity where name like '%" + search + "%' or content like '%" + search + "%'" );
	}
}
