<%@ include file="page/header.jspf" %>
<%@page import="java.sql.*"%>

<%  ResultSet res = (ResultSet)request.getAttribute("produits"); %>
<% boolean modifCommercant = (request.getAttribute("modifCommercant") != null && 
			(Boolean)request.getAttribute("modifCommercant")); %>

<% if(modifCommercant) { %>
<h2>Mes Produits</h2>
<% } else { %>
<h2>Nos Produits</h2>
<% } %>

<% if(request.getAttribute("messageAjout") != null) { %>
<p><strong><%= request.getAttribute("messageAjout") %></strong></p>
<% } %>

<table>
	<tr>
		<th>Produit</th>
		<th>Prix</th>
		<th>Description</th>
		<th>Boutique</th>
		<% if (SessionManager.getUtilisateur(request) == null ||
				SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.CLIENT) { %>
		<th>Panier</th>
		<% } %>
		<% if (modifCommercant) { %>
		<th>Qté</th>
		<th>Qté dispo</th>
		<th>Qté réservée</th>
		<th>Qté vendue</th>		
		<th>Modification</th>
		<% } %>
	</tr>
	
	<% while(res.next())
	   { %>
		<tr>
			<td><%= res.getString("nom") %> </td> 
			<td><%= res.getFloat("prix") %> </td> 
			<td><%= res.getString("description") %> </td>
			<td><%= res.getString("nomBoutique") %> </td>
			<% if (SessionManager.getUtilisateur(request) == null ||
					SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.CLIENT) { %>
			<td>
				<form method="post" action="ajoutpanier">
					<input type="text" value="1" name="nbArticle" size="3" />
					<input type="hidden" value="<%= res.getString("idProduit") %>" name="idArticle"/>
					<input type="submit" value="Ajout"/>
				</form>
			</td>
			<% } %>
			<% if (modifCommercant) { %>
			<td><%= res.getInt("quantite") %></td>
			<td><%= (res.getInt("quantite") - res.getInt("nbReserve")) %></td>
			<td><%= res.getInt("nbReserve") %></td>	
			<td><%= res.getInt("nbVendu") %></td>		
			<td><a href="ajoutproduit?modification=<%= res.getString("idProduit") %>" >Modifier</a></td>
			<% } %>
			
		</tr>
	<% } %>

</table>


<%@ include file="page/footer.jspf" %>