<%@ include file="page/header.jspf" %>

<% if((String)request.getAttribute("erreur") != null) { %>
<p><%= (String)request.getAttribute("erreur") %></p>
<% } %>

<form method="post" action="#">
	<fieldset>
		<legend>Connexion</legend>
		<label>Identifiant<input type="text" name="user"/></label><br/>
		<label>Mot de passe<input type="password" name="password"/></label><br/>
		<input type="submit" value="Valider"/>
	</fieldset>
</form>

<%@ include file="page/footer.jspf" %>