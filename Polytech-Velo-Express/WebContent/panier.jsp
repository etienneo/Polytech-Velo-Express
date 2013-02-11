<%@ include file="page/header.jspf" %>
<%@ page import="java.sql.ResultSet" %>
<h2>Mon Panier</h2>

<% ResultSet res = (ResultSet)request.getAttribute("produitsPanier"); %>

<% float total = 0; %>

<table>
	<tr>
		<th>Produit</th>
		<th>Quantité</th>
		<th>Prix</th>
	</tr>
	
	<% while(res.next())
	   { %>
		<tr>
			<td><%= res.getString("nom") %></td>
			<td><%= res.getInt("paniercontientproduit.quantite") %></td>
			<td><%= res.getFloat("prix") %></td>
			<% total += res.getInt("paniercontientproduit.quantite") * res.getFloat("prix"); %>
		</tr>
	<% } %>

</table>

<p>Coût total : <%= total %> euros<br/>
<strong><a href="validePanier" >Valider le panier</a></strong></p>

<%@ include file="page/footer.jspf" %>