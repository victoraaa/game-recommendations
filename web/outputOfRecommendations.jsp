<%-- 
    Document   : outputOfRecommendations
    Created on : 17/10/2011, 16:30:40
    Author     : Luisa
--%>

<%@page import="entities.Game"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Game> games = (List<Game>) request.getSession().getAttribute("games");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>..::Recomendação de jogos::..</title>
    </head>
    <body>
        <h1>Os jogos sugeridos são:</h1>
        <% for (int i = 0; i < games.size(); i++) {%>
        <%=games.get(i).getName()%><br>
        <%}%> 
    </body>
</html>
