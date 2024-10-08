package com.miw.presentation.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.miw.model.LoginInfo;
import com.miw.model.User;
import com.miw.presentation.user.UserManagerServiceHelper;
import com.miw.util.Constants;
import com.opensymphony.xwork2.ActionSupport;


@Results({
	@Result(name="success", location="counter.action" , type="redirectAction" ),
    @Result(name="login-error", location="/index.jsp"),
	
	//For validation
    @Result(name="input", location="/index.jsp")

})

/*@Validations(
		requiredStrings = {
					@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "loginInfo.login", message = "You must enter a value for login."),
					@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "loginInfo.password", message = "You must enter a value for password.") })
*/
public class LoginAction extends ActionSupport implements ServletRequestAware , SessionAware {

	Logger logger = LogManager.getLogger(this.getClass());
	private static final long serialVersionUID = -3854762737466390371L;
	private LoginInfo login = null;
	private Map<String, Object> session = null;
	private HttpServletRequest request;

	public LoginInfo getLoginInfo() {
		return login;
	}

	public void setLoginInfo(LoginInfo login) {
		this.login = login;
	}

	@Override
	public String execute() throws Exception {

		//Quito el captcha por comodidad
		/*if ( !login.getCaptcha().equals("23344343"))
		{
			request.setAttribute("mymessage", "Captcha is wrong");

			return "captcha-error";
		}*/
		UserManagerServiceHelper helper = new UserManagerServiceHelper();
		User userExist = helper.getUserByLoginAndPassword(login.getLogin(),login.getPassword());
		if(userExist!=null) {
			logger.debug("Loggin in!: " + login);
			session.put(Constants.LOGIN_INFO, login );
			return SUCCESS;
		}
		else
		{
			logger.debug("Credentials are wrong: " + login);
			request.setAttribute("mymessage", "Wrong credentials");
			return "login-error";
		}
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
