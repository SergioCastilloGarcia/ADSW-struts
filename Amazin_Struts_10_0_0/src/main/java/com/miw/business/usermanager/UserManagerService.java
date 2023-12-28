package com.miw.business.usermanager;

import com.miw.model.User;

public interface UserManagerService {
	public User getUserByLogin(String login) throws Exception;
	public User registerUser(String login, String password) throws Exception;

}