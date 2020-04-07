package org.formation.zoo.stockage;
import java.io.*;
import java.util.List;
import java.util.Vector;


/**s vD
 * Classe technique qui gère les accès fichier pour la sauvegarde des animaux du zoo.
 * @author jacques
 *
 */
public class FichierAccess<T> implements Dao<T>{

	/**
	 * nom du fichier
	 */
	private String fichier;
	/**
	 * Collection des animaux lus
	 */
	private List<T> elts;
	/**
	 * constructeur
	 * @param f nom du fichier à lire et écrire
	 */
	public FichierAccess(String f)
	{
		fichier = f;
		elts = null;
		read();
	}

	/**
	 * méthode privée d'écriture de la collection
	 */
	private void write()
	{
	      ObjectOutputStream fic = null;
	      try {
	             fic= new ObjectOutputStream(new FileOutputStream(fichier));
	             fic.writeObject(elts);
	             fic.close();
	        } catch (IOException e) {
	          System.out.println(" erreur :" + e.getMessage());
	        }			
	}
	/**
	 * méthode privée de lecture de la collection dans, le fichier<s
	 */
	private void read() {
	    ObjectInputStream fic = null;
		try {
			fic= new ObjectInputStream(new FileInputStream(fichier));
			elts = (Vector<T>)fic.readObject();
			fic.close();
		}
		catch (IOException e) {
			//fichier inexistant tout remplir à la main en appelant init
			DaoMemoire dm = new DaoMemoire();
			elts = (List<T>) dm.lireTous();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * méthode qui permet l'accès en lecture à l'information (fait partie de l'api)
	 * @return la collection lue
	 */
	@Override
	public List<T> lireTous(){
		if (elts == null){
			read();
		}
		return elts;
	}
	/**
	 * méthode qui permet l'accès en écriture à l'information (fait partie de l'api)
	 * @param lesCages la collection à persister
	 */
	@Override
	public void ecrireTous(List<T> lesCages) {
		if(lesCages !=null) {
			elts = lesCages;
		}
		write();
		
	}

	@Override
	public void modifier(int cle, T obj) {
		elts.set(cle, obj);
		ecrireTous(elts);
	}

	@Override
	public void effacer(int cle) {
		elts.remove(cle);
		ecrireTous(elts);
	}

	@Override
	public void effacer(T obj) {
		elts.remove(obj);
		ecrireTous(elts);
	}

	@Override
	public void ajouter(T obj) {
		elts.add(obj);
		ecrireTous(elts);
		
	}

	@Override
	public T lire(int cle) {
		return elts.get(cle);	
	}
}
