package com.reader.model;

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
}
