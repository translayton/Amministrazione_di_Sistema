<%-- 
    Document   : logged_customer
    Created on : 17-apr-2016, 21.28.05
    Author     : utente
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cliente</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Riccardo Locci">
        <meta name="keywords" content="cliente">
        <link href="M3/style.css" rel="stylesheet" type="text/css" media="screen">
        <script type="text/javascript" src="./js/jquery-2.2.4.min.js"></script>
        <script type="text/javascript" src="./js/filter.js"></script>
    </head>
    <body>
        <nav>
            <ul class="summary">
                <c:if test="${user != null}">
                    <li>
                        <form action="cliente.html" method="post">
                            <input type="submit" name="Logout" class="logout" value="${user.getName()} (Logout)"/>
                        </form>
                    </li>
                </c:if>
                <c:if test="${user == null}">
                    <li><a href="login.html">Login</a></li>
                </c:if>
                <li>
                    <a href="descrizione.html">Homepage</a>
                </li>
            </ul>
        </nav>
        <c:if test="${authError}">
            <div>
                <h1>Forbidden</h1>
                <p class='center'>Autenticazione fallita! <br/>Non sei autorizzato a visualizzare la risorsa!</p>
            </div>
        </c:if>
        <c:if test="${!authError}">
            <c:if test="${thisItem == null}">
                <div>
                    <p class="center">
                        Buongiorno cliente!<br/>
                        Di seguito troverai una tabella di oggetti che potrebbero interessarti.
                    </p>
                    
                    <input type="text" id="filtra" size="15" value="Filtra"/>
                    
                    <p class="hide" id="mex"></p>
                    
                    <table id="itemList">
                        <tr class="columns">
                            <th id="name">Nome</th>
                            <th id="image">Foto</th>
                            <th id="amount">Quantità</th>
                            <th id="price">Prezzo</th>
                            <th id="link">Link al carrello</th>
                        </tr>
                        <c:forEach var="item" items="${itemList}">
                            <c:if test="${itemList.indexOf(item) % 2 == 0}">
                                <c:set var="class" value="pari"/>
                            </c:if>
                            <c:if test="${!(itemList.indexOf(item) % 2 == 0)}">
                                <c:set var="class" value="dispari"/>
                            </c:if>
                            <tr class="${class}">
                                <td>${item.getName()}</td>
                                <td><img alt="${item.getImgAlt()}" src="M3/${item.getImgName()}" width="${item.getImgWidth()}" height="${item.getImgHeight()}"/></td>
                                <td>${item.getAmount()}</td>
                                <td>${item.getPrice()}</td>
                                <td class="cart">
                                    <form action="cliente.html" method="post">
                                        <input type="hidden" name="obj" value="${itemList.indexOf(item)}">
                                        <input type="submit" name="Cart" value="Aggiungi al carrello"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
            <c:if test="${thisItem != null}">
                <p class="center">
                    Ecco il resoconto dell'oggetto che vuoi acquistare
                </p>
                <table>
                    <tr class="dispari">
                        <td>Nome</td>
                        <td>${thisItem.getName()}</td>
                    </tr>
                    <tr class="pari">
                        <td>Immagine</td>
                        <td><img alt="${thisItem.getImgAlt()}" src="M3/${thisItem.getImgName()}" width="${thisItem.getImgWidth()}" height="${thisItem.getImgHeight()}"/></td>
                    </tr>
                    <tr class="dispari">
                        <td>Quantità</td>
                        <td>${thisItem.getAmount()}</td>
                    </tr>
                    <tr class="pari">
                        <td>Prezzo</td>
                        <td>${thisItem.getPrice()}</td>
                    </tr>
                </table>
                <form action="cliente.html" method="post">
                    <input type="submit" name="Back" class="client" value="Indietro"/>
                </form>
                <form action="cliente.html" method="post">
                    <input type="hidden" name="obj" value="${itemList.indexOf(thisItem)}">
                    <input type="submit" name="Buy" class="client" value="Compra oggetto"/>
                </form>
                <c:if test="${lowBudget && !selled}">
                    <p class='info'><strong>Denaro insufficente!</strong></p>
                </c:if>
                <c:if test="${!lowBudget && selled}">
                    <p class='info'>Acquisto avvenuto con successo!</p>       
                </c:if> 
            </c:if>
        </c:if>
    </body>
</html>