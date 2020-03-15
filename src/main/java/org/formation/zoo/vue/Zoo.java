package org.formation.zoo.vue;

import org.formation.zoo.controleur.Manager;

/**
 * Programme PRINCIPAL 
 * Contient le main
 * @author j.Vincensini
 *
 */
public final class Zoo {
	
		
	public Zoo() {
	}
	/**
	 * Méthode privée qui charge le modèle.
	 * Pour l'instant elle instancie les animaux
	 */
	
	public void afficher()
	{
		/*Manager.getInstance().afficher().forEach(e->{
			System.out.println(e.toString());
		});*/
		
		String[] infosAnimaux = Manager.getInstance().afficher();
		for(int i=0; i<infosAnimaux.length; i++)
		{
			System.out.println(infosAnimaux[i].toString());
		}
	}
	/**
	 * Permet de nourrir tous les animaux du zoo
	 */
	public void nourrir ()
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
		System.out.println(Manager.getInstance().devorer(mangeur, mange));
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
//		System.out.println(z.devorer(1,0));
//		z.devorer(1, 0);
		System.out.println("on ferme le zoo");
//		z.fermer();
	}

}
