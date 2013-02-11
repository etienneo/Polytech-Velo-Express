package siteInterface;

import java.io.IOException;

import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.MySQLManager;
import modele.SessionManager;
import modele.utilisateurs.TypeUtilisateur;

public class PenseBete extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public PenseBete()
	{
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		if(SessionManager.getUtilisateur(request) != null &&
				SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.LIVREUR) {
			MySQLManager mysql = MySQLManager.getMySQLManager();
			ResultSet res = mysql.execRequest("SELECT message FROM penseBete WHERE infoTrafic = 1;");
			
			request.setAttribute("title", "PenseBete");
			request.setAttribute("penseBete", res);
			
			getServletContext().getRequestDispatcher("/penseBete.jsp").forward(request, response);
		}
		else
			response.sendRedirect("index");
	}
}
