package com.reader.service.interfaces;

import com.jfinal.plugin.activerecord.Page;
import com.reader.model.Book;

public interface IBookService {
	Page<Book> getBooks(int typeId, int pageNumber);
	Book getBook(int bookId);
	boolean delBook(int id);
	boolean updateBook(Book book);
	boolean saveBook(Book book);
}
