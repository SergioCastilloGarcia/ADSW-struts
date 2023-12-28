package com.miw.business;


import com.miw.infrastructure.Factories;
import com.miw.model.User;

public class UserDataServiceHelper {

	public User getUserByLogin(String login) throws Exception {
		return (Factories.dataServices.getUserDataService()).getUserByLogin(login);
	}
	public User registerUser(String login, String password) throws Exception {
		return (Factories.dataServices.getUserDataService()).registerUser(login, password);
	}
}
