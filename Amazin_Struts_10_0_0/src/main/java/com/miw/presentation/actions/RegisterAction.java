package com.miw.presentation.actions;


import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.miw.model.LoginInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;


@Results({
	@Result(name="success", location="/index.jsp" ),
    @Result(name="register-error", location="/WEB-INF/content/register.jsp"),
	
	//For validation
    @Result(name="register", location="/WEB-INF/content/register.jsp")

})

/*@Validations(
		requiredStrings = {
					@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "loginInfo.login", message = "You must enter a value for login."),
					@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "loginInfo.password", message = "You must enter a value for password."),
					@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "loginInfo.passwordRepeat", message = "You must enter a value for password repeat.") })
*/
public class RegisterAction extends ActionSupport implements ServletRequestAware {

	
	private static final long serialVersionUID = -2136730103218052292L;
	Logger logger = LogManager.getLogger(this.getClass());
	private LoginInfo login = null;
	private HttpServletRequest request;

	public LoginInfo getLoginInfo() {
		return login;
	}

	public void setLoginInfo(LoginInfo login) {
		this.login = login;
	}

	@Override
	public String execute() throws Exception {

		if ( !login.getPassword().equals(login.getPasswordRepeat()))
		{
			request.setAttribute("mymessage", "Las Passwords no coinciden");

			return "register-error";
		}
		//TODO validar que el usuario no exista
		return SUCCESS;
	}
	
	@Override
	public void withServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
}
