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
	public Dao<CagePOJO> getDao(TypeDao typeDAO){
//		return new FichierAccess<CagePOJO>("zoo.data");
//		return new DAOJDBCImpl();
//		return new DaoMemoire();
		switch (typeDAO) {
		case DAOJDBC:
			return new DAOJDBCImpl();
		case DAOMEMOIRE:
			return new DaoMemoire();

		default:
			return null;
		}
	}

}
