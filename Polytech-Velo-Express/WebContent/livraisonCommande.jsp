<%@ include file="page/header.jspf" %>
<%@page import="java.sql.*"%>

<%  ResultSet res = (ResultSet)request.getAttribute("livraisonCommande"); %>
	

<%
   		int cmdPrec = 0;
   		while(res.next())
   		{
		 %>

		  <% if(cmdPrec != res.getInt("idCommande")) { %>
			<h3>Commande n° <%= res.getInt("idCommande") %></h3>
			<p>Livrer de la boutique <strong><%= res.getString("nomBoutique") %></strong> à <br/>
				<strong><%= res.getString("prenom") %></strong> <strong><%= res.getString("nomClient") %></strong><br/>
				<strong><%= res.getString("adresse") %></strong><br/>
				<strong><%= res.getString("codePostal") %></strong> <strong><%= res.getString("ville") %></strong><br/>
				Téléphone : <strong><%= res.getString("tel") %></strong><br/>
			</p>
			<h4>Produits</h4>
			<% } %>
				<p>
					Produit : <strong><%= res.getString("quantite") %></strong>
					 <strong><%= res.getString("nomProduit") %></strong>, 
					 ref <strong><%= res.getString("referenceProduit") %></strong>
			
			
   			<% cmdPrec = res.getInt("idCommande"); %>
	 <% } %>
	



<%@ include file="page/footer.jspf" %>