<%-- 
    Document   : registration
    Created on : 15/10/2011, 17:15:29
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
        <h1>Registration</h1>
         <form action="register" method="get">
            User:<input type="text" name="username"></input> <br></br>
            Password: <input type="password" name="password"></input> <br></br>
            <input type="submit" value="Register"></input>
        </form>
    </body>
</html>
