package siteInterface;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Produit;
import modele.SessionManager;
import modele.utilisateurs.TypeUtilisateur;
import modele.utilisateurs.Utilisateur;

public class AjoutProduit extends HttpServlet {
	
	public AjoutProduit() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if(request.getParameter("idModif") != null &&
			request.getParameter("nom") != null && request.getParameter("nom") != "" &&
			request.getParameter("reference") != null && request.getParameter("reference") != "" &&
			request.getParameter("quantite") != null && Integer.valueOf(request.getParameter("quantite")) >= 0 &&
			request.getParameter("prix") != null && Float.valueOf(request.getParameter("prix")) >= 0 &&
			request.getParameter("description") != null && request.getParameter("description") != "" &&
			request.getParameter("idCommercant") != null && Integer.valueOf(request.getParameter("idCommercant")) > -1) {
			
			if(Integer.valueOf(request.getParameter("idModif")) == -1) {
				Produit p = new Produit(request.getParameter("nom"), 
						request.getParameter("reference"), 
						Integer.valueOf(request.getParameter("quantite")), 
						request.getParameter("description"), 
						Float.valueOf(request.getParameter("prix")), 
						Integer.valueOf(request.getParameter("idCommercant")));
				
				if(p.insert() > 0)
					request.setAttribute("message", "L'ajout du produit s'est bien déroulé");
				else
					request.setAttribute("message", "L'ajout du produit a échoué");
			}
			else {
				Produit p = new Produit(Integer.valueOf(request.getParameter("idModif")),
						request.getParameter("nom"), 
						request.getParameter("reference"), 
						Integer.valueOf(request.getParameter("quantite")), 
						request.getParameter("description"), 
						Float.valueOf(request.getParameter("prix")), 
						Integer.valueOf(request.getParameter("idCommercant")));
				
				if(p.update() > 0)
					request.setAttribute("message", "La modification du produit s'est bien déroulée");
				else
					request.setAttribute("message", "La modification du produit a échoué");
			}
		}
		
		
		if(SessionManager.getUtilisateur(request) != null &&
				SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.COMMERCANT)
			getServletContext().getRequestDispatcher("/ajoutproduit.jsp").forward(request, response);
		else
			response.sendRedirect("index");
	
	}
}
