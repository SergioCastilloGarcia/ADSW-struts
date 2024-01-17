package com.miw.persistence.book;

import java.util.List;

import com.miw.model.Book;

public interface BookDataService {

	List<Book> getBooks() throws Exception;
	Book updateBook(Book book)throws Exception;
	Book addBook(Book book)throws Exception;
	Book getBookById(int id)throws Exception;
}