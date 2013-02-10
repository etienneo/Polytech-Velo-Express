package modele.utilisateurs;

import java.sql.ResultSet;
import java.sql.SQLException;

import modele.MySQLManager;

public class GestionnairePVE extends Utilisateur {
	
	private int idGestionnaire;
	
	public GestionnairePVE(String login) {
		super(login);
		typeUtilisateur = TypeUtilisateur.GESTIONNAIRE;

		
		MySQLManager mysql = MySQLManager.getMySQLManager();
		
		ResultSet res = mysql.execRequest("SELECT * FROM Client WHERE login='" + login + "';");
		
		try {
			res.next();
			idGestionnaire = res.getInt("idClient");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public GestionnairePVE(String login, String motDePasse, String nom, String prenom, 
			String adresse, String ville, String codePostal, String mail, String tel) {
		super(login, motDePasse, nom, prenom, adresse, ville, codePostal, mail, tel);
	}
	
	/**
	 * Insère l'utilisateur dans la base de données
	 */
	public void insert() {
		super.insert();
		MySQLManager mysql = MySQLManager.getMySQLManager();
		mysql.execModif("INSERT INTO gestionnairepve (login) " +
				"VALUES ('" + login + "');");

		ResultSet res = mysql.execRequest("SELECT idGestionnaire FROM gestionnairepve WHERE login='" + login + "';");
		try {
			res.next();
			idGestionnaire = res.getInt("idGestionnaire");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "GestionnairePVE [idGestionnaire=" + idGestionnaire
				+ ", toString()=" + super.toString() + "]";
	}

}
