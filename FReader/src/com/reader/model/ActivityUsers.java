package com.reader.model;
/*
 * activityUsers model
 * mysql> desc activity_user;
+------------+---------+------+-----+---------+----------------+
| Field      | Type    | Null | Key | Default | Extra          |
+------------+---------+------+-----+---------+----------------+
| id         | int(11) | NO   | PRI | NULL    | auto_increment |
| activityId | int(11) | YES  | MUL | NULL    |                |
| userId     | int(11) | YES  | MUL | NULL    |                |
+------------+---------+------+-----+---------+----------------+
 */
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class ActivityUsers extends Model<ActivityUsers> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final ActivityUsers me = new ActivityUsers();
	
	public List<ActivityUsers> getActivityByUserId(int id){
			return ActivityUsers.me.find("select * from activity_user where userId = " + id);
	}
	
	public List<ActivityUsers> getUserByActivityId(int id){
		return ActivityUsers.me.find("select * from activity_user where activityId = " + id);
}
	
	public Activity getActivity(){
		return Activity.me.findById(get("activityId"));
	}
	
	public User getUser(){
		return User.me.findById(get("userId"));
	}
}
