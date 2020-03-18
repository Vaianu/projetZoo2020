package org.formation.zoo.modele.metier;

/**
 * Classe concrète représentant un lion.
 * Elle est FINALE!!!!
 * @author SM Vaianu
 *
 */
public final class Lion extends Animal {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Lion()
	{	
		this("LLLLL",1,30);
	}
	
	public Lion(String n, int a, double p)
	{
		super(n,a,p,300);
	}
	@Override		
	public void manger()
	{
		setPoids(getPoids()+2.1);
	}
	@Override
	@Deprecated
	public String manger(Gazelle g)
	{
		if (g != null)
		{
			setPoids(getPoids()+(g.getPoids()/3));
		}
		return "MIAM";
	}
	@Override
	@Deprecated
	public String manger(Individu i) {
		
		/*if (i != null)
		{
			setPoids(getPoids()+(i.getPoids()/3));
		}*/
		return "MIAM";
	}
	@Override
	public String manger(Mangeable m) {
		
		if (m != null)
		{
			setPoids(getPoids()+(m.prelever()));
		}
		return "MIAM";
	}
	@Override
	public String crier()
	{
		return "Je rugi rhoaaaa";
	}
	@Override
	public void dormir()
	{
		setPoids(getPoids()-1);
	}
	
	@Override
	public void sauter()
	{
		setPoids(getPoids()-0.7);
	}

	
	

}
