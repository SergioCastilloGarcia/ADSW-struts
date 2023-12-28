package com.miw.presentation.user;


import org.apache.logging.log4j.*;

import com.miw.infrastructure.Factories;
import com.miw.model.User;

public class UserManagerServiceHelper {

	Logger logger = LogManager.getLogger(this.getClass());

	public User getUserByLogin(String login) throws Exception {
		logger.debug("Retrieving User from Business Layer");
		return (Factories.services.getUserManagerService()).getUserByLogin(login);
	}

	public User registerUser(String login, String password) throws Exception {
		logger.debug("Adding User from Business Layer");
		return (Factories.services.getUserManagerService()).registerUser(login, password);
	}
	
}
