package modele.utilisateurs;

import java.sql.ResultSet;
import java.sql.SQLException;

import modele.MySQLManager;

public class Livreur extends Utilisateur {
	
	private int idLivreur;
	private boolean disponibilite;
	
	public Livreur (String login) {
		super(login);
		typeUtilisateur = TypeUtilisateur.LIVREUR;

		
		MySQLManager mysql = MySQLManager.getMySQLManager();
		
		ResultSet res = mysql.execRequest("SELECT * FROM LIVREUR WHERE login='" + login +"';");
		
		try {
			res.next();
			idLivreur = res.getInt("idLivreur");
			disponibilite = res.getBoolean("disponibilite");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Livreur(String login, String motDePasse, String nom, String prenom, 
			String adresse, String ville, String codePostal, String mail, String tel, 
			boolean disponibilite) {
		super(login, motDePasse, nom, prenom, adresse, ville, codePostal, mail, tel);
		this.disponibilite = disponibilite;
	}
	
	/**
	 * Insère l'utilisateur dans la base de données
	 */
	public void insert() {
		super.insert();
		
		int valDispo = (disponibilite) ? 1 : 0;
		
		MySQLManager mysql = MySQLManager.getMySQLManager();
		mysql.execModif("INSERT INTO Livreur (login, disponibilite) " +
				"VALUES ('" + login + "', '" + valDispo + "');");

		ResultSet res = mysql.execRequest("SELECT idLivreur FROM Livreur WHERE login='" + login + "';");
		try {
			res.next();
			idLivreur = res.getInt("idLivreur");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int changerDisponibilite() {
		MySQLManager mysql = MySQLManager.getMySQLManager();
		int nvDispo = (disponibilite) ? 0 : 1;
		int ret = mysql.execModif("UPDATE Livreur SET " +
				"disponibilite=" + nvDispo + " WHERE idLivreur=" + idLivreur + ";");
		if(ret > 0)
			disponibilite = !disponibilite;
		return ret;
	}

	@Override
	public String toString() {
		return "Livreur [idLivreur=" + idLivreur + ", disponibilite="
				+ disponibilite + ", toString()=" + super.toString() + "]";
	}
	
	public boolean getDisponibilite() {
		return disponibilite;
	}

}
