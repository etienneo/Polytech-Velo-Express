package siteInterface;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.SessionManager;
import modele.utilisateurs.TypeUtilisateur;

public class AjoutPanier extends HttpServlet {
	
	public AjoutPanier() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("messageAjout", "L'ajout a échoué");
		getServletContext().getRequestDispatcher("/produits").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(!SessionManager.estConnecte(request))
			getServletContext().getRequestDispatcher("/connexion").forward(request, response);
		else {
			if(SessionManager.getUtilisateur(request) != null 
				&& SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.CLIENT) {
				if(request.getParameter("idArticle") != null && request.getParameter("nbArticle") != null) {
					int idArticle = Integer.valueOf(request.getParameter("idArticle"));
					int nbArticle = Integer.valueOf(request.getParameter("nbArticle"));
					
					
					if(nbArticle == 1)
						request.setAttribute("messageAjout", "L'ajout au panier de " + idArticle + " s'est bien effectué");
					else 
						request.setAttribute("messageAjout", "L'ajout au panier de " + nbArticle + " " + idArticle + " s'est bien effectué");
				}
				else {
					request.setAttribute("messageAjout", "L'ajout a échoué");
				}
			}
			else {
				request.setAttribute("messageAjout", "L'ajout a échoué, vous n'êtes pas un client");
			}
			
			getServletContext().getRequestDispatcher("/produits").forward(request, response);
		}
	}
	
	//"L'ajout au panier de (2) bla s'est bien effectué"
}
