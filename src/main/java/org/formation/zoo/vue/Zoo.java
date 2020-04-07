package org.formation.zoo.vue;

import java.util.List;
import java.util.Vector;

import org.formation.zoo.controleur.Manager;
import org.formation.zoo.modele.metier.*;
import org.formation.zoo.modele.technique.BeurkException;
import org.formation.zoo.modele.technique.CagePleineException;
import org.formation.zoo.modele.technique.PorteException;
import org.formation.zoo.stockage.FichierAccess;

/**
 * Programme PRINCIPAL 
 * Contient le main
 * @author j.Vincensini
 *
 */
public final class Zoo {
	public Zoo() {
		//Manager.getInstance();
	}
	
	public void afficher()
	{
		String[] infosAnimaux = Manager.getInstance().afficher();
		for(int i=0;i<infosAnimaux.length;i++)
		{
			System.out.println(infosAnimaux[i].toString());
		}
		
		/*Manager.getInstance().afficher().forEach(e->{
			System.out.println(e.toString());
		});*/
		
		//ArrayStoreException.asLManager.getInstance().afficher().
		//stream().forEach(System.out::println);
	}
	
	/**
	 * Permet de nourrir tous les animaux du zoo
	 */
	public void nourrir()
	{
		Manager.getInstance().nourrir();
	}
	/**
	 * 
	 * @param mangeur indice de l'animal mangeur (sa cage)
	 * @param mange indice de la cage de la proie
	 * @return le texte sur ce qu'il s'est passé
	 */
	/*public void devorer(int mangeur, int mange)
	{
		System.out.println( Manager.getInstance().devorer(mangeur, mange));
	}*/
	
	/*public void fermer() {
		Manager.getInstance().fermer();
	}*/
	
	public static void main(String[] args) {
		Zoo z = null;
		z = new Zoo();
					
		z.afficher(); 
		System.out.println("on fait manger tous les animaux");
		z.nourrir();
		z.afficher();
		System.out.println("on ferme le zoo");
	}

}
