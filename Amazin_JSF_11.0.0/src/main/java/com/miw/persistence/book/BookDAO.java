package com.miw.persistence.book;

import java.util.List;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.*;

import com.miw.model.Book;
import com.miw.persistence.Dba;

@Named ("bookDataService")
@RequestScoped
public class BookDAO implements BookDataService  {

	protected Logger logger = LogManager.getLogger(getClass());

	public List<Book> getBooks() throws Exception {

		List<Book> resultList = null;

		Dba dba = new Dba();
		try {
			EntityManager em = dba.getActiveEm();

			resultList = em.createQuery("Select a From Book a", Book.class).getResultList();

			logger.debug("Result list: "+ resultList.toString());
			for (Book next : resultList) {
				logger.debug("next book: " + next);
			}

		} finally {
			// 100% sure that the transaction and entity manager will be closed
			dba.closeEm();
		}

		// We return the result
		return resultList;
	}
	@Override
	public Book addBook(Book book)throws Exception {
		Dba dba = new Dba();
	    try {
	        EntityManager em = dba.getActiveEm();

	        // Persiste el nuevo usuario
	        em.persist(book);

	        logger.debug("Buy registered successfully: " + book.toString());

	    } catch (Exception e) {
	        // Si hay algún error, realiza un rollback
	        if (dba.getActiveEm().getTransaction().isActive()) {
	            dba.getActiveEm().getTransaction().rollback();
	        }
	        throw e;
	    } finally {
	        // Asegura que la transacción y el entity manager se cierren
	        dba.closeEm();
	    }

	    return book;
	}
	@Override
	public Book updateBook(Book book) throws Exception {
		Dba dba = new Dba();
	    try {
	        EntityManager em = dba.getActiveEm();

	        em.merge(book);

	        logger.debug("Buy updated successfully: " + book.toString());

	    } catch (Exception e) {
	        // Si hay algún error, realiza un rollback
	        if (dba.getActiveEm().getTransaction().isActive()) {
	            dba.getActiveEm().getTransaction().rollback();
	        }
	        throw e;
	    } finally {
	        // Asegura que la transacción y el entity manager se cierren
	        dba.closeEm();
	    }

	    return book;
	}
	public Book getBookById(int id) throws Exception {
		Book book = null;
		Dba dba = new Dba();
		try {
			EntityManager em = dba.getActiveEm();
			book= em.find(Book.class,id);
			logger.debug("Book: "+ book);

		}catch (Exception e) {
			return null;//Si no hay ningun resultado, devuelvo null;
		}finally {
			// 100% sure that the transaction and entity manager will be closed
			dba.closeEm();
		}
		return book;
	}
}