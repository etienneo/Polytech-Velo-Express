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
		
		ResultSet res = mysql.exec("SELECT * FROM Commercant WHERE login='" + login + "';");
		
		try {
			res.next();
			idCommercant = res.getInt("idCommercant");
			nomBoutique = res.getString("nomBoutique");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Commercant [idCommercant=" + idCommercant + ", nomBoutique="
				+ nomBoutique + ", toString()=" + super.toString() + "]";
	}
	
	

}
