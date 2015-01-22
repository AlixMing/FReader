package com.reader.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.reader.model.Activity;
import com.reader.model.ActivityUsers;
import com.reader.service.interfaces.IActivityUsersService;

@Service
public class ActivityUsersService implements IActivityUsersService {

	public List<ActivityUsers> getActivityUsersByUser(int id) {
		return ActivityUsers.me.getActivityByUserId(id);
	}

}
