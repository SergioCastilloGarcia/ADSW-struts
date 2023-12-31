package com.miw.business;


import com.miw.infrastructure.Factories;
import com.miw.model.User;

public class UserDataServiceHelper {

	public User getUserByLogin(String login) throws Exception {
		return (Factories.dataServices.getUserDataService()).getUserByLogin(login);
	}
	public User getUserByLoginAndPassword(String login, String password) throws Exception {
		return (Factories.dataServices.getUserDataService()).getUserByLoginAndPassword(login,password);
	}
	public User registerUser(User user) throws Exception {
		return (Factories.dataServices.getUserDataService()).registerUser(user);
	}
}
