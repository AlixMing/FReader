package com.reader.service.impl;

import com.jfinal.plugin.activerecord.Page;
import com.reader.model.Book;
import com.reader.service.interfaces.IBookService;

public class BookService implements IBookService {

	public Page<Book> getBooks(int pageNumber) {
		return Book.me.paginate(pageNumber, 10);
	}

	public boolean delBook(int id) {
		return Book.me.deleteById(id);
	}

	public boolean updateBook(Book book) {
		return book.update();
	}

	public boolean saveBook(Book book) {
		return book.save();
	}

}
