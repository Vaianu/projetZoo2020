package org.formation.zoo.stockage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.service.GazellePOJO;

/**
 * @author SM Vaianu
 *
 */
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
		
		for (CagePOJO cagePOJO : elts) {
			String req = "INSERT INTO animal (codeAnimal,nom,age,poids,x,y)"
					+ " VALUES('"+cagePOJO.getCodeAnimal()+"','"+cagePOJO.getNom()+"',"+cagePOJO.getAge()
					+","+cagePOJO.getPoids()+","+cagePOJO.getX()+","+cagePOJO.getY()+")";
			Statement st = null;
			try {
				st = connecteur.getConn().createStatement();
				st.executeUpdate(req);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void modifier(int cle, CagePOJO obj) {
		String req = "UPDATE animal SET codeAnimal='" + obj.getCodeAnimal() + "', nom='" + obj.getNom() 
			+ "', age=" + obj.getAge() + ", poids=" + obj.getPoids() + ", x=" + obj.getX() + ", y=" + obj.getY() 
			+ " WHERE idAnimal=" + cle;
		Statement st = null;
		try {
			st = connecteur.getConn().createStatement();
			st.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void effacer(int cle) {
		String req = "DELETE FROM animal WHERE idAnimal=" + cle;
		Statement st = null;
		try {
			st = connecteur.getConn().createStatement();
			st.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void effacer(CagePOJO obj) {
		String req = "DELETE FROM animal WHERE idAnimal=" + obj.getCle();
		Statement st = null;
		try {
			st = connecteur.getConn().createStatement();
			st.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void ajouter(CagePOJO obj) {
		String req = "INSERT INTO animal (codeAnimal,nom,age,poids,x,y) "
				+ "VALUES('"+obj.getCodeAnimal()+"','"+obj.getNom()+"',"+obj.getAge()
				+","+obj.getPoids()+","+obj.getX()+","+obj.getY()+")";
		Statement st = null;
		try {
			st = connecteur.getConn().createStatement();
			st.executeUpdate(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
