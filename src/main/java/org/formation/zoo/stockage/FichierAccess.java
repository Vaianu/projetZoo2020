package org.formation.zoo.stockage;
import java.io.*;
import java.util.List;
import java.util.Vector;

/**
 * Classe technique qui gère les accés fichier pour la sauvegarde des animaux du zoo.
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
	 * @param f nom du fichier à  lire et écrire
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
	 * méthode privée de lecture de la collection dans le fichier
	 */
	private void read() {
	    ObjectInputStream fic = null;
		File f = new File(fichier);
		try {
			fic= new ObjectInputStream(new FileInputStream(fichier));
			elts = (Vector<T>)fic.readObject();
			fic.close();
		}
		catch (IOException e) {
			DaoMemoire dm = new DaoMemoire(); // fichier inexistant
        	elts = (List<T>) dm.lireTous(); 
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}


	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}
	/**
	 * mÃ©thode qui permet l'accÃ¨s en lecture Ã  l'information (fait partie de l'api)
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
	 * méthode qui permet l'accés en écriture à l'information (fait partie de l'api)
	 * @param lesCages la collection Ã  persister
	 */
	@Override
	public void ecrireTous(List<T> lesCages) {
		elts = lesCages;
		write();
		
	}

	@Override
	public void modifier(int cle, T obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effacer(int cle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effacer(T obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouter(T obj) {
		// TODO Auto-generated method stub
		
	}
}
