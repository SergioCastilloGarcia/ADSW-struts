package com.miw.business.bookmanager;

import java.util.List;

import com.miw.model.Book;

public interface BookManagerService {
	public List<Book> getBooks() throws Exception;
	public Book getSpecialOffer() throws Exception;
	public Book updateBook(Book book) throws Exception;
	public Book addBook(Book book) throws Exception;
	public Book getBookById(int id) throws Exception;
}