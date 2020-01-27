package org.formation.zoo.modele;

/**
 * Classe représentant une gazelle. 
 * C'est une proie pour le lion, donc elle implémente l'interface Mangeable
 * @author j.vincensini
 *
 */
public final class Gazelle extends Animal implements Mangeable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int lgCornes;
		
	public Gazelle()
	{
		this("GGGGG",1,30,10);
		lgCornes = 20;
	}
	public Gazelle(String n, int a, double p, int l) 
	{
		super(n,a,p,150);
		setLgCornes(l);
	}

	public int getLgCornes() {
		return lgCornes;
	}

	public void setLgCornes(int lgCornes) {
		this.lgCornes = lgCornes;
	}
	@Override
	public void manger()
	{
		setPoids(getPoids()+0.8);
	}
	@Override
	public void courir()
	{
		setPoids(getPoids()-0.2);
	}
	@Override
	public void dormir()
	{
		setPoids(getPoids()-0.4);
				
	}
	@Override
	public void finalize()
	{
		System.out.println("Arghhhhhh!!!! je meurs!");
	}
	
	@Override
	public String toString() {
		return super.toString() +  ", ses cornes mesurent " + lgCornes + " cm";
	}
	@Override
	public double prelever() {
		return getPoids()/3;
	}
}
