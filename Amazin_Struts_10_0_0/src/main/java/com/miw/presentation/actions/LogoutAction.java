package com.miw.presentation.actions;

import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.miw.util.Constants;
import com.opensymphony.xwork2.ActionSupport;


@Results({
    @Result(name="success", location="/index.jsp")

})

public class LogoutAction extends ActionSupport implements SessionAware {

	
	private static final long serialVersionUID = 7326942934072658445L;
	Logger logger = LogManager.getLogger(this.getClass());
	private Map<String, Object> session = null;

	

	@Override
	public String execute() throws Exception {

		logger.debug("Loggout ");
		session.put(Constants.LOGIN_INFO, null );//Con un null sirve para cerrar sesion
		return SUCCESS;
	}

	@Override
	public void withSession(Map<String, Object> session) {
		this.session = session;		
	}

	
}
