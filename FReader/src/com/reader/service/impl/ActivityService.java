package com.reader.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Page;
import com.reader.model.Activity;
import com.reader.model.ActivityUsers;
import com.reader.model.User;
import com.reader.service.interfaces.IActivityService;

@Service
public class ActivityService implements IActivityService {

	public Page<Activity> getActivities(int pageNumber) {
		int totalPage = User.me.paginate(pageNumber, 8).getTotalPage();
		if (totalPage < pageNumber) {
			return Activity.me.paginate(totalPage, 8);
		} else {
			return Activity.me.paginate(pageNumber, 8);
		}
	}

	public boolean delActivity(int id) {
		try {
			Activity.me.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		if (new ActivityUsersService().delActivityUsersByAId(id))
			return true;
		else
			return false;
	}

	public boolean updateActivity(Activity activity) {
		return activity.update();
	}

	public boolean saveActivity(Activity activity) {
		return activity.save();
	}

	public Activity getActivity(int id) {
		return Activity.me.findById(id);
	}

	public List<Activity> getActivityByUser(int userId) {
		return Activity.me.find("select * from activity where userId = "
				+ userId);
	}
}
