package org.formation.zoo.controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Pour creer un animal et l'ajouter dans une cage vide
 * 
 * @author Vaianu
 *
 */
@WebServlet("/creer")
public class CreerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreerServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String typeAnimal = req.getParameter("typeAnimal");
		String nom = req.getParameter("nom");
		int age = Integer.parseInt(req.getParameter("age"));
		double poids = Double.parseDouble(req.getParameter("poids"));
		int lgCornes = 0;
		lgCornes = Integer.parseInt(req.getParameter("lgCornes"));
		int numCage = Integer.parseInt(req.getParameter("numCage"));
		String message = Manager.getInstance().creer(typeAnimal, nom, age, poids, lgCornes, numCage);
		req.getSession(false).setAttribute("etat", message);
		resp.sendRedirect("/projetzoo2020");
	}

}
