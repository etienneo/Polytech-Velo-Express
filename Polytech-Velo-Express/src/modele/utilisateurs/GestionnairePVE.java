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
		
		ResultSet res = mysql.exec("SELECT * FROM Client WHERE login='" + login + "';");
		
		try {
			res.next();
			idGestionnaire = res.getInt("idClient");
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
