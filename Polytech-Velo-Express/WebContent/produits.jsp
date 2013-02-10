<%@ include file="page/header.jspf" %>
<%@page import="java.sql.*"%>

<%  ResultSet res = (ResultSet)request.getAttribute("produits"); %>

<% if(request.getAttribute("messageAjout") != null) { %>
<p><strong><%= request.getAttribute("messageAjout") %></strong></p>
<% } %>

<br/>
<br/>
<table>
	
	<% while(res.next())
	   { %>
		<tr>
			<td> <%= res.getString("nom") %> </td> <td> <%= res.getString("referenceProduit") %> </td>
			<td> <%= res.getFloat("prix") %> </td> <td> <%= res.getString("description") %> </td>
			<td> <%= res.getString("nomBoutique") %> </td>
		</tr>
	<% } %>

</table>


<%@ include file="page/footer.jspf" %>