<%@ include file="page/header.jspf" %>

<%@page import="modele.Produit"%>
<%@page import="modele.utilisateurs.Utilisateur"%>
<%@page import="modele.utilisateurs.Commercant"%>

<% 

// On vérifie si c'est une modification, si oui on charge le produit
int idModif = -1;
String nom = "";
String reference = "";
int quantite = 0;
float prix = 0;
String description = "";

Commercant commercant = (Commercant)SessionManager.getUtilisateur(request);

if(request.getParameter("modification") != null) {
	idModif = Integer.valueOf(request.getParameter("modification"));
	// Vérification que le produit appartient au client
	if(Produit.appartientCommercant(idModif, commercant.getIdCommercant())) {
		Produit p = new Produit(idModif);
		nom = p.getNom();
		reference = p.getReferenceProduit();
		quantite = p.getQuantite();
		prix = p.getPrix();
		description = p.getDescription();
%>
<h2>Modification du produit</h2>
<% } else { idModif = -1; %>
<h2>Ajouter un nouveau produit</h2>
<p>Le produit que vous avez tenté de modifier ne vous appartenait pas</p>
<% } } else {%>
<h2>Ajouter un nouveau produit</h2>
<% } %>

<% if(request.getAttribute("message") != null) { %>
	<p><strong><%= request.getAttribute("message") %></strong></p>
<% } %>

<%if(idModif != -1) { %>
<form name="formProduit" method="post" action="ajoutproduit?modification=<%= idModif %>">
<% } else { %>
<form name="formProduit" method="post" action="ajoutproduit">
<% } %>
	<fieldset>
		<legend>Produit</legend>
		<label>Nom<input type="text" name="nom" id="nom" value="<%= nom %>" /></label><br/><br/>
		<label>Reference<input type="text" name="reference" id="reference" value="<%= reference %>" /></label><br/><br/>
		<label>Quantite<input type="text" name="quantite" id="quantite" value="<%= quantite %>" /></label><br/><br/>
		<label>Prix<input type="text" name="prix" id="prix" value="<%= prix %>" /></label><br/><br/>
		<label for="description">Description</label><textarea name="description" id="description" rows="3" cols="40" ><%= description %></textarea><br/><br/>
		<input type="hidden" name="idModif" value="<%= idModif %>" /><input type="hidden" name="idCommercant" value="<%= commercant.getIdCommercant() %>" />
		<input type="submit" value="Valider"/>
	</fieldset>
</form>

<%@ include file="page/footer.jspf" %>