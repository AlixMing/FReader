package com.reader.service.impl;

import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Page;
import com.reader.model.Activity;
import com.reader.model.User;
import com.reader.service.interfaces.IActivityService;

@Service
public class ActivityService implements IActivityService {

	public Page<Activity> getActivities(int pageNumber) {
		int totalPage = User.me.paginate(pageNumber, 8).getTotalPage();
		if(totalPage < pageNumber){
			return Activity.me.paginate(totalPage, 8);
		}
		else {
			return Activity.me.paginate(pageNumber, 8);
		}
	}

	public boolean delActivity(int id) {
		return Activity.me.deleteById(id);
	}

	public boolean updateActivity(Activity activity) {
		return activity.update();
	}

	public boolean saveActivity(Activity activity) {
		return activity.save();
	}
}
