package com.reader.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jfinal.plugin.activerecord.Page;
import com.reader.model.User;
import com.reader.service.interfaces.IUserService;
import com.reader.util.MD5;

@Service
public class UserService implements IUserService {

	public Page<User> getUsers(int pageNumber) {
		int totalPage = User.me.paginate(pageNumber, 8).getTotalPage();
		if (totalPage < pageNumber) {
			return User.me.paginate(totalPage, 8);
		} else {
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

	public User login(int loginType, User user) {
		user.set("password",
				MD5.UseMD5(user.getStr("name") + user.getStr("password"))); // 使用用户名+密码作为密码加密明文
		List<User> userComfirm = User.me.getByName(user.getStr("name"));
		if (loginType == 1)
			for (User user2 : userComfirm) {
				if (user2.get("password").equals(user.get("password"))
						&& (user2.getInt("level") == 1)) {
					return user2;
				}
			}
		else {
			for (User user2 : userComfirm) {
				if(user2.get("password").equals(user.get("password")) && (user2.getInt("level") == 2)){
					return user2;
				}
			}
		}
		return null;
	}
}
