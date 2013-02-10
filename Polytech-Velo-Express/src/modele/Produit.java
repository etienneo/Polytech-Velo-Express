package modele;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Produit {
	
	private int idProduit = -1;
	private String nom;
	private String referenceProduit;
	private int quantite = 0;
	private String description;
	private float prix = 0;
	private int nbReserve = 0;
	private int nbVendu = 0;
	private int idCommercant = -1;
	
	public Produit(int idProduit) {
		this.idProduit = idProduit;
		retrieveData(idProduit);
	}
	
	public Produit(String nom, String reference, int quantite, String description, float prix, int idCommercant) {
		this.nom = nom;
		this.referenceProduit = reference;
		this.quantite = quantite;
		this.description = description;
		this.prix = prix;
		this.idCommercant = idCommercant;
	}
	
	public Produit(int idProduit, String nom, String reference, int quantite, String description, float prix, int idCommercant) {
		this.idProduit = idProduit;
		this.nom = nom;
		this.referenceProduit = reference;
		this.quantite = quantite;
		this.description = description;
		this.prix = prix;
		this.idCommercant = idCommercant;
	}
	
	public void retrieveData(int idProduit) {
		MySQLManager mysql = MySQLManager.getMySQLManager();
		ResultSet res = mysql.execRequest("SELECT * FROM produit WHERE idProduit = '" + idProduit + "';");
		
		try {
			res.next();
			nom = res.getString("nom");
			referenceProduit = res.getString("referenceProduit");
			quantite = res.getInt("quantite");
			description = res.getString("description");
			prix = res.getFloat("prix");
			nbReserve = res.getInt("nbReserve");
			nbVendu = res.getInt("nbVendu");
			idCommercant = res.getInt("idCommercant");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert() {
		MySQLManager mysql = MySQLManager.getMySQLManager();
		return mysql.execModif("INSERT INTO Produit " +
				"(nom, referenceProduit, quantite, description, prix, nbReserve, nbVendu, idCommercant) VALUES " +
				"('" + nom + "', '" + referenceProduit + "', " + quantite + ", '" + description +"', " +
						prix + ", " + nbReserve + ", " + nbVendu + ", " + idCommercant + ");"
				);
	}
	
	public int update() {
		MySQLManager mysql = MySQLManager.getMySQLManager();
		return mysql.execModif("UPDATE Produit SET " +
				"nom='" + nom + "', referenceProduit='" + referenceProduit + "', quantite=" + quantite + ", " +
				"description='" + description +"', prix=" + prix +" WHERE idProduit=" + idProduit + ";");
	}
	
	public static boolean appartientCommercant(int idProduit, int idCommercant) {
		MySQLManager mysql = MySQLManager.getMySQLManager();
		ResultSet res = mysql.execRequest("SELECT COUNT(*) AS appartient FROM produit WHERE idProduit='" + idProduit + "' AND idCommercant='" + idCommercant + "';");
		
		boolean appartient = false;
		try {
			res.next();
			appartient = res.getBoolean("appartient");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appartient;
	}

	public int getIdProduit() {
		return idProduit;
	}

	public String getNom() {
		return nom;
	}

	public String getReferenceProduit() {
		return referenceProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public String getDescription() {
		return description;
	}

	public float getPrix() {
		return prix;
	}

	public int getNbReserve() {
		return nbReserve;
	}

	public int getNbVendu() {
		return nbVendu;
	}

	public int getIdCommercant() {
		return idCommercant;
	}

}
