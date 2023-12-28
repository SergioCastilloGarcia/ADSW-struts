package com.miw.persistence.user;


import javax.persistence.EntityManager;

import org.apache.logging.log4j.*;

import com.miw.model.User;
import com.miw.persistence.Dba;

public class UserDAO implements UserDataService  {

	protected Logger logger = LogManager.getLogger(getClass());

	@Override
	public User getUserByLogin(String login) throws Exception {
		User user = null;
		Dba dba = new Dba();
		try {
			EntityManager em = dba.getActiveEm();
			//Obtengo el usuario por su nombre
			String jpql = "SELECT u FROM User u WHERE u.username = :username";
			user = em.createQuery(jpql, User.class)
		              .setParameter("username", login)
		              .getSingleResult();

			logger.debug("User: "+ user.toString());

		}catch (Exception e) {
			return null;//Si no hay ningun resultado, devuelvo null;
		}finally {
		
			// 100% sure that the transaction and entity manager will be closed
			dba.closeEm();
		}
		return user;
	}

	@Override
	public User registerUser(String login, String password) throws Exception {
	    User newUser = new User(login, password, false);
		Dba dba = new Dba();
	    try {
	        EntityManager em = dba.getActiveEm();

	        // Persiste el nuevo usuario
	        em.persist(newUser);

	        logger.debug("User registered successfully: " + newUser.toString());

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

	    return newUser;
	}
}