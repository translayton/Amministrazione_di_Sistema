<%-- 
    Document   : logged_seller
    Created on : 17-apr-2016, 21.26.39
    Author     : Riccardo Locci
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Venditore</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Riccardo Locci">
        <meta name="keywords" content="venditore">
        <link href="style.css" rel="stylesheet" type="text/css" media="screen">
    </head>
    <body>
        <nav>
            <ul class="summary">
                <li><a href="login.html">Login</a></li>
                <li><a href="descrizione.html">Homepage</a></li>
            </ul>
        </nav>
        <div>
            <p class="center">
                Buongiorno!<br/>
                Se hai raggiunto questa pagina, vuol dire che hai degli oggetti da vendere!<br/>
                Sfrutta il seguente form per iniziare a vendere!<br/>
            </p>
            <table>
                <tr class="columns">
                    <th>Campo</th>
                    <th>Descrizione del campo</th>
                </tr>
                <tr class="dispari">
                    <td>Nome</td>
                    <td>Nome dell'oggetto da vendere</td>
                </tr>
                <tr class="pari">
                    <td>Foto</td>
                    <td>URL di una foto riguardante l'oggetto</td>
                </tr>
                <tr class="dispari">
                    <td>Descrizione</td>
                    <td>Descrizione dell'oggetto</td>
                </tr>
                <tr class="pari">
                    <td>Prezzo</td> 
                    <td>Prezzo al quale si vuol vendere l'oggetto</td>
                </tr>
                <tr class="dispari">
                    <td>Quantità</td> 
                    <td>Quantità che si vuole vendere</td>
                </tr>
            </table>
            <br/><br/><br/>
            <form action="venditore.html" method="post">
                <label for="nome">Nome:</label>
                <input type="text" name="nome" id="nome" value=""/>
                <br/>
                <label for="url">Foto:</label>
                <input type="text" name="nome" id="url" value=""/>
                <br/>
                <label for="price">Prezzo:</label>
                <input type="text" name="nome" id="price" value=""/>
                <br/>
                <label for="amount">Quantità:</label>
                <input type="number" name="nome" id="amount" value=""/>
                <br/>
                <label for="desc">Descrizione:</label>
                <textarea rows="5" cols="25" name="nome" id="desc"></textarea>
                <br/>
                <button type="submit">Vendi!</button>
            </form>
        </div>
    </body>
</html>

