package modele.utilisateurs;

import java.sql.ResultSet;
import java.sql.SQLException;

import modele.MySQLManager;


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
			return returnUser(mysql, login);
		return null;
	}
	
	/**
	 * Fonction statique retournant un objet d'utilisateur correspondant à son type selon le login
	 * @param mysql : instance pour la connexion à la bd
	 * @param login
	 * @return retourne null si l'utilisateur n'est pas trouvé
	 */
	public static Utilisateur returnUser(MySQLManager mysql, String login) {
		try {
			// Cas d'un gestionnaire
			ResultSet res = mysql.exec("SELECT COUNT(*) AS estGestionnaire FROM Utilisateur NATURAL JOIN gestionnairepve WHERE login='" + login + "' ;");
			res.next();
			if(res.getInt("estGestionnaire") == 1)
				return new GestionnairePVE(login);

			// Cas d'un commercant
			res = mysql.exec("SELECT COUNT(*) AS estCommercant FROM Utilisateur NATURAL JOIN commercant WHERE login='" + login + "' ;");
			res.next();
			if(res.getInt("estCommercant") == 1)
				return new Commercant(login);

			// Cas d'un livreur
			res = mysql.exec("SELECT COUNT(*) AS estLivreur FROM Utilisateur NATURAL JOIN livreur WHERE login='" + login + "' ;");
			res.next();
			if(res.getInt("estLivreur") == 1)
				return new Livreur(login);
			
			// Cas d'un client
			res = mysql.exec("SELECT COUNT(*) AS estClient FROM Utilisateur NATURAL JOIN Client WHERE login='" + login + "' ;");
			res.next();
			if(res.getInt("estClient") == 1)
				return new Client(login);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
