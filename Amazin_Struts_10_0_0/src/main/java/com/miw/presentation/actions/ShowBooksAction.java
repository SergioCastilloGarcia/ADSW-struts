package com.miw.presentation.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.miw.presentation.book.BookManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;
import com.miw.model.Book;


@ParentPackage(value ="miw.Amazin")

public class ShowBooksAction extends ActionSupport implements ServletRequestAware, ApplicationAware  {
	
	private static final long serialVersionUID = -4752542581534740735L;
	Logger logger = LogManager.getLogger(this.getClass());
	HttpServletRequest request = null;
	private Map<String, Object> application = null;
    private static final String BOOKS_LIST = "booklist";

	public String execute() {
		logger.debug("Executing ShowBooksCommand");
		try {
			if ( application.get(BOOKS_LIST) == null )//si no está la lista de libros en el contexto
			{
				logger.debug("Context has not books");
				BookManagerServiceHelper helper = new BookManagerServiceHelper();
				List<Book> booklist = helper.getBooks();
				application.put(BOOKS_LIST, booklist);//Añado la lista al contexto
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


	@Override
	public void withServletRequest(HttpServletRequest request) {
		this.request = request;			
		
	}


	@Override
	public void withApplication(Map<String, Object> application) {
		this.application = application;		
		
	}

	
}
