package modele.utilisateurs;

import java.sql.ResultSet;
import java.sql.SQLException;

import modele.MySQLManager;

public class Client extends Utilisateur {

	private int idClient;

	public Client(String login) {
		super(login);
		typeUtilisateur = TypeUtilisateur.CLIENT;
		
		MySQLManager mysql = MySQLManager.getMySQLManager();
		
		ResultSet res = mysql.execRequest("SELECT * FROM Client WHERE login='" + login + "';");
		
		try {
			res.next();
			idClient = res.getInt("idClient");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Client(String login, String motDePasse, String nom, String prenom, 
			String adresse, String ville, String codePostal, String mail, String tel) {
		super(login, motDePasse, nom, prenom, adresse, ville, codePostal, mail, tel);
	}
	

	/**
	 * Insère l'utilisateur dans la base de données
	 */
	public void insert() {
		super.insert();
		MySQLManager mysql = MySQLManager.getMySQLManager();
		mysql.execModif("INSERT INTO client (login) VALUES ('" + login + "');");

		ResultSet res = mysql.execRequest("SELECT idClient FROM Client WHERE login='" + login + "';");
		try {
			res.next();
			idClient = res.getInt("idClient");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getPanier() {
		MySQLManager mysql = MySQLManager.getMySQLManager();
		return mysql.execRequest("SELECT idPanier, idClient, nom, paniercontientproduit.quantite, prix " +
				"FROM (panier NATURAL JOIN paniercontientproduit) " +
				"JOIN produit ON paniercontientproduit.idProduit = produit.idProduit " +
				"WHERE idClient=" + idClient + ";");
	}
	
	public int getIdClient() {
		return idClient;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", toString()="
				+ super.toString() + "]";
	}

}
