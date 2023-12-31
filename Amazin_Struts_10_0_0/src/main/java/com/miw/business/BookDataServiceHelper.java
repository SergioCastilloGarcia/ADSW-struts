package com.miw.business;

import java.util.List;

import com.miw.infrastructure.Factories;
import com.miw.model.Book;

public class BookDataServiceHelper {

	public List<Book> getBooks() throws Exception {
		return (Factories.dataServices.getBookDataService()).getBooks();
	}
	public Book getBook(int id) throws Exception {
		return (Factories.dataServices.getBookDataService()).getBook(id);
	}
}
