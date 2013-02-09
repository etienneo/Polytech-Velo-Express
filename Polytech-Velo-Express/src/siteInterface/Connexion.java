package siteInterface;
/*pouet*/
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import modele.MySQLManager;
import modele.SessionManager;
import modele.utilisateurs.Utilisateur;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		MySQLManager mysql = MySQLManager.getMySQLManager();
		request.setAttribute("title", "Connexion");
		
		boolean essai = request.getParameter("user") != null && request.getParameter("password") != null &&
				request.getParameter("user") != "" && request.getParameter("password") != "";
		
		if(essai) {
			Utilisateur user = Utilisateur.connect(request.getParameter("user"), request.getParameter("password"));
			if(user != null) {
				SessionManager.startSession(request, user);
				getServletContext().getRequestDispatcher("/index").forward(request, response);
			}
			else {
				request.setAttribute("erreur", "Le nom d'utilisateur ou mot de passe n'est pas bon.");
				getServletContext().getRequestDispatcher("/connexion.jsp").forward(request, response);
			}
		}
		else
			getServletContext().getRequestDispatcher("/connexion.jsp").forward(request, response);
		
		mysql.close();
	}

}
