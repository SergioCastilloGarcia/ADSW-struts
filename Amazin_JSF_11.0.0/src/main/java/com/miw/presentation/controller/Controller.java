package com.miw.presentation.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.*;

import com.miw.business.bookmanager.BookManager;
import com.miw.business.bookmanager.BookManagerService;
import com.miw.model.Book;
import com.miw.model.ShoppingCart;
import com.miw.model.User;
import com.miw.model.UserBean;
import com.miw.presentation.book.BookManagerServiceHelper;
import com.miw.presentation.user.UserManagerServiceHelper;

@Named
@SessionScoped
public class Controller implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3757676961917467994L;

	@Inject
	private BookManagerServiceHelper bookManagerServiceHelper = null;
	@Inject
	private UserManagerServiceHelper userManagerServiceHelper = null;
	private ShoppingCart shoppingCart = new ShoppingCart();
	private HashMap<Book, String>checkMap = new HashMap<Book, String>();
	Logger logger = LogManager.getLogger(this.getClass());

	private UserBean loginInfo = new UserBean();

	public void setBookManagerServiceHelper(BookManagerServiceHelper bookManagerServiceHelper) {
		logger.debug("Injecting bookManagerServiceHelper");
		this.bookManagerServiceHelper = bookManagerServiceHelper;
	}
	
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
		User userExist;
		try {
			userExist = userManagerServiceHelper.getUserByLoginAndPassword(loginInfo.getLogin(),loginInfo.getPassword());
			if(userExist!=null) {
				logger.debug("Loggin in!: " + loginInfo);
				 msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ "+ loginInfo.getLogin(), loginInfo.getLogin());
				FacesContext.getCurrentInstance().addMessage("welcome", msg);
				return "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error: Invalid credentials",
                     "Credentials are not valid");
			 FacesContext.getCurrentInstance().addMessage(null, msg);

			return "login-error";
	}
	public String register() {
		FacesMessage msg;
		User user;
		try {
			user = userManagerServiceHelper.registerUser(loginInfo.getLogin(), loginInfo.getDni(),
					loginInfo.getName(),loginInfo.getPassword());
			if (user!=null)
			{
			    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ "+ loginInfo.getLogin(), loginInfo.getLogin());
				FacesContext.getCurrentInstance().addMessage("welcome", msg);
				return "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al registrarse",
                     "Credentials are not valid");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return "register-error";
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
	}public String showRegisterAction() {
		logger.debug("Redirecting to register view");
		return "success";
	}
	public String startAction() {
		return "success";
	}
	public HashMap<Book, String> getCheckMap() {
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
				checkMap.put(book, "0");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
		
	} 	 	
	public String addProcess() {
		try {
			for (Book book: checkMap.keySet()) {
				try {
					int stock=Integer.parseInt(checkMap.get(book));
					if(stock >0) {
						
						shoppingCart.add(book,stock);
					}
				}catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
			return "success";
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "error";
	}
	public String buyProcess() {
		try {
			for (Book book: checkMap.keySet()) {
				try {
					int stock=Integer.parseInt(checkMap.get(book));
					if(stock >0) {
						Book contextBook=bookManagerServiceHelper.getBookById(book.getId());
						if(contextBook.getStock()>=stock) {
							contextBook.setStock(contextBook.getStock()-stock);
							bookManagerServiceHelper.updateBook(contextBook);
						}
					}
				}catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
			shoppingCart = new ShoppingCart();//resetea el carrito
			return "success";
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "error";
	}
}
