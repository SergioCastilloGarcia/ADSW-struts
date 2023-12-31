package com.miw.business.buy;

import java.util.List;

import com.miw.model.Buy;
import com.miw.model.User;

public interface BuyManagerService {
	public Buy addBuy(double price, String direccion, User user) throws Exception;
	public Buy getBuyById(int id) throws Exception;
	public List<Buy> getBuysByLogin(String login) throws Exception;
	public List<Buy> getBuys() throws Exception;

}