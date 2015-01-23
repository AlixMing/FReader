package com.reader.service.interfaces;

import java.util.List;

import com.reader.model.ActivityUsers;

public interface IActivityUsersService {
	List<ActivityUsers> getActivityUsersByUser(int id);
	List<ActivityUsers> getUsersByActivity(int id);
	boolean saveActivityUsers(int userId, int activityId);
	boolean delActivityUsers(int userId, int activityId);
	boolean delActivityUsersByAId(int activityId);
}
