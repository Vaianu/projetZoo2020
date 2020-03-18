package org.formation.zoo.stockage;

import org.formation.zoo.service.CagePOJO;

/**
 * PATRON DE LA FABRIQUE 
 * @author SM Vaianu
 *
 */
public class DaoFactory {
	/**
	 * pour SINGLETON
	 */
	private static DaoFactory instance = new DaoFactory();
	private DaoFactory() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Singleton
	 * @return l'instance unique de la classe
	 */
	public static DaoFactory getInstance() {
		return instance;
	}
	
	/**
	 * Choix DAO
	 * @param typeDAO le choix du type de DAO qu'on veut. Soit DAOJDBC soit DAOMemoire
	 * @return un instance d'un type de DAO en fonction du choix du DAO choisit
	 */
	public Dao<CagePOJO> getDao(TypeDao typeDAO){
//		return new FichierAccess<CagePOJO>("zoo.data");
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
