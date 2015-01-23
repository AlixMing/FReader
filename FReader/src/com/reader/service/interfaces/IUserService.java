package com.reader.service.interfaces;

import com.jfinal.plugin.activerecord.Page;
import com.reader.model.User;

public interface IUserService {
	Page<User> getUsers(int pageNumber);
	boolean delUser(int id);
	boolean updateUser(User user);
	boolean saveUser(User user);
	User login(int loginType, User user);
}
