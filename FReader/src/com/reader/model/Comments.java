package com.reader.model;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Comments extends Model<Comments> {
	public static final Comments me = new Comments();
	
	public User getUser() {
		return User.me.findById(get("userId"));
	}
}
