package com.reader.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.reader.model.Book;
import com.reader.model.BookUsers;
import com.reader.service.interfaces.IBookUsersService;

@Service
public class BookUsersService implements IBookUsersService {

	public Boolean saveBookUsers(BookUsers bookUsers) {
		return bookUsers.save();
	}

	public Boolean updateBookUsers(BookUsers bookUsers) {
		return bookUsers.update();
	}

	public List<Book> getBooks(int userId) {
		List<BookUsers> bookUsersList = BookUsers.me.find("select * from book_user where userId = ?", userId);
		List<Book> books = new ArrayList<Book>();
		for (BookUsers bookUsers : bookUsersList) {
			books.add(Book.me.findById(bookUsers.getInt("bookId")));
		}
		return books;
	}

}
