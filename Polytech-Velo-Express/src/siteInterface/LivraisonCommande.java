package siteInterface;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import javax.servlet.http.HttpServlet;
import modele.MySQLManager;
import modele.SessionManager;
import modele.utilisateurs.Livreur;
import modele.utilisateurs.TypeUtilisateur;

public class LivraisonCommande extends HttpServlet
{	
	private static final long serialVersionUID = 1L;
	
	public LivraisonCommande() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		// TODO Auto-generated method stub
    	doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Récupération de l'id du livreur
		if(SessionManager.getUtilisateur(request) != null 
				&& SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.LIVREUR) {
			int idLivreur = ((Livreur)SessionManager.getUtilisateur(request)).getIdLivreur();
			
			MySQLManager mysql = MySQLManager.getMySQLManager();

			ResultSet res = mysql.execRequest("SELECT idCommande, produit.nom AS nomProduit, " +
					"referenceProduit, commandeContientProduit.quantite AS quantite, utilisateur.nom AS nomClient, " +
					"prenom, adresse, ville, codePostal, tel, nomBoutique " +
					"FROM (commande NATURAL JOIN " +
					"(commandeContientProduit JOIN Produit ON commandeContientProduit.idProduit = produit.idProduit) " +
					"NATURAL JOIN livraisonCommande " +
					"JOIN Commercant ON commande.idCommercant = Commercant.idCommercant " +
					"JOIN Client ON commande.idClient = Client.idClient " +
					"JOIN Utilisateur ON Client.login = Utilisateur.login) WHERE idLivreur=" + idLivreur + ";");
			
			request.setAttribute("title", "Livraison des Commandes");
			request.setAttribute("livraisonCommande", res);
			
			getServletContext().getRequestDispatcher("/livraisonCommande.jsp").forward(request, response);
			
		}
		else
			response.sendRedirect("index");
			

	}
}
