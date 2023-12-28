package com.miw.persistence.user;

import com.miw.model.User;

public interface UserDataService {

	User getUserByLogin(String login) throws Exception;
	User getUserByLoginAndPassword(String login, String password) throws Exception;
	User registerUser(String login, String password) throws Exception;

}