package org.formation.zoo.stockage;
import java.io.*;
import java.util.List;
import java.util.Vector;

import org.formation.zoo.modele.metier.Cage;
import org.formation.zoo.modele.metier.Gazelle;
import org.formation.zoo.modele.metier.Lion;
import org.formation.zoo.modele.metier.Singe;
import org.formation.zoo.modele.technique.CagePleineException;
import org.formation.zoo.modele.technique.PorteException;

/**
 * Classe technique qui gère les accès fichier pour la sauvegarde des animaux du zoo.
 * @author jacques
 *
 */
public class FichierAccess{

	/**
	 * nom du fichier
	 */
	private String fichier;
	/**
	 * Collection des animaux lus
	 */
	private List<Cage> elts;
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
		File f = new File(fichier);
		try {
			fic= new ObjectInputStream(new FileInputStream(fichier));
			elts = (Vector<Cage>)fic.readObject();
			fic.close();
		}
		catch (IOException e) {
			//fichier inexistant tout remplir à la main en appelant init
			init();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * méthode privée qui rempli la liste si aucun fichier n'est trouvé
	 */
	private void init()
	{
		Cage tmp = null;
		elts = new Vector<Cage>();
		try {
			tmp = new Cage(101,201);
			tmp.ouvrir();
				tmp.entrer(new Singe("Cheeta",20,75));
			tmp.fermer();
			elts.add(tmp);
			
			tmp = new Cage(100,50);
			tmp.ouvrir();
			tmp.entrer(new Lion("Simba",3,20));
			tmp.fermer();
			elts.add(tmp);
			
			tmp = new Cage(150,250); //CAGE VIDE
			tmp.fermer();
			elts.add(tmp);
			
			tmp = new Cage(90,230);
			tmp.ouvrir();
			tmp.entrer(new Gazelle("Lady Gaga",20,75,10));
			tmp.fermer();
			elts.add(tmp);
			
			tmp = new Cage(60,100);
			tmp.ouvrir();
			tmp.entrer(new Singe("Baloo",30,50));
			tmp.fermer();
			elts.add(tmp);
		} catch (PorteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CagePleineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}
	/**
	 * méthode qui permet l'accès en lecture à l'information (fait partie de l'api)
	 * @return la collection lue
	 */
	public List<Cage> lireTous(){
		if (elts == null){
			read();
		}
		return elts;
	}
	/**
	 * méthode qui permet l'accès en écriture à l'information (fait partie de l'api)
	 * @param lesCages la collection à persister
	 */
	public void ecrireTous(List<Cage> lesCages) {
		elts = lesCages;
		write();
		
	}
}
