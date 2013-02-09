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
		
		ResultSet res = mysql.exec("SELECT * FROM Client WHERE login='" + login + "';");
		
		try {
			res.next();
			idClient = res.getInt("idClient");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", toString()="
				+ super.toString() + "]";
	}

}
