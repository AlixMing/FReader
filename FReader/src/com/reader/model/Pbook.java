package com.reader.model;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Pbook extends Model<Pbook> {
	public static final Pbook me = new Pbook();
	
	public Book getBook(){
		return Book.me.findById(getInt("bookId"));
	}
}
