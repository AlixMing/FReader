package com.reader.service.impl;

import com.jfinal.plugin.activerecord.Page;
import com.reader.model.User;
import com.reader.service.interfaces.IUserService;

public class UserService implements IUserService {

	public Page<User> getUsers(int pageNumber) {
		int totalPage = User.me.paginate(pageNumber, 8).getTotalPage();
		if(totalPage < pageNumber){
			return User.me.paginate(totalPage, 8);
		}
		else {
			return User.me.paginate(pageNumber, 8);
		}
	}

	public boolean delUser(int id) {
		return User.me.deleteById(id);
	}

	public boolean updateUser(User user) {
		return user.update();
	}

	public boolean saveUser(User user) {
		return user.save();
	}

}
