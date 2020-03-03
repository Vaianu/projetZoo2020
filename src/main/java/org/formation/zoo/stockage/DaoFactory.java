package org.formation.zoo.stockage;

import org.formation.zoo.service.CagePOJO;

public class DaoFactory {
	private static DaoFactory instance = new DaoFactory();
	private DaoFactory() {
		// TODO Auto-generated constructor stub
	}
	public static DaoFactory getInstance() {
		return instance;
	}
	public Dao<CagePOJO> getDao(){
//		return new FichierAccess<CagePOJO>("zoo.data");
		return new DAOJDBCImpl();
	}

}
