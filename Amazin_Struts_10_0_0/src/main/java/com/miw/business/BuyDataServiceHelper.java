package com.miw.business;


import java.util.List;

import com.miw.infrastructure.Factories;
import com.miw.model.Buy;
import com.miw.model.User;

public class BuyDataServiceHelper {

	public Buy addBuy(Buy buy) throws Exception {
		return (Factories.dataServices.getBuyDataService()).addBuy(buy);
	}
	public Buy getBuyById(int id) throws Exception {
		return (Factories.dataServices.getBuyDataService()).getBuyById(id);
	}
	public List<Buy> getBuysByUser(User userId) throws Exception {
		return (Factories.dataServices.getBuyDataService()).getBuysByUser(userId);
	}
	public List<Buy> getBuys() throws Exception {
		return (Factories.dataServices.getBuyDataService()).getBuys();
	}
}
