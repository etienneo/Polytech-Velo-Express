package siteInterface;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Commande;
import modele.MySQLManager;
import modele.Produit;
import modele.SessionManager;
import modele.utilisateurs.Client;
import modele.utilisateurs.Commercant;
import modele.utilisateurs.TypeUtilisateur;

public class ValidationPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ValidationPanier() {
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
			
			int idClient = ((Client)SessionManager.getUtilisateur(request)).getIdClient();
			ResultSet res = ((Client)SessionManager.getUtilisateur(request)).getPanier();
			
			class Pdt {
				int idCommercant = 0;
				int idProduit = 0;
				int qte = 0;
				Pdt(int c, int p, int q) {
					this.idCommercant = c;
					this.idProduit = p;
					this.qte = q;
				}
			}
			
			int idPanier = -1;
			
			// Récupération de la liste des produits
			ArrayList<Pdt> produits = new ArrayList<Pdt>();
			try {
				if(res.next()) {
					produits.add(new Pdt(res.getInt("idCommercant"), res.getInt("idProduit"), res.getInt("qte")));
					idPanier = res.getInt("idPanier");
				}
				while(res.next()) {
					produits.add(new Pdt(res.getInt("idCommercant"), res.getInt("idProduit"), res.getInt("qte")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// Création des commandes
			int precCommercant = -1;
			Commande c = null;
			for (Pdt pdt : produits) {
				if(precCommercant != pdt.idCommercant)
				{
					c = new Commande(idClient, pdt.idCommercant);
					c.ajout(new Produit(pdt.idProduit), pdt.qte);
				}
				else {
					c.ajout(new Produit(pdt.idProduit), pdt.qte);
				}
				precCommercant = pdt.idCommercant;
			}
			
			MySQLManager mysql = MySQLManager.getMySQLManager();
			mysql.execModif("DELETE FROM panier WHERE idPanier=" + idPanier +";");

			request.setAttribute("message", "La validation du panier est effectuée");
			getServletContext().getRequestDispatcher("/panier").forward(request, response);
		}
		else
			response.sendRedirect("index");
			
	}

}
