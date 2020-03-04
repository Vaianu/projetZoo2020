<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="org.formation.zoo.controleur.Manager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<%! String moi = "vaianu"; 
%>
<p>LES SCRIPTLETS JSP</p>
<%=moi
%>
<p>Du code!!!!</p>
<%
String[] res = Manager.getInstance().afficher();
out.println("taille de " + res.length);
for(String s : res){
	out.println(s+"<br>");
}
%>
</body>
</html>