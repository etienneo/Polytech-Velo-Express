<%@ include file="page/header.jspf" %>
<%@ page import="java.sql.ResultSet" %>
<h2>Mon Panier</h2>

<% ResultSet res = (ResultSet)request.getAttribute("produitsPanier"); %>

<% float total = 0; %>

<% if(request.getAttribute("message") != null) { %>
<p><strong><%= request.getAttribute("message") %></strong></p>
<% } %>

<table>
	<tr>
		<th>Produit</th>
		<th>Quantité</th>
		<th>Prix Unitaire</th>
		<th>Prix</th>
	</tr>
	
	<% while(res.next())
	   { %>
		<tr>
			<td><%= res.getString("nom") %></td>
			<td><%= res.getInt("qte") %></td>
			<td><%= res.getFloat("prix") %></td>
			<td><%= res.getInt("qte") * res.getFloat("prix") %></td>
			<% total += res.getInt("qte") * res.getFloat("prix"); %>
		</tr>
	<% } %>

</table>

<p>Coût total : <%= total %> euros<br/>
<strong><a href="validerpanier" >Valider le panier</a></strong></p>

<%@ include file="page/footer.jspf" %>