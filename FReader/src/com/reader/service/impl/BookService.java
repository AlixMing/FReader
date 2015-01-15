package com.reader.service.impl;

import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Page;
import com.reader.model.Book;
import com.reader.service.interfaces.IBookService;

@Service
public class BookService implements IBookService {

	public Page<Book> getBooks(int typeId,int pageNumber) {
		return Book.me.paginate(typeId, pageNumber, 5);
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

	public Book getBook(int bookId) {
		return Book.me.findById(bookId);
	}

}
