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
		
		ResultSet res = mysql.exec("SELECT * FROM Client WHERE login='" + login +"';");
		
		try {
			res.next();
			idLivreur = res.getInt("idClient");
			disponibilite = res.getBoolean("disponibilite");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Livreur [idLivreur=" + idLivreur + ", disponibilite="
				+ disponibilite + ", toString()=" + super.toString() + "]";
	}

}
