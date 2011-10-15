<%-- 
    Document   : index
    Created on : 08/10/2011, 15:43:43
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/IsLoggedController"); 
        dispatcher.include(request,response); 
%> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
