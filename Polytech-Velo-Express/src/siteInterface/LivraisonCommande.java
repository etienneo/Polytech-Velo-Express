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
				&& SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.LIVREUR)
			((Livreur)SessionManager.getUtilisateur(request)).getIdLivreur();
		else
			response.sendRedirect("index");
		
		MySQLManager mysql = MySQLManager.getMySQLManager();

		ResultSet res = mysql.execRequest("SELECT * FROM (commande NATURAL JOIN commandeContientProduit NATURAL JOIN livraisonCommande);");
		//ResultSet res2 = mysql.execRequest("SELECT * FROM (utilisateur NATURAL JOIN commande);");
		
		request.setAttribute("title", "Livraison des Commandes");
		request.setAttribute("livraisonCommande", res);
		//request.setAttribute("info", res2);
		
		getServletContext().getRequestDispatcher("/livraisonCommande.jsp").forward(request, response);

	}
}
