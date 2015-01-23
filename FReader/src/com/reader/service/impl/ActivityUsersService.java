package com.reader.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.reader.model.ActivityUsers;
import com.reader.service.interfaces.IActivityUsersService;

@Service
public class ActivityUsersService implements IActivityUsersService {

	public List<ActivityUsers> getActivityUsersByUser(int id) {
		return ActivityUsers.me.getActivityByUserId(id);
	}

	public List<ActivityUsers> getUsersByActivity(int id) {
		return ActivityUsers.me.getUserByActivityId(id);
	}

	public boolean saveActivityUsers(int userId, int activityId){
		ActivityUsers activityUsers = new ActivityUsers().set("userId", userId).set("activityId", activityId);
		return activityUsers.save();
	}

	public boolean delActivityUsers(int userId, int activityId) {
		try {
			List<ActivityUsers> activityUsers = ActivityUsers.me.find("select * from activity_user where userId = " +  userId + " and activityId = " + activityId);
			for (ActivityUsers activityUsers2 : activityUsers) {
				activityUsers2.delete();
			}
		} catch (Exception e) {
			return false;
		}
 		return true;
	}

	public boolean delActivityUsersByAId(int activityId) {
		try {
			List<ActivityUsers> activityUsers = ActivityUsers.me.find("select * from activity_user where activityId = " + activityId);
			for (ActivityUsers activityUsers2 : activityUsers) {
				activityUsers2.delete();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
