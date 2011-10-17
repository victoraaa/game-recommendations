<%-- 
    Document   : login
    Created on : 15/10/2011, 17:10:17
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Already has an account? Please Login:</h1>
        <form action="Login" method="get">
            Usuário:<input type="text" name="username"></input> <br></br>
            Senha: <input type="text" name="password"></input> <br></br>
            <input type="submit" value="Login"></input>
        </form>
        <h2>New? Please Sign Up:</h2>
        <a href="registration.jsp">Sign Up...</a>
    </body>
</html>
