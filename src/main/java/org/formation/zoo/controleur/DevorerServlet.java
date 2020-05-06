package org.formation.zoo.controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Vaianu
 *
 */
@WebServlet("/devorer")
public class DevorerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DevorerServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("mangeur") != null && req.getParameter("mange") != null)
		{
			int mangeur = Integer.parseInt(req.getParameter("mangeur"));
			int mange = Integer.parseInt(req.getParameter("mange"));
			String message = Manager.getInstance().devorer(mangeur, mange);
			req.getSession(false).setAttribute("etat", message);
			resp.sendRedirect("/projetzoo2020");
		}
		else
		{
			String message = "Veuillez cocher un animal mangeur et un animal mange";
			req.getSession(false).setAttribute("etat", message);
			resp.sendRedirect("/projetzoo2020");
		}
	}

}
