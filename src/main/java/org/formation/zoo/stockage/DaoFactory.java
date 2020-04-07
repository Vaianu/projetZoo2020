package org.formation.zoo.stockage;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.formation.zoo.service.CagePOJO;

public class DaoFactory {
	private static DaoFactory instance = new DaoFactory();
	public final static String CHEMIN = "org.formation.zoo.stockage.";
	private Class<?> classeDao;
	private Properties properties;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private DaoFactory(){
		logger.setLevel(Level.INFO);
		properties = new Properties();
		try {
			properties.load(new FileInputStream("zoo.properties"));
		} catch (IOException e) {
			logger.log(Level.INFO,e.getMessage());
		}
	}
	public static DaoFactory getInstance() {
		return instance;
	}
	@SuppressWarnings("unchecked")
	public Dao<CagePOJO> getDao(){
		Dao<CagePOJO> ret = null;
		try {
			classeDao = (Class<?>) Class.forName(String.join("", CHEMIN,properties.getProperty("dao")));
			ret = (Dao<CagePOJO>) classeDao.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			logger.log(Level.INFO,e.getMessage());
		}
		return ret;
	}

}

