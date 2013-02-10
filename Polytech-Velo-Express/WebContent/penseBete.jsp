<%@ include file="page/header.jspf" %>
<%@page import="java.sql.*"%>

<%  ResultSet res = (ResultSet)request.getAttribute("penseBete"); %>

<br/>
<br/>
	<h2>Infos trafic</h2>
	<br/>
	
	<ul>
	<% while(res.next())
	   { %>
		 <li><%= res.getString("message") %></li>
	
	<% } %>
	</ul>

<%@ include file="page/footer.jspf" %>