package com.miw.business.usermanager;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.*;

import com.miw.model.User;
import com.miw.persistence.book.BookDataService;
import com.miw.persistence.user.UserDataService;
@Named ("userManagerService")
@RequestScoped
public class UserManager implements UserManagerService {
	Logger logger = LogManager.getLogger(this.getClass());

	@Inject
	private UserDataService userDataService = null;
	public User getUserByLogin(String login) throws Exception {
		logger.debug("Asking for user by Login");
		User user = userDataService.getUserByLogin(login);
		return user;
	}
	public User getUserByLoginAndPassword(String login, String password) throws Exception {
		logger.debug("Asking for user by Login and Password");
		//Deberiamos encriptar la contraseña, pero como no es el objetivo de esta tarea lo obviare
		User user = userDataService.getUserByLoginAndPassword(login,password);
		return user;
	}
	public User registerUser(String login,String dni,String name, String password) throws Exception {
		logger.debug("Registing user");
		//Deberiamos encriptar la contraseña, pero como no es el objetivo de esta tarea lo obviare
	    User user = new User(login,dni,name, password, false);
	    userDataService.registerUser(user);
		return user;
	}
}
