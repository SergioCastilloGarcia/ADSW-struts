package com.miw.presentation.user;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.*;

import com.miw.business.usermanager.UserManagerService;
import com.miw.model.User;

@Named ("userManagerServiceHelper")
@RequestScoped
public class UserManagerServiceHelper {

	@Inject
	private UserManagerService userManagerService = null;
	
	Logger logger = LogManager.getLogger(this.getClass());

	public User getUserByLogin(String login) throws Exception {
		logger.debug("Retrieving User from Business Layer");
		return userManagerService.getUserByLogin(login);
	}
	public User getUserByLoginAndPassword(String login, String password) throws Exception {
		logger.debug("Retrieving User from Business Layer");
		return userManagerService.getUserByLoginAndPassword(login,password);
	}
	public User registerUser(String login,String dni,String name, String password) throws Exception {
		logger.debug("Adding User from Business Layer");
		return userManagerService.registerUser(login,dni,name, password);
	}
	
}
