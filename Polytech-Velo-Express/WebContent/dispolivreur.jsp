<%@ include file="page/header.jspf" %>
<%@page import="modele.utilisateurs.Livreur"%>

<%
	boolean dispo = false;
	if(SessionManager.getUtilisateur(request) != null &&
		SessionManager.getUtilisateur(request).getType() == TypeUtilisateur.LIVREUR) {
		
		if(request.getParameter("chDispo") != null && Integer.valueOf(request.getParameter("chDispo")) == 1)
			((Livreur)SessionManager.getUtilisateur(request)).changerDisponibilite();
		
		dispo = ((Livreur)SessionManager.getUtilisateur(request)).getDisponibilite();
	}
%>

<h2>Votre disponibilit�</h2>
<% if(dispo) { %>
	<p>Vous �tes actuellement disponible.</p>
<% } else { %>
	<p>Vous �tes actuellement indisponible.</p>
<% } %>

<form name="formDispo" method="post" action="modifdispo">
	<input type="hidden"  name="chDispo" id="chDispo" value="1" />
	<input type="submit" value="Changer ma disponibilit�" />
</form>

<%@ include file="page/footer.jspf" %>