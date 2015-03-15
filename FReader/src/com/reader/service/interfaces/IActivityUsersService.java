package com.reader.service.interfaces;

import java.util.List;

import com.reader.model.Activity;
import com.reader.model.User;

public interface IActivityUsersService {
	List<Activity> getActivityUsersByUser(int id);
	List<User> getUsersByActivity(int id);
	boolean saveActivityUsers(int userId, int activityId);
	boolean delActivityUsers(int userId, int activityId);
	boolean delActivityUsersByAId(int activityId);
}
