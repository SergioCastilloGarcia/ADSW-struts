package com.miw.presentation.book;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import org.apache.logging.log4j.*;

import com.miw.business.bookmanager.BookManagerService;
import com.miw.model.Book;
import com.miw.presentation.CounterBean;


@Named ("bookManagerServiceHelper")
@RequestScoped
public class BookManagerServiceHelper {
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	@Inject
	private CounterBean counter = null;
	
	@Inject
	private BookManagerService bookManagerService = null;
	
	public void setBookManagerService(BookManagerService bookManagerService) {
		logger.debug("Injecting bookManagerService");
		this.bookManagerService = bookManagerService;
	}

	public CounterBean getCounter() {
		return counter;
	}

	public void setCounter(CounterBean counter) {
		logger.debug("Injecting the counter");
		this.counter = counter;
	}

	public List<Book> getBooks() throws Exception {
		logger.debug("Retrieving Books from Business Layer and incrementing the counter");
		counter.inc();
		return bookManagerService.getBooks();
	}

	public Book getSpecialOffer() throws Exception {
		logger.debug("Retrieving Special Offer from Business Layer");
		return bookManagerService.getSpecialOffer();
	}

	public Book updateBook(Book book) throws Exception {
		logger.debug("Updating Book from Business Layer");
		return bookManagerService.updateBook(book);
	}public Book addBook(Book book) throws Exception {
		logger.debug("Adding Book from Business Layer");
		return bookManagerService.addBook(book);
	}
	public Book getBookById(int id) throws Exception {
		logger.debug("Adding Book from Business Layer");
		return bookManagerService.getBookById(id);
	}
}
