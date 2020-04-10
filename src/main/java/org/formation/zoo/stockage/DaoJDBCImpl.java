package org.formation.zoo.stockage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.formation.zoo.service.CagePOJO;
import org.formation.zoo.service.GazellePOJO;

/**
 * Cette classe implémente les méthodes C.R.U.D. en JDBC
 * Elle ne fonctionne qu'avec des CagePOJO!!!!!
 * Elle n'est pas générique 
 * @author jacques Vincensini
 *
 */
public class DaoJDBCImpl implements Dao<CagePOJO> {

	private DaoORB connecteur;
	private static final String GAZELLE = "Gazelle";
	private Logger logger = Logger.getLogger(this.getClass().getName());
	public DaoJDBCImpl() {
		logger.setLevel(Level.INFO);
		connecteur = new DaoORB();
	}
/**
 * Méthode qui permet de recinstituer un pojo à partir d'un ResultSet
 * @param rs le ResultSet en entrée
 * @return au moins un POJO "cage vide"
 * @throws SQLException
 */
	private CagePOJO creerPOJO(ResultSet rs) throws SQLException {
		CagePOJO ret = null;
		GazellePOJO gaz = null;
		
		ret = new CagePOJO();
		ret.setX(rs.getInt("x"));
		ret.setY(rs.getInt("y"));
		ret.setIdAnimal(rs.getInt("idAnimal"));
		//SI QUELQU'UN
		if(rs.getString("codeAnimal") != null) {
			ret.setAge(rs.getInt("age"));
			ret.setNom(rs.getString("nom"));
			ret.setPoids(rs.getDouble("poids"));
			ret.setCodeAnimal(rs.getString("codeAnimal"));
		//SI GAZELLE
			if(ret.getCodeAnimal().equals(GAZELLE)) {
				gaz = new GazellePOJO();
				gaz.setId(rs.getInt("id"));
				gaz.setIdAnimal(rs.getInt("idAnimal"));
				gaz.setLgCornes(rs.getInt("lgCornes"));
				ret.setGaz(gaz);
			}
		}
		return ret;
	}
	/**
	 * voir doc de l'interface
	 */
	@Override
	public List<CagePOJO> lireTous() {
		List<CagePOJO> ret = null;
		ResultSet rs = null;
		
		String req = "SELECT * FROM animal as gauche left join gazelle "
							+ "	as droite on gauche.idAnimal = droite.idAnimal;";
		try (Statement st = connecteur.getConn().createStatement()){
			ret = new Vector<>();
			rs = st.executeQuery(req);
			while(rs.next()) {
				ret.add(creerPOJO(rs));
			}
		} catch (SQLException e) {
			logger.log(Level.INFO,e.getMessage());
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				logger.log(Level.INFO,e.getMessage());
			}
		}
		return ret;
	}
	/**
	 * voir doc de l'interface
	 */
	@Override
	public void ecrireTous(List<CagePOJO> elts) {
		// vide !!!!! car n'a pas de sens avec les bases de données
		
	}

	/**
	 * utilise les requetes préparées pour les perfs et la securite
	 * Comme il s'agit d'un update sur plusieurs tables possibles
	 * les transactions sont beaucoup mieux
	 */
	@Override
	public void modifier(int cle, CagePOJO obj) {
		int nb = 0; //nb de lignes modifiées
		PreparedStatement prepareeCage = null;
		String requeteCage = "update animal set codeAnimal=?, x=?,y=?,nom=?,poids=?,age=? where idAnimal=? ";
		String requeteGazelle = "update gazelle set lgCornes=?  where idAnimal=?";
		PreparedStatement prepareeGazelle =null; 
		try {
			connecteur.getConn().setAutoCommit(false);
			prepareeCage = connecteur.getConn().prepareStatement(requeteCage);
			//cage pleine
			if (obj.getCodeAnimal() != null) {
				prepareeCage.setString(1,obj.getCodeAnimal());
			}else {
				prepareeCage.setNull(1, Types.VARCHAR);
			}
			prepareeCage.setInt(2,obj.getX());
			prepareeCage.setInt(3,obj.getY());
			prepareeCage.setString(4,obj.getNom());
			prepareeCage.setDouble(5,obj.getPoids());
			prepareeCage.setInt(6,obj.getAge());	
			prepareeCage.setInt(7,obj.getIdAnimal());
			nb = prepareeCage.executeUpdate();
			if((obj.getCodeAnimal() != null) && (obj.getCodeAnimal().equals(GAZELLE))){
				prepareeGazelle = connecteur.getConn().prepareStatement(requeteGazelle);
				prepareeGazelle.setInt(1,obj.getGaz().getLgCornes());
				prepareeGazelle.setInt(2,obj.getGaz().getIdAnimal());
				nb = prepareeGazelle.executeUpdate();
				prepareeGazelle.close();
			}
			if (nb != 0) {
				connecteur.getConn().commit();
			}else {
				//si update echoue (mauvaise clef par exemple)
				connecteur.getConn().rollback();
			}
			prepareeCage.close();
		} catch (SQLException e) {
			try {
				connecteur.getConn().rollback();
			} catch (SQLException e1) {
				logger.log(Level.INFO, e.getMessage());
			}
			logger.log(Level.INFO, e.getMessage());
		}finally {
			try {
				if(prepareeCage != null) {
						prepareeCage.close();
				}
				if(prepareeGazelle != null) {
					prepareeGazelle.close();
				}
			} catch (SQLException e) {
				logger.log(Level.INFO, e.getMessage());
			}
		}
		
	}

	/**
	 * voir doc de l'interface
	 */
	@Override
	public void effacer(int cle) {
		String requete = String.join(" ", "SELECT * FROM animal WHERE idAnimal =",Integer.toString(cle),";");
		ResultSet res = null;
	
		try (Statement st = connecteur.getConn().createStatement()){
			
			res = st.executeQuery(requete);
			if (res.next()) {
				effacer(creerPOJO(res));
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, e.getMessage());
		}finally {
			try {
				if(res != null) {
					res.close();
				}
			} catch (SQLException e) {
				logger.log(Level.INFO, e.getMessage());
			}
		}
	}

	/**
	 * voir doc de l'interface
	 */
	@Override
	public void effacer(CagePOJO obj) {
		String requete = String.join(" ","delete from animal where idAnimal= ",Integer.toString(obj.getIdAnimal()),";"); 
		String requeteGazelle =	 String.join(" ", "DELETE FROM animal WHERE idAnimal =",Integer.toString(obj.getIdAnimal()),";"); 
		
		Statement st = null;
		try {
			connecteur.getConn().setAutoCommit(false);
			st = connecteur.getConn().createStatement();
			st.executeUpdate(requete);
			if(obj.getCodeAnimal().equals(GAZELLE)) {
				st.executeUpdate(requeteGazelle);
			}
			connecteur.getConn().commit();
		}catch(SQLException e) {
			try {
				connecteur.getConn().rollback();
			} catch (SQLException e1) {
				logger.log(Level.INFO, e.getMessage());
			}
			logger.log(Level.INFO, e.getMessage());
		}finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					logger.log(Level.INFO, e.getMessage());
				}
			}
		}
	}

	/**
	 * voir doc de l'interface
	 */
	@Override
	public void ajouter(CagePOJO obj) {
		String requeteCage = String.join("", "insert into animal (idAnimal,codeAnimal, nom, age, poids, x, y) values (",
					Integer.toString(obj.getIdAnimal()),",'",obj.getCodeAnimal(),"','",obj.getNom(),"',",Integer.toString(obj.getAge()),",",
					Double.toString(obj.getPoids()),",",Integer.toString(obj.getX()),
					","+Integer.toString(obj.getY()),");");	
		String requeteGazelle = null;
		Statement st = null;
		try {
			connecteur.getConn().setAutoCommit(false);
			st = connecteur.getConn().createStatement();
			st.executeUpdate(requeteCage);
			if(obj.getCodeAnimal().equals(GAZELLE)) {
				requeteGazelle = String.join(" ", "insert into gazelle (id,idAnimal, lgCornes) values ('",
						Integer.toString(obj.getGaz().getId()),",",
						Integer.toString(obj.getIdAnimal()),",",Integer.toString(obj.getGaz().getLgCornes()),");");
				st.executeUpdate(requeteGazelle);
			}
			connecteur.getConn().commit();
		}catch(SQLException e) {
			try {
				connecteur.getConn().rollback();
			} catch (SQLException e1) {
				logger.log(Level.INFO, e.getMessage());
			}
			logger.log(Level.INFO, e.getMessage());
		}finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					logger.log(Level.INFO, e.getMessage());
				}
			}
		}
	}
	/**
	 * voir doc de l'interface
	 */
	@Override
	public CagePOJO lire(int cle) {
		CagePOJO ret = null;
		String requete = String.join(" ","SELECT * FROM animal as gauche left join gazelle as droite"
				,"on gauche.idAnimal = droite.idAnimal WHERE gauche.idAnimal =",Integer.toString(cle),";");
		Statement st  = null;
		ResultSet rs = null;
		
		try {
			st = connecteur.getConn().createStatement();
			rs = st.executeQuery(requete);
			if(rs.next()) {
					ret = creerPOJO(rs);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, e.getMessage());
		}finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					logger.log(Level.INFO, e.getMessage());
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.log(Level.INFO, e.getMessage());
				}
			}
		}
			
		return ret;
	}
	/** 
	 * juste pour vérifier que l'accès à la base fonctionne
	 * @param args pas utilisés
	 */
	public static void main(String[]args) {
		DaoJDBCImpl dao = new DaoJDBCImpl();
		dao.lireTous().forEach(System.out::println);
	}
}



