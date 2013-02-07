package modele;

import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class Utilisateur {
	
	/**
	 * Fonction statique permettant de connecter un utilisateur
	 * @param mysql : instance pour la connexion à la bd
	 * @param login
	 * @param password
	 * @return retourne null si les identifiants sont faux, un objet représentant l'utilisateur sinon
	 */
	public static Utilisateur connect(MySQLManager mysql, String login, String password) {
		
		ResultSet res = mysql.exec("SELECT COUNT(*) AS compte FROM Utilisateur WHERE login='" + login + "' AND motDePasse=sha1('" + password + "') ;");
		
		boolean connected = false;		
		try {
			res.next();
			connected = (res.getInt("compte") == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		if(connected)
			System.out.println("Connecté");
		else
			System.out.println("Pas connecté");
		return null;
	}

}
