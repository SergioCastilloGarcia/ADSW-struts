
package com.miw.business.bookmanager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.*;

import com.miw.model.Book;
import com.miw.persistence.book.BookDataService;
//import com.miw.persistence.vat.VATDataService;

@Named ("bookManagerService")
@RequestScoped
public class BookManager implements BookManagerService {
	Logger logger = LogManager.getLogger(this.getClass());
	
	@Inject
	private BookDataService bookDataService = null;
	/*
	@Inject
	private VATDataService vatDataService = null;
	
	public void setVatDataService(VATDataService vatDataService) {
		logger.debug("Injecting vatDataService");
		this.vatDataService = vatDataService;
	}
	*/
	public void setBookDataService(BookDataService bookDataService) {
		logger.debug("Injecting bookDataService");
		this.bookDataService = bookDataService;
	}



	public List<Book> getBooks() throws Exception {
		logger.debug("Asking for books");
		List<Book> books = bookDataService.getBooks();
		
		// We calculate the final price with the VAT value
		for (Book b : books) {
			FacesMessage msg;
			b.setPrice(b.getBasePrice() * (1 + b.getVat().getValue()));
			if(b.getStock()>=10) {
				b.setPrice(b.getPrice() * (0.95));
			    b.setMessage( "¡Oferta!");
			}if(b.getStock()<=3) {
				b.setPrice(b.getPrice() * (1.05));
			    b.setMessage( "¡Últimas unidades!");
			}

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
	public Book updateBook(Book book) throws Exception{
		Book result =bookDataService.updateBook(book);
		return result;
	}public Book addBook(Book book) throws Exception{
		Book result =bookDataService.addBook(book);
		return result;
	}public Book getBookById(int id) throws Exception{
		Book result =bookDataService.getBookById(id);
		return result;
	}
	
}
