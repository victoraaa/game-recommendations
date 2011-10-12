<%-- 
    Document   : InputOfCategories
    Created on : 11/10/2011, 18:27:22
    Author     : Luisa
--%>
<%@page import="java.util.List"%>
<%@page import="entities.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Category> categories = (List<Category>) request.getSession().getAttribute("categorias");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>..::Seleção de categorias::..</title>
    </head>
    <body>
        <h1>Selecione as categorias de jogos que você gosta:</h1>
    </body>
</html>
