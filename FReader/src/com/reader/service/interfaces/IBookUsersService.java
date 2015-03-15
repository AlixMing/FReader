package com.reader.service.interfaces;

import java.util.List;

import com.reader.model.Book;
import com.reader.model.BookUsers;

public interface IBookUsersService {
	public Boolean saveBookUsers(BookUsers bookUsers);

	public Boolean updateBookUsers(BookUsers bookUsers);

	public List<Book> getBooks(int userId);
}
