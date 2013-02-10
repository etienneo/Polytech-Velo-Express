<%@ include file="page/header.jspf" %>
<%@page import="java.sql.*"%>

<%  ResultSet res1 = (ResultSet)request.getAttribute("livraisonCommande");
	//ResultSet res2 = (ResultSet)request.getAttribute("info");%>
	
<br/>
<br/>

<%
   		int cmdPrec = 0;
   		while(res1.next())
   		{
		 %>
		  
		  <% if(cmdPrec != res1.getInt("idCommande")) { %>
			<h3>Commande n° <%= res1.getInt("idCommande") %></h3>
			<% } %>
   			<% cmdPrec = res1.getInt("idCommande"); %>
	 <% } %>
	



<%@ include file="page/footer.jspf" %>