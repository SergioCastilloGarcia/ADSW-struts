package com.miw.business.buy;


import java.util.List;

import org.apache.logging.log4j.*;

import com.miw.business.BuyDataServiceHelper;
import com.miw.business.UserDataServiceHelper;
import com.miw.model.Buy;
import com.miw.model.User;
import com.miw.util.BuyConstants;

public class BuyManager implements BuyManagerService {
	Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public Buy addBuy(double price, String direccion, User user) throws Exception {
		logger.debug("Registing buy");
		Buy buy = new Buy(price, BuyConstants.PREPARACION, direccion, user);
		(new BuyDataServiceHelper()).addBuy(buy);
		return buy;
	}
	@Override
	public Buy getBuyById(int id) throws Exception {
		logger.debug("Asking for buy by id");
		Buy buy = (new BuyDataServiceHelper()).getBuyById(id);
		return buy;
	}
	@Override
	public List<Buy> getBuysByLogin(String login) throws Exception {
		logger.debug("Asking for buy by login");
		User user= (new UserDataServiceHelper()).getUserByLogin(login);//A traves del nmbre de usuario conseguimos el user
		List<Buy> buys = (new BuyDataServiceHelper()).getBuysByUser(user);
		return buys;
	}
	@Override
	public List<Buy> getBuys() throws Exception {
		logger.debug("Asking for buys");
		List<Buy> buys = (new BuyDataServiceHelper()).getBuys();
		return buys;
	}

	@Override
	public Buy updateBuy(Buy buy) throws Exception {
		logger.debug("Updating buy");
		return (new BuyDataServiceHelper()).updateBuy(buy);
	}
}
