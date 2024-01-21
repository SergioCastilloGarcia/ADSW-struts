package com.miw.presentation.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.miw.presentation.buy.BuyManagerServiceHelper;
import com.miw.presentation.user.UserManagerServiceHelper;
import com.miw.util.BuyConstants;
import com.miw.util.Constants;
import com.opensymphony.xwork2.ActionSupport;
import com.miw.model.Buy;
import com.miw.model.LoginInfo;
import com.miw.model.User;


@ParentPackage(value ="miw.Amazin")

public class ShowBuysAction extends ActionSupport implements ServletRequestAware, SessionAware  {
	
	private static final long serialVersionUID = -4752542581534740735L;
	Logger logger = LogManager.getLogger(this.getClass());
	HttpServletRequest request = null;
	private Map<String, Object> session = null;

	public String execute() {
		logger.debug("Executing ShowBooksCommand");
		try {
			LoginInfo loginInfo = (LoginInfo) session.get(Constants.LOGIN_INFO);
			if ( loginInfo != null )//si no está la lista de libros en el contexto
			{
				BuyManagerServiceHelper helper = new BuyManagerServiceHelper();
				List<Buy> buylist = null;
				UserManagerServiceHelper userHelper = new UserManagerServiceHelper();
				User user = userHelper.getUserByLogin(loginInfo.getLogin());
				//Si el usuario es administrador, podrá ver todas las compras
				if(user.getAdmin()==true) {
					buylist = helper.getBuys();
					request.setAttribute("admin", true);
					request.setAttribute("estados", getEstadosOptions());
				}
				else {
					buylist = helper.getBuysByLogin(loginInfo.getLogin());
				}
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
	
    // Método para obtener las constantes como un mapa
    public Map<String, String> getEstadosOptions() {
        Map<String, String> estadosOptions = new HashMap<>();
        estadosOptions.put(BuyConstants.PREPARACION, "En preparación");
        estadosOptions.put(BuyConstants.CAMINO, "En camino");
        estadosOptions.put(BuyConstants.ENTREGA, "En entrega");
        estadosOptions.put(BuyConstants.ENTREGADO, "Pedido entregado");
        return estadosOptions;
    }
	
}
