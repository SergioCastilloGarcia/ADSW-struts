package com.miw.business.usermanager;


import org.apache.logging.log4j.*;

import com.miw.business.UserDataServiceHelper;
import com.miw.model.User;

public class UserManager implements UserManagerService {
	Logger logger = LogManager.getLogger(this.getClass());

	public User getUserByLogin(String login) throws Exception {
		logger.debug("Asking for user by Login");
		User user = (new UserDataServiceHelper()).getUserByLogin(login);
		return user;
	}
	public User getUserByLoginAndPassword(String login, String password) throws Exception {
		logger.debug("Asking for user by Login and Password");
		//Deberiamos encriptar la contraseña, pero como no es el objetivo de esta tarea lo obviare
		User user = (new UserDataServiceHelper()).getUserByLoginAndPassword(login,password);
		return user;
	}
	public User registerUser(String login, String password) throws Exception {
		logger.debug("Registing user");
		//Deberiamos encriptar la contraseña, pero como no es el objetivo de esta tarea lo obviare
	    User user = new User(login, password, false);
		(new UserDataServiceHelper()).registerUser(user);
		return user;
	}
}
