package modele.utilisateurs;

import java.sql.ResultSet;
import java.sql.SQLException;

import modele.MySQLManager;

public class Commercant extends Utilisateur {
	
	private int idCommercant;
	private String nomBoutique;
	
	public Commercant(String login) {
		super(login);
		typeUtilisateur = TypeUtilisateur.COMMERCANT;
		
		MySQLManager mysql = MySQLManager.getMySQLManager();
		
		ResultSet res = mysql.execRequest("SELECT * FROM Commercant WHERE login='" + login + "';");
		
		try {
			res.next();
			idCommercant = res.getInt("idCommercant");
			nomBoutique = res.getString("nomBoutique");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Commercant(String login, String motDePasse, String nom, String prenom, 
			String adresse, String ville, String codePostal, String mail, String tel, 
			String nomBoutique) {
		super(login, motDePasse, nom, prenom, adresse, ville, codePostal, mail, tel);
		this.nomBoutique = nomBoutique;
	}
	
	/**
	 * Insère l'utilisateur dans la base de données
	 */
	public void insert() {
		super.insert();
		MySQLManager mysql = MySQLManager.getMySQLManager();
		mysql.execModif("INSERT INTO Commercant (login, nomBoutique) " +
				"VALUES ('" + login + "', '" + nomBoutique + "');");

		ResultSet res = mysql.execRequest("SELECT idCommercant FROM Commercant WHERE login='" + login + "';");
		try {
			res.next();
			idCommercant = res.getInt("idCommercant");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getIdCommercant() {
		return idCommercant;
	}

	@Override
	public String toString() {
		return "Commercant [idCommercant=" + idCommercant + ", nomBoutique="
				+ nomBoutique + ", toString()=" + super.toString() + "]";
	}

}
