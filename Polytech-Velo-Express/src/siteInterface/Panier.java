package siteInterface;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.SessionManager;
import modele.utilisateurs.Client;
import modele.utilisateurs.TypeUtilisateur;

public class Panier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Panier() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(SessionManager.getUtilisateur(request) != null &&
			SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.CLIENT) {
			ResultSet res = ((Client)SessionManager.getUtilisateur(request)).getPanier();
			request.setAttribute("produitsPanier", res);
			getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);
		}
		else
			response.sendRedirect("index");
			
	}
	

}
