package com.miw.presentation.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.miw.model.Buy;
import com.miw.model.LoginInfo;
import com.miw.model.ShoppingCart;
import com.miw.model.User;
import com.miw.presentation.buy.BuyManagerServiceHelper;
import com.miw.presentation.user.UserManagerServiceHelper;
import com.miw.util.Constants;
import com.opensymphony.xwork2.ActionSupport;


@Results({
	@Result(name="success", location="show-books.action" , type="redirectAction" ),
    @Result(name="buy-error", location="/WEB-INF/content/buy.jsp"),
	
	//For validation
    @Result(name="buy", location="/WEB-INF/content/buy.jsp")

})


public class BuyAction extends ActionSupport implements ServletRequestAware , SessionAware {

	
	private static final long serialVersionUID = -2629540519658623032L;
	Logger logger = LogManager.getLogger(this.getClass());
	private String direccion = null;
	private Map<String, Object> session = null;
	private HttpServletRequest request;

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String execute() throws Exception {


		try {
			LoginInfo loginInfo = (LoginInfo) session.get(Constants.LOGIN_INFO);
			ShoppingCart shoppingCart=(ShoppingCart) session.get(Constants.SHOPPING_CART);
			UserManagerServiceHelper userHelper = new UserManagerServiceHelper();
			User user = userHelper.getUserByLogin(loginInfo.getLogin());
			//Añade la compra
			BuyManagerServiceHelper helper = new BuyManagerServiceHelper();
			Buy buy =helper.addBuy(shoppingCart.getPrice(),direccion,user);
			System.out.println(buy);
			if(buy!=null) {
				session.put(Constants.SHOPPING_CART, new ShoppingCart());//reseteamos el carrito
				return SUCCESS;
			}
		}catch(Exception e) {
			logger.error("No se ha podido añadir la compra: "+e.getMessage());
		}
		return "buy-error";
		
	}

	@Override
	public void withSession(Map<String, Object> session) {
		this.session = session;		
	}

	@Override
	public void withServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
}
