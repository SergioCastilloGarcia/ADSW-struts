package com.miw.presentation.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;


@ParentPackage(value ="miw.Amazin")
@Result(name="success", location="/WEB-INF/content/viewshoppingcart.jsp" )
public class ViewShoppingCartAction extends ActionSupport implements ServletRequestAware  {
	
	
	private static final long serialVersionUID = 2685836764427279681L;
	Logger logger = LogManager.getLogger(this.getClass());
	HttpServletRequest request = null;

	@Override
	public String execute() {
		logger.debug("Executing ViewShoppingCartAction");
		
		return SUCCESS;
	}


	@Override
	public void withServletRequest(HttpServletRequest request) {
		this.request = request;			
	}
	
	
}
