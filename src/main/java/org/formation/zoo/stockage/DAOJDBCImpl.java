package org.formation.zoo.stockage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.formation.zoo.service.CagePOJO;

public class DAOJDBCImpl implements Dao<CagePOJO> {
	private DaoORB connecteur;
	public DAOJDBCImpl() {
		connecteur = new DaoORB();
	}

	@Override
	public List<CagePOJO> lireTous() {
		String req = "SELECT * FROM animal;";
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connecteur.getConn().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void ecrireTous(List<CagePOJO> elts) {
		// TODO Auto-generated method stub
		
	}

}
