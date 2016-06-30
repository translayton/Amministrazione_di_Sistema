<%-- 
    Document   : login_form
    Created on : 17-apr-2016, 21.28.39
    Author     : utente
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <jsp:include page= "meta.jsp" />
        <meta name="keywords" content="login">
    </head>
    <body>
        <nav>
            <ul class="summary">
                <li>
                    <a href="descrizione.html">Descrizione</a>
                </li>
                <li>
                    <a href="venditore.html">Venditore</a>
                </li>
                <li>
                    <a href="cliente.html">Cliente</a>
                </li>
            </ul>
            <br/>
        </nav>
        <div>
            <p class="log_in">
                Per favore, autenticati!
            </p>
            <form action="login.html" method="post">
                <label for="user">Username</label>
                <input type="text" name="username" id="user" value=""/>
                <em>${usernameError}</em>
                <br/>
                <label for="psw">Password</label>
                <input type="password" name="password" id="psw" value=""/>
                <em>${passwordError}</em>
                <br/>
                <input type="submit" class="send" name="Submit" value="Login">
            </form>
        </div>
    </body>
</html>

