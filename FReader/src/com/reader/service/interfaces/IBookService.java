package com.reader.service.interfaces;

import java.util.List;
import com.jfinal.plugin.activerecord.Page;
import com.reader.model.Book;
import com.reader.model.Type;

public interface IBookService {
	Page<Book> getBooks(int typeId, int pageNumber);
	Book getBook(int bookId);
	Book download(int bookId);
	List<Type> getAllTypes();
	boolean delBook(int id);
	boolean updateBook(Book book);
	boolean saveBook(Book book);
}
