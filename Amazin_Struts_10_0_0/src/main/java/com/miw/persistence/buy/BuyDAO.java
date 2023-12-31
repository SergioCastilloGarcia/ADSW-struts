package com.miw.persistence.buy;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.*;

import com.miw.model.Buy;
import com.miw.persistence.Dba;

public class BuyDAO implements BuyDataService  {

	protected Logger logger = LogManager.getLogger(getClass());


	@Override
	public Buy addBuy(Buy buy) throws Exception {
		Dba dba = new Dba();
	    try {
	        EntityManager em = dba.getActiveEm();

	        // Persiste el nuevo usuario
	        em.persist(buy);

	        logger.debug("Buy registered successfully: " + buy.toString());

	    } catch (Exception e) {
	        // Si hay algún error, realiza un rollback
	        if (dba.getActiveEm().getTransaction().isActive()) {
	            dba.getActiveEm().getTransaction().rollback();
	        }
	        throw e;
	    } finally {
	        // Asegura que la transacción y el entity manager se cierren
	        dba.closeEm();
	    }

	    return buy;
	}
	@Override
	public Buy getBuyById(int id) throws Exception {
		Buy Buy = null;
		Dba dba = new Dba();
		try {
			EntityManager em = dba.getActiveEm();
			//Obtengo el usuario por su nombre
			String jpql = "SELECT b FROM Buy b WHERE b.id = :id";
			Buy = em.createQuery(jpql, Buy.class)
		              .setParameter("id", id)
		              .getSingleResult();

			logger.debug("Buy: "+ Buy);

		}catch (Exception e) {
			return null;//Si no hay ningun resultado, devuelvo null;
		}finally {
			// 100% sure that the transaction and entity manager will be closed
			dba.closeEm();
		}
		return Buy;
	}
	@Override
	public List<Buy> getBuys() throws Exception {

		List<Buy> resultList = null;

		Dba dba = new Dba();
		try {
			EntityManager em = dba.getActiveEm();

			resultList = em.createQuery("Select a From Buy a", Buy.class).getResultList();

			logger.debug("Result list: "+ resultList.toString());
			for (Buy next : resultList) {
				logger.debug("next book: " + next);
			}

		} finally {
			// 100% sure that the transaction and entity manager will be closed
			dba.closeEm();
		}

		// We return the result
		return resultList;
	}
}