package com.reader.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.reader.model.Activity;
import com.reader.model.ActivityUsers;
import com.reader.model.User;
import com.reader.service.interfaces.IActivityUsersService;

@Service
public class ActivityUsersService implements IActivityUsersService {

	public List<Activity> getActivityUsersByUser(int id) {
		List<ActivityUsers> activityUsersList = ActivityUsers.me.getActivityByUserId(id);
		List<Activity> activities = new ArrayList<Activity>();
		for (ActivityUsers activityUser : activityUsersList) {
			activities.add(Activity.me.findById(activityUser.getInt("activityId")));
		}
		return activities;
	}

	public List<User> getUsersByActivity(int id) {
		List<ActivityUsers> activityUsersList = ActivityUsers.me.getUserByActivityId(id);
		List<User> users = new ArrayList<User>();
		for (ActivityUsers activityUser : activityUsersList) {
			users.add(User.me.findById(activityUser.getInt("userId")));
		}
		return users;
	}

	public boolean saveActivityUsers(int userId, int activityId){
		if(ActivityUsers.me.checkIsExist(activityId, userId)){
			ActivityUsers activityUsers = new ActivityUsers().set("userId", userId).set("activityId", activityId);
			return activityUsers.save();
		}
		else {
			return false;
		}
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
