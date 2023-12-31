package com.miw.presentation.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.ApplicationAware;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.miw.presentation.book.BookManagerServiceHelper;
import com.miw.presentation.buy.BuyManagerServiceHelper;
import com.miw.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.miw.model.Book;
import com.miw.model.Buy;
import com.miw.model.LoginInfo;


@ParentPackage(value ="miw.Amazin")

public class ShowBuysAction extends ActionSupport implements ServletRequestAware, SessionAware  {
	
	private static final long serialVersionUID = -4752542581534740735L;
	Logger logger = LogManager.getLogger(this.getClass());
	HttpServletRequest request = null;
	private Map<String, Object> session = null;
    private static final String BOOKS_LIST = "booklist";

	public String execute() {
		logger.debug("Executing ShowBooksCommand");
		try {
			LoginInfo loginInfo = (LoginInfo) session.get(Constants.LOGIN_INFO);
			if ( loginInfo != null )//si no está la lista de libros en el contexto
			{
				logger.debug("Context has not books");
				BuyManagerServiceHelper helper = new BuyManagerServiceHelper();
				List<Buy> buylist = helper.getBuysByLogin(loginInfo.getLogin());
				System.out.println(buylist.size());
				request.setAttribute("buys", buylist);
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
	public void withSession(Map<String, Object> session) {
		this.session = session;		
	}

	
}
