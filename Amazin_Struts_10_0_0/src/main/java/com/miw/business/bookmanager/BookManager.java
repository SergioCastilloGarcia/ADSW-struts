
package com.miw.business.bookmanager;

import java.util.List;

import org.apache.logging.log4j.*;

import com.miw.business.BookDataServiceHelper;
import com.miw.model.Book;

public class BookManager implements BookManagerService {
	Logger logger = LogManager.getLogger(this.getClass());

	public List<Book> getBooks() throws Exception {
		logger.debug("Asking for books");
		List<Book> books = (new BookDataServiceHelper()).getBooks();
		
		// We calculate the final price with the VAT value
		for (Book b : books) {
			b=setPrice(b);
		}
		return books;
	}
	
	public Book getSpecialOffer() throws Exception
	{
		List<Book> books = getBooks();
		int number = (int) (Math.random()*books.size());
		logger.debug("Applying disccount to "+books.get(number).getTitle());
		books.get(number).setPrice((double)books.get(number).getPrice()*0.85);
		return books.get(number);
	}
	public Book getBook(String id) throws Exception {
		try {//Llega la id como string, hay que pasearla a int
			Book book=new BookDataServiceHelper().getBook(Integer.parseInt(id));
			book=setPrice(book);
			return book;
		}catch(Exception e) {
			return null;
		}
	}
	public Book setPrice(Book book) {
		book.setPrice(book.getBasePrice() * (1 + book.getVat().getValue()));
		return book;
	}
}
