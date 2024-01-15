package com.miw.presentation.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.*;
import org.apache.openejb.config.rules.CheckRestMethodArePublic;

import com.miw.model.Book;
import com.miw.model.ShoppingCart;
import com.miw.model.UserBean;
import com.miw.presentation.book.BookManagerServiceHelper;

@Named
@SessionScoped
public class Controller implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3757676961917467994L;

	private BookManagerServiceHelper bookManagerServiceHelper = new BookManagerServiceHelper();
	private ShoppingCart shoppingCart = new ShoppingCart();
	private HashMap<String, Boolean>checkMap= new HashMap();
	Logger logger = LogManager.getLogger(this.getClass());

	private UserBean loginInfo = new UserBean();

	public UserBean getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(UserBean loginInfo) {
		logger.debug("Setting loginInfo");
		this.loginInfo = loginInfo;
	}

	public String login() {
		logger.debug("doing login with " + loginInfo);
		FacesMessage msg;
		if (loginInfo.getLogin().equals("admin") && loginInfo.getPassword().equals("amazin"))
		{
		    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ "+ loginInfo.getLogin(), loginInfo.getLogin());
			FacesContext.getCurrentInstance().addMessage("welcome", msg);
			return "success";
		}
		else
		{
			 msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error: Invalid credentials",
                     "Credentials are not valid");
			 FacesContext.getCurrentInstance().addMessage(null, msg);

			return "login-error";
		}
	}

	public String showBooksAction() {
		logger.debug("Redirecting to showBooks view with success");
		// At this point we interact with the model...
		return "success";
	}
	public String addBooksAction() {
		logger.debug("Redirecting to addBooks view");
		return "success";
	}
	public String showSpecialOfferAction() {
		logger.debug("Redirecting to showSpecialOffer view");
		return "success";
	}public String showCartAction() {
		logger.debug("Redirecting to showCart view");
		return "success";
	}
	public String startAction() {
		return "success";
	}
	public HashMap<String, Boolean> getCheckMap() {
		return checkMap;
	}
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public String addBooks() {
		try {
			List<Book> books = bookManagerServiceHelper.getBooks();
			checkMap.clear();
			for(Book book:books) {
				checkMap.put(book.getTitle(), Boolean.FALSE);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
		
	} 	 	
	public String addProcess() {
		for (String title: checkMap.keySet()) {
			if(checkMap.get(title)) {
				shoppingCart.add(title);
			}
		}
		return "success";
	}
}
