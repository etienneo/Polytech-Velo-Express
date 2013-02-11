package modele;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Panier {
	
	private int idPanier;
	private int idClient;
	
	public Panier(int idClient) {
		this.idClient = idClient;
		
		MySQLManager mysql = MySQLManager.getMySQLManager();
		ResultSet res = mysql.execRequest("SELECT * FROM panier WHERE idClient=" + idClient + ";");
		
		try {
			// Le panier existe deja
			if(res.next()) {
				idPanier = res.getInt("idPanier");
			}
			// Le panier n'existe pas
			else {
				creerPanier(idClient);
				res = null;
				res = mysql.execRequest("SELECT * FROM panier WHERE idClient=" + idClient + ";");
				res.next();
				idPanier = res.getInt("idPanier");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int creerPanier(int idClient) {
		MySQLManager mysql = MySQLManager.getMySQLManager();
		return mysql.execModif("INSERT INTO panier (idClient) VALUES (" + idClient +");");
	}
	
	public boolean ajout(Produit produit, int nbArticle) {
		
		if(produit.reserver(nbArticle)) {

			MySQLManager mysql = MySQLManager.getMySQLManager();
			ResultSet res = mysql.execRequest("SELECT quantite FROM paniercontientproduit " +
					"WHERE idPanier=" + idPanier + " AND idProduit=" + produit.getIdProduit() + ";");
			
			try {
				if(res.next()) {
					mysql.execModif("UPDATE paniercontientproduit SET quantite=" + (nbArticle + res.getInt("quantite")) +
							" WHERE idPanier=" + idPanier + " AND idProduit=" + produit.getIdProduit() + ";");
				}
				else {
					mysql.execModif("INSERT INTO paniercontientproduit (idPanier, idProduit, quantite) " +
						"VALUES (" + idPanier + ", " + produit.getIdProduit() + ", " + nbArticle + ");");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return true;
		}
		return false;
	}

	public int getIdPanier() {
		return idPanier;
	}

	public int getIdClient() {
		return idClient;
	}
}