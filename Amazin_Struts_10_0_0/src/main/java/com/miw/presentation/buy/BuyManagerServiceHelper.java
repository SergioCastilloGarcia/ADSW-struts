package com.miw.presentation.buy;

import java.util.List;

import org.apache.logging.log4j.*;

import com.miw.infrastructure.Factories;
import com.miw.model.Buy;
import com.miw.model.User;

public class BuyManagerServiceHelper {

	Logger logger = LogManager.getLogger(this.getClass());

	public Buy addBuy(double price, String direccion, User user) throws Exception {
		logger.debug("Adding buy from Business Layer");
		return (Factories.services.getBuyManagerService()).addBuy(price, direccion, user);
	}
	public List<Buy> getBuysByLogin(String login) throws Exception {
		logger.debug("Retrieving Buy from Business Layer");
		return (Factories.services.getBuyManagerService()).getBuysByLogin(login);
	}
	public Buy getBuyById(int id) throws Exception {
		logger.debug("Retrieving Buy from Business Layer");
		return (Factories.services.getBuyManagerService()).getBuyById(id);
	}
	public List<Buy> getBuys() throws Exception {
		logger.debug("Retrieving Buys from Business Layer");
		return (Factories.services.getBuyManagerService()).getBuys();
	}
	
	
}
