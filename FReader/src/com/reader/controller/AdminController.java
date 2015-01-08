package com.reader.controller;

import com.jfinal.core.Controller;
import com.reader.model.User;
import com.reader.service.impl.UserService;
import com.reader.service.interfaces.IUserService;

public class AdminController extends Controller {
	
	private IUserService userService = new UserService();
	
	public void index(){
		render("admin.jsp");
}
	/*
	 *分页得到user列表
	 *url:/admin/getUser/pageNum>0 
	 */
	public void getUser(){
		renderJson("userList",userService.getUsers(getParaToInt()).getList());
 	}
	
	/*
	 * 删除user
	 * url:/admin/delUser/idNum 
	 */
	public void delUser(){
		renderJson("{status:" + userService.delUser(getParaToInt()) + "}");
	}
	
	//TODO spring plugin 完成后修改是否可以直接通过ioc传入user
	/*
	 * 保存user
	 * url:/admin/saveUser/userName-userPassword
	 */
	public void saveUser(){
		User user = new User().set("name", getPara(0)).set("password", getPara(1));
		renderJson("{status:" + userService.saveUser(user) +"}");
	}

	/*
	 * 更新user信息
	 * url:/admin/updateUser/userId-userName-userPassword 
	 */
	public void updateUser(){
		User user = User.me.findById(getPara(0));
		user.set("name", getPara(1)).set("password", getPara(2));
		renderJson("{status:" + userService.updateUser(user) + "}");
	}
}
