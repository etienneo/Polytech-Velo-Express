package siteInterface;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.utilisateurs.Livreur;

public class Accueil extends HttpServlet {
	
	public Accueil() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Livreur c = new Livreur("lauru", "zzz", "Ruquier", "Laurent", 
				"partout", "Paris", "01000", "ruq@b.fr", 654525886, true);
		c.insert();
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
