<%! String title = "Connexion"; %><%@ include file="page/header.jspf" %>

<form method="post" action="#">
	<fieldset>
		<legend>Connexion</legend>
		<label>Identifiant<input type="text" name="user"/></label><br/>
		<label>Mot de passe<input type="password" name="password"/></label><br/>
		<input type="submit" value="Valider"/>
	</fieldset>
</form>

<%@ include file="page/footer.jspf" %>