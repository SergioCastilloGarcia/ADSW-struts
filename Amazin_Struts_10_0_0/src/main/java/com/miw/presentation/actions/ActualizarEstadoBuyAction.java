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
	@Result(name="success", location="show-buys.action" , type="redirectAction" ),
    @Result(name="estado-error", location="show-buys.action" , type="redirectAction" ),

})


public class ActualizarEstadoBuyAction extends ActionSupport implements ServletRequestAware , SessionAware {


	private static final long serialVersionUID = 6180382975156959878L;
	Logger logger = LogManager.getLogger(this.getClass());
	private int buyId ;
	private String estado = null;
	private Map<String, Object> session = null;
	private HttpServletRequest request;


	@Override
	public String execute() throws Exception {


		try {
			LoginInfo loginInfo = (LoginInfo) session.get(Constants.LOGIN_INFO);
			UserManagerServiceHelper userHelper = new UserManagerServiceHelper();
			User user = userHelper.getUserByLogin(loginInfo.getLogin());
			if(user.getAdmin()==false) {
				return "estado-error";//solo un admin puede modificar el estado de una compra
			}
			//Obtiene la compra
			BuyManagerServiceHelper helper = new BuyManagerServiceHelper();
			Buy buy =helper.getBuyById(buyId);
			if(buy!=null) {
				buy.setEstado(estado);
				helper.updateBuy(buy);
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

	public int getBuyId() {
		return buyId;
	}

	public void setBuyId(int buyId) {
		this.buyId = buyId;
	}
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
