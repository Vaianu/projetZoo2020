package org.formation.zoo.modele.metier;

/**
 * Classe concrète représentant un singe.
 * Elle est dérivable
 * @author j.vincensini
 *
 */
public class Singe extends Animal {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Singe()
	{
	    this("SSSSS",1,30);
	}
	public Singe(String n, int a, double p)
	{
		super(n,a,p,100);
	}
	@Override
	public void manger()
	{
		setPoids(getPoids()+1.4); 
	}
	@Override
	public void courir()
	{
		
	}
	@Override
	public void dormir()
	{
		setPoids(getPoids()-0.8); 
				
	}
	@Override
	public void sauter()
	{
		
	}

	
	
	
}
