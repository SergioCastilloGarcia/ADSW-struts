package com.miw.presentation.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.miw.presentation.book.BookManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage(value ="miw.Amazin")

public class ShowBooksAction extends ActionSupport implements ServletRequestAware  {
	
	private static final long serialVersionUID = -4752542581534740735L;
	Logger logger = LogManager.getLogger(this.getClass());
	HttpServletRequest request = null;

	public String execute() {
		logger.debug("Executing ShowBooksCommand");
		BookManagerServiceHelper helper = new BookManagerServiceHelper();
		try {
			request.setAttribute("books", helper.getBooks());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


	@Override
	public void withServletRequest(HttpServletRequest request) {
		this.request = request;			
		
	}

	
}
