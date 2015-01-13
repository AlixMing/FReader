package com.reader.service.interfaces;

import com.jfinal.plugin.activerecord.Page;
import com.reader.model.Activity;

public interface IActivityService {
	Page<Activity> getActivities(int pageNumber);
	boolean delActivity(int id);
	boolean updateActivity(Activity activity);
	boolean saveActivity(Activity activity);
}
