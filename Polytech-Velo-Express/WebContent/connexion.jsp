<%@ include file="page/header.jspf" %>

<h2>Connexion</h2>

<% if((String)request.getAttribute("erreur") != null) { %>
<p><%= (String)request.getAttribute("erreur") %></p>
<% } %>

<form method="post" action="connexion">
	<fieldset>
		<legend>Connexion</legend>
		<label>Identifiant<input type="text" name="user"/></label><br/>
		<label>Mot de passe<input type="password" name="password"/></label><br/>
		<input type="submit" value="Valider"/>
	</fieldset>
</form>

<p>
Pas encore inscrit ? <br/>
<a href="inscription.jsp">S'inscrire ici.</a>
</p>
<%@ include file="page/footer.jspf" %>