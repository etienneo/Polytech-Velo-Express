<%@page import="modele.utilisateurs.Client"%>
<%@page import="modele.utilisateurs.Commercant"%>
<%@page import="modele.utilisateurs.Livreur"%>
<%@ include file="page/header.jspf" %>


<h2>Inscription</h2>

<%
String login = request.getParameter("loginInscription");
String mdp = request.getParameter("mdpInscription");
String nom = request.getParameter("nomInscription");
String prenom = request.getParameter("prenomInscription");
String adresse = request.getParameter("adresseInscription");
String cp = request.getParameter("cpInscription");
String ville = request.getParameter("villeInscription");
String mail = request.getParameter("mailInscription");
String tel = request.getParameter("telInscription");
String nomBoutique = request.getParameter("nomBoutiqueInscription");

if (login!=null && mdp!=null && nom!=null && prenom!=null && adresse!=null && cp!=null && ville!=null && mail!=null && tel!=null)
{
	if(request.getParameter("type") == null || request.getParameter("type") == "client" )
	{
		Utilisateur user = new Client(login, mdp, nom, prenom, adresse, ville, cp, mail, tel);
		user.insert();
	}
	else if (request.getParameter("type") == "livreur")
	{
		Utilisateur user = new Livreur(login, mdp, nom, prenom, adresse, ville, cp, mail, tel, false);
		user.insert();
	}
	else if (request.getParameter("type") == "commercant")
	{
		if(nomBoutique != null)
		{
			Utilisateur user = new Commercant(login, mdp, nom, prenom, adresse, ville, cp, mail, tel, nomBoutique);		
			user.insert();
		}
	}
	
	out.println("<p>Votre compte a bien été créé.</p>");
}
else
{
%>

<% // Début du formulaire %>


<form name="formulaireInscription" method="post" action="inscription" onsubmit="return VerifierChamps()">
	<fieldset>
		<legend>S'inscrire</legend>
		<label>Login : <input type="text" name="loginInscription" id="loginInscription" /></label>
		<br/>
		<br/>
		<label>Mot de passe : <input type="text" name="mdpInscription" id="mdpInscription" /></label>
		<br/>
		<br/>
		<label>Nom : <input type="text" name="nomInscription" id="nomInscription" /></label>
		<br/>
		<br/>
		<label>Prénom : <input type="text" name="prenomInscription" id="prenomInscription" /></label>
		<br/>
		<br/>
		<label>Adresse : <input type="text" name="adresseInscription" id="adresseInscription" /></label>
		<br/>
		<br/>
		<label>Code postal : <input type="text" name="cpInscription" id="cpInscription" /></label>
		<br/>
		<br/>
		<label>Ville : <input type="text" name="villeInscription" id="villeInscription" /></label>
		<br/>
		<br/>
		<label>E-mail : <input type="text" name="mailInscription" id="mailInscription" /></label>
		<br/>
		<br/>
		<label>Téléphone : <input type="text" name="telInscription" id="telInscription" /></label>
		<br/>
		<br/>
		<% if(request.getParameter("type") != null && request.getParameter("type").equals("commercant")) { %>
		<label>Nom boutique : <input type="text" name="nomBoutiqueInscription" id="nomBoutiqueInscription" /></label>
		<br/>
		<br/>
		<% } %>
		<% if(request.getParameter("type") != null) { %>
		<input type="hidden" name="type" value="<%= request.getParameter("type") %>" />
		<% } %>
		<input type="submit" value="Valider"/>
 		<input type="reset" name="reset" value="Annuler" >
	</fieldset>
</form>

<script language="javascript">
function verifierChamps()
 { 
	if (trim(document.formulaireInscription.loginInscription.value)=="")
	{
		alert("Vous n'avez pas saisi votre login.");
	    return false;
	}
	if (trim(document.formulaireInscription.mdpInscription.value)=="")
	{
		alert("Vous n'avez pas saisi votre mot de passe.");
	    return false;
	}
 	if (trim(document.formulaireInscription.nomInscription.value)=="")
   	{  
   		alert("Vous n'avez pas saisi votre nom.");
      	return false;
	}
	if (trim(document.formulaireInscription.prenomInscription.value)=="")
	{  
		alert("Vous n'avez pas saisi votre prénom.");
		return false;
	}
	if (trim(document.formulaireInscription.adresseInscription.value)=="")
	{
		alert("Vous n'avez pas saisi votre adresse.");
	    return false;
	}
	if (trim(document.formulaireInscription.cpInscription.value)=="")
	{
		alert("Vous n'avez pas saisi votre code postal.");
	    return false;
	}
	if (trim(document.formulaireInscription.villeInscription.value)=="")
	{
		alert("Vous n'avez pas saisi votre ville.");
	    return false;
	}
	if (trim(document.formulaireInscription.emailInscription.value)=="")
	{
		alert("Vous n'avez pas saisi votre adresse email");
	    return false;
	}
	if (trim(document.formulaireInscription.telInscription.value)=="")
	{
		alert("Vous n'avez pas saisi votre numéro de téléphone.");
	    return false;
	}
	    return true;
}
</script>

<% } %>

<%@ include file="page/footer.jspf" %>