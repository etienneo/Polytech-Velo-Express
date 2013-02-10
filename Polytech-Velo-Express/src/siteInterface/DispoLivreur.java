package siteInterface;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Produit;
import modele.SessionManager;
import modele.utilisateurs.TypeUtilisateur;

public class DispoLivreur extends HttpServlet {
	
	public DispoLivreur() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(SessionManager.getUtilisateur(request) != null &&
				SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.LIVREUR)
			getServletContext().getRequestDispatcher("/dispolivreur.jsp").forward(request, response);
		else
			response.sendRedirect("index");
	
	}

}
