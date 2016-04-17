<%-- 
    Document   : login_form
    Created on : 17-apr-2016, 21.28.39
    Author     : utente
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Riccardo Locci">
        <meta name="keywords" content="login">
        <link href="style.css" rel="stylesheet" type="text/css" media="screen">
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
            <form action="descrizione.html" method="post">
                <label for="user">Username</label>
                <input type="text" name="username" id="user" value=""/>
                <br/>
                <label for="psw">Password</label>
                <input type="password" name="password" id="psw" value=""/>
                <br/>
                <button type="submit">Login</button>
            </form>
        </div>
    </body>
</html>

