package modele;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Commande {
	private int idCommande;
	private int idClient;
	private int idCommercant;
	private float coutCommande;
	
	public Commande(int idCommande) {
		this.idCommande = idCommande;
		
		MySQLManager mysql = MySQLManager.getMySQLManager();
		ResultSet res = mysql.execRequest("SELECT * FROM Commande WHERE idCommande=" + idCommande + ";");
		
		try {
			// Le Commande existe deja
			if(res.next()) {
				idCommande = res.getInt("idCommande");
				idClient = res.getInt("idClient");
				idCommercant = res.getInt("idCommercant");
				coutCommande = res.getFloat("coutCommande");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Commande(int idClient, int idCommercant) {
		this.idClient = idClient;
		this.idCommercant = idCommercant;
		this.coutCommande = 0;
		MySQLManager mysql = MySQLManager.getMySQLManager();
		if(mysql.execModif("INSERT INTO Commande (coutCommande, idCommercant, idClient) VALUES " +
				"(0, " + idCommercant + ", " + idClient + ");") > 0 )
		{
			ResultSet res = mysql.execRequest("SELECT * FROM Commande WHERE idClient=" + idClient + " AND idCommercant=" + idCommercant +
					" ORDER BY idCommande DESC;");
			try {
				res.next();
				this.idCommande = res.getInt("idCommande");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("merde");
	}
	
	public void ajout(Produit produit, int nbArticle) {
		
		produit.validerAchat(nbArticle);

		MySQLManager mysql = MySQLManager.getMySQLManager();
		mysql.execModif("INSERT INTO commandecontientproduit (idCommande, idProduit, quantite) " +
					"VALUES (" + idCommande + ", " + produit.getIdProduit() + ", " + nbArticle + ");");
		
		coutCommande += produit.getPrix() * nbArticle;
		mysql.execModif("UPDATE commande SET coutCommande=" + coutCommande + " WHERE idCommande=" + idCommande + " ;");
		
	}

	public float getCoutCommande() {
		return coutCommande;
	}

	public int getIdCommande() {
		return idCommande;
	}

	public int getIdCommercant() {
		return idCommercant;
	}

	public int getIdClient() {
		return idClient;
	}

}
