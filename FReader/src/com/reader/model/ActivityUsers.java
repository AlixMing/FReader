package com.reader.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.sun.accessibility.internal.resources.accessibility;

public class ActivityUsers extends Model<ActivityUsers> {
	public static final ActivityUsers me = new ActivityUsers();
	
	public List<ActivityUsers> getActivityByUserId(int id){
			return ActivityUsers.me.find("select * from activity_user where userId = " + id);
	}
	
	public Activity getActivity(){
		return Activity.me.findById(get("activityId"));
	}
	
	public User getUser(){
		return User.me.findById(get("userId"));
	}
}
