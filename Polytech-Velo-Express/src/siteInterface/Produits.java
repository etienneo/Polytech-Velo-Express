package siteInterface;

import java.io.IOException;

import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.MySQLManager;
import modele.SessionManager;
import modele.utilisateurs.Commercant;
import modele.utilisateurs.TypeUtilisateur;


public class Produits extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public Produits()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// On vérifie si l'on a à faire à un commercant
		 boolean modifCommercant = (request.getParameter("consult") != null && 
				 	Integer.valueOf(request.getParameter("consult")) == 1 &&
					SessionManager.getUtilisateur(request) != null &&
					SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.COMMERCANT);
		
		MySQLManager mysql = MySQLManager.getMySQLManager();
		String sqlRequest;
		if(modifCommercant) {
			request.setAttribute("modifCommercant", new Boolean(true));
			int idCommercant = ((Commercant)SessionManager.getUtilisateur(request)).getIdCommercant();
			sqlRequest = "SELECT * FROM (produit NATURAL JOIN commercant) WHERE idCommercant=" + idCommercant + ";";
		}
		else
			sqlRequest = "SELECT * FROM (produit NATURAL JOIN commercant) WHERE (quantite - nbReserve) > 0;";
		
		ResultSet res = mysql.execRequest(sqlRequest);
		
		request.setAttribute("title", "Produits");
		request.setAttribute("produits", res);
		
		getServletContext().getRequestDispatcher("/produits.jsp").forward(request, response);
	}
}
