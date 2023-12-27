package com.miw.presentation.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.miw.presentation.book.BookManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value ="miw.Amazin")

public class ShowSpecialOfferAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 4674623943937761755L;
	Logger logger = LogManager.getLogger(this.getClass());


	HttpServletRequest request = null;
	
	@Override
	public String execute() throws Exception {
		logger.debug("Executing "+this.getClass().getName());
		BookManagerServiceHelper helper = new BookManagerServiceHelper();
		try {
			request.setAttribute("book", helper.getSpecialOffer());
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
