package com.miw.persistence.buy;

import java.util.List;

import com.miw.model.Buy;
import com.miw.model.User;

public interface BuyDataService {
	Buy addBuy(Buy buy) throws Exception;

	Buy getBuyById(int id) throws Exception;

	List<Buy> getBuysByUser(User userId) throws Exception;

	List<Buy> getBuys() throws Exception;
}