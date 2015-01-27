package com.reader.service.interfaces;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.reader.model.Activity;
public interface IActivityService {
	Page<Activity> getActivities(int pageNumber);
	Page<Activity> findByName(String searchName, int pageNumber);
	List<Activity> getActivityByUser(int userId);
	Activity getActivity(int id);
	boolean delActivity(int id);
	boolean updateActivity(Activity activity);
	boolean saveActivity(Activity activity);
}
