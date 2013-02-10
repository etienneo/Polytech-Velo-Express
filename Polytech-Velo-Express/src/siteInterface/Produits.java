package siteInterface;

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


public class Produits extends HttpServlet 
{

	public Produits()
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
		MySQLManager mysql = MySQLManager.getMySQLManager();
		ResultSet res = mysql.execRequest("SELECT * FROM produit;");
		
		request.setAttribute("title", "Produits");
		request.setAttribute("produits", res);
		
		getServletContext().getRequestDispatcher("/produits.jsp").forward(request, response);
	}
}
