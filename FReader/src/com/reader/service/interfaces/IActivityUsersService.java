package com.reader.service.interfaces;

import java.util.List;

import com.reader.model.ActivityUsers;

public interface IActivityUsersService {
	List<ActivityUsers> getActivityUsersByUser(int id);
}
