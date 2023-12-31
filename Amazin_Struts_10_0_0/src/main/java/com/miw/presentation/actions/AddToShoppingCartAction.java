package com.miw.presentation.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.miw.model.Book;
import com.miw.model.ShoppingCart;
import com.miw.presentation.book.BookManagerServiceHelper;
import com.miw.util.Constants;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage(value ="miw.Amazin")
@Result(name="success", location="view-shopping-cart.action" , type="redirectAction" )
public class AddToShoppingCartAction extends ActionSupport implements ServletRequestAware,SessionAware  {
	
	
	private static final long serialVersionUID = 6109209800155780574L;
	Logger logger = LogManager.getLogger(this.getClass());
	HttpServletRequest request = null;
	private Map<String, Object> session=null;
    private List<String> addedBooks;

	@Override
	public String execute() {
		logger.debug("Executing AddToShoppingCartActionCommand");
		try {
			ShoppingCart shoppingCart=(ShoppingCart) session.get(Constants.SHOPPING_CART);
			if ( shoppingCart == null )//si no está el carrito de libros en el contexto
			{
				logger.debug("Session has not shoppingCart");
				shoppingCart= new ShoppingCart();
				session.put(Constants.SHOPPING_CART, shoppingCart);//Añado el carrito al contexto
			}
			
			for (String bookId : addedBooks) {//Añado los nuevos libros
				BookManagerServiceHelper helper = new BookManagerServiceHelper();
				Book book = helper.getBook(bookId);
				shoppingCart.add(book);
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
	
	public List<String> getAddedBooks() {
		return addedBooks;
	}

	public void setAddedBooks(List<String> addedBooks) {
		this.addedBooks = addedBooks;
	}
}
