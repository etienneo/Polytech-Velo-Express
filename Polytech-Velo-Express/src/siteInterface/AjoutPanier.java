package siteInterface;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Panier;
import modele.Produit;
import modele.SessionManager;
import modele.utilisateurs.TypeUtilisateur;
import modele.utilisateurs.Client;

public class AjoutPanier extends HttpServlet {
	
	public AjoutPanier() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("messageAjout", "L'ajout a �chou�");
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(!SessionManager.estConnecte(request))
			response.sendRedirect("connexion");
		else {
			if(SessionManager.getUtilisateur(request) != null 
				&& SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.CLIENT) {
				if(request.getParameter("idArticle") != null && request.getParameter("nbArticle") != null) {
					int idArticle = Integer.valueOf(request.getParameter("idArticle"));
					int nbArticle = Integer.valueOf(request.getParameter("nbArticle"));
					int idClient = ((Client)SessionManager.getUtilisateur(request)).getIdClient();
					
					Panier panier = new Panier(idClient);
					
					Produit produit = new Produit(idArticle);
					
					if(produit.getNom() != null)
					{
						if(panier.ajout(produit, nbArticle)) {
							if(nbArticle == 1)
								request.setAttribute("messageAjout", "L'ajout au panier de " + produit.getNom() + " s'est bien effectu�");
							else 
								request.setAttribute("messageAjout", "L'ajout au panier de " + nbArticle + " " + produit.getNom() + " s'est bien effectu�");
						}
						else
							request.setAttribute("messageAjout", "L'ajout a �chou�, trop peu de produits sont disponibles");
					}
					else
						request.setAttribute("messageAjout", "L'article n'a pas �t� trouv�");
				}
				else
					request.setAttribute("messageAjout", "L'ajout a �chou�");
			}
			else
				request.setAttribute("messageAjout", "L'ajout a �chou�, vous n'�tes pas un client");
			
			getServletContext().getRequestDispatcher("/produits").forward(request, response);
		}
	}
}
