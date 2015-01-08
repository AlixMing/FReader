package com.reader.controller;
import com.jfinal.core.Controller;

public class FrontController extends Controller {
	public void index(){
		render("index.jsp");
	}
	
	public void hurry(){
		if(getPara() == null)
			renderText("Hurry to Jfinal World!");
		else
			renderText(getPara() + "baby" + getPara(1));
	}
}
