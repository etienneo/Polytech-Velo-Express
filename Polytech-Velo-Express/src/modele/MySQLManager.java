package modele;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.*;

public class MySQLManager {
	private String dbURL;
	private String table;
	private String user;
	private String password;
	private java.sql.Connection dbConnect = null;
	private java.sql.Statement dbStatement = null;
	
	private static MySQLManager manager = null;
	
	public final static MySQLManager getMySQLManager() {
		if(manager == null)
			manager = new MySQLManager("127.0.0.1:3306", "pve_db", "root", "");
		return manager;
	}

	/**
	 * Constructeur
	 * @param url
	 * @param user
	 * @param password
	 */
	private MySQLManager(String url, String table, String user, String password) {
		this.dbURL = url;
		this.table = table;
		this.user = user;
		this.password = password;
		this.connect();
	}

	/**
	 * Connecter à la base de donnée
	 * @return false en cas d'échec
	 */
	public Boolean connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.dbConnect = DriverManager.getConnection("jdbc:mysql://" + this.dbURL + "/" + this.table, this.user, this.password);
			this.dbStatement = this.dbConnect.createStatement();
			return true;
		} catch (SQLException ex) {
			System.out.println("SQL Exception Connexion");
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException Connexion SQL");
		} catch (InstantiationException ex) {
			System.out.println("InstantiationException Connexion SQL");
		} catch (IllegalAccessException ex) {
			System.out.println("IllegalAccessException Connexion SQL");
		}
		return false;
	}

	/**
	 * Executer une requete SQL
	 * @param sql
	 * @return resultat de la requete
	 */
	public ResultSet exec(String sql) {
		try {
			ResultSet rs = this.dbStatement.executeQuery(sql);
			return rs;
		} catch (SQLException ex) {
			System.out.println("SQL Exception Requête");
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Fermer la connexion au serveur de DB
	 */
	public void close() {
		try {
			this.dbStatement.close();
			this.dbConnect.close();
			// On supprime l'instance
			manager=null;
		} catch (SQLException ex) {
			System.out.println("SQL Exception Close");
		}
	}
	
	/**
	 * Lors de la destruction de l'objet, on ferme la connexion mysql
	 */
	protected void finalize() throws Throwable {
		manager.close();
	}

	
}