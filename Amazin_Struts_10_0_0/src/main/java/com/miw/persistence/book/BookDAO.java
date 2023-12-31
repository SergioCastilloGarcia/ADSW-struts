package com.miw.persistence.book;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.*;

import com.miw.model.Book;
import com.miw.persistence.Dba;

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
	public Book getBook(int id) throws Exception {
		Book book = null;
		Dba dba = new Dba();
		try {
			EntityManager em = dba.getActiveEm();
			//Obtengo el usuario por su nombre
			String jpql = "SELECT b FROM Book b WHERE b.id = :id";
			book = em.createQuery(jpql, Book.class)
		              .setParameter("id", id)
		              .getSingleResult();

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