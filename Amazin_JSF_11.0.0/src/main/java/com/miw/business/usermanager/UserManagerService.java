package com.miw.business.usermanager;

import com.miw.model.User;

public interface UserManagerService {
	public User getUserByLogin(String login) throws Exception;
	public User getUserByLoginAndPassword(String login, String password) throws Exception;
	public User registerUser(String login,String dni,String name, String password) throws Exception;

}