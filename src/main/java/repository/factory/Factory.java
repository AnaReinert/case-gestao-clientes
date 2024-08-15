package repository.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {
	
	private static EntityManagerFactory factory = null;
	
	static {
		if(factory == null)
		factory = Persistence.createEntityManagerFactory("projetoweb");
		}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
