<%@ include file="page/header.jspf" %>

<%
String login = request.getParameter("loginInscription");
String mdp = request.getParameter("mdpInscription");
String nom = request.getParameter("nomInscription");
String prenom = request.getParameter("prenomInscription");
String adresse = request.getParameter("adresseInscription");
String cp = request.getParameter("cpInscription");
String ville = request.getParameter("villeInscription");
String email = request.getParameter("emailInscription");
String tel = request.getParameter("telInscription");

if (login!=null && mdp!=null && nom!=null && prenom!=null && adresse!=null && cp!=null && ville!=null && email!=null && tel!=null)
{ 
	out.println("Votre compte a bien été créé.");
    out.close();  //cesser de charger la page
}
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

<%@ include file="page/footer.jspf" %>