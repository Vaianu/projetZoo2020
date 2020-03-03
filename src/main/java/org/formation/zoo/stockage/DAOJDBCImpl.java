package org.formation.zoo.stockage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.service.GazellePOJO;

public class DAOJDBCImpl implements Dao<CagePOJO> {
	private DaoORB connecteur;
	public DAOJDBCImpl() {
		connecteur = new DaoORB();
	}

	@Override
	public List<CagePOJO> lireTous() {
		List<CagePOJO> ret = null;
		CagePOJO tmp = null;
		GazellePOJO gaz = null;
		
		String req = "SELECT * FROM animal as gauche left join gazelle "
				+ "as droite on gauche.idAnimal = droite.idAnimal;";
		Statement st = null;
		ResultSet rs = null;
		try {
			ret = new Vector<CagePOJO>();
			st = connecteur.getConn().createStatement();
			rs = st.executeQuery(req);
			if(rs != null) {
				while(rs.next()) {
					tmp = new CagePOJO();
					tmp.setX(rs.getInt("x"));
					tmp.setY(rs.getInt("y"));
					tmp.setCle(rs.getInt("idAnimal"));
					//SI QUELQU'UN
					if(rs.getString("codeAnimal") != null) {
						tmp.setAge(rs.getInt("age"));
						tmp.setNom(rs.getString("nom"));
						tmp.setPoids(rs.getDouble("poids"));
						tmp.setCodeAnimal(rs.getString("codeAnimal"));
					}
					//SI GAZELLE
					if(tmp.getCodeAnimal() != null && tmp.getCodeAnimal().equals("Gazelle")) {
						gaz = new GazellePOJO();
						gaz.setId(rs.getInt("id"));
						gaz.setIdAnimal(rs.getInt("idAnimal"));
						gaz.setLgCornes(rs.getInt("lgCornes"));
						tmp.setGaz(gaz);
					}
					ret.add(tmp);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void ecrireTous(List<CagePOJO> elts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifier(int cle, CagePOJO obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effacer(int cle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effacer(CagePOJO obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouter(CagePOJO obj) {
		// TODO Auto-generated method stub
		
	}

}
