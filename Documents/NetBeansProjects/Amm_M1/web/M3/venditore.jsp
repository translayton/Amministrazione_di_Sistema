<%-- 
    Document   : logged_seller
    Created on : 17-apr-2016, 21.26.39
    Author     : Riccardo Locci
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${authError}">
            <title>Errore 403</title>           
        </c:if>
        <c:if test="${!authError}">
            <title>Venditore</title>
        </c:if>
        <jsp:include page= "meta.jsp" />
        <meta name="keywords" content="venditore">
    </head>
    <body>
        <nav>
            <ul class="summary">
                <c:if test="${user != null}">
                    <li class="sum">
                        <form action="venditore.html" method="post">
                            <input type="submit" name="Logout" class="logout" value="${user.getName()} (Logout)"/>
                        </form>
                    </li>
                </c:if>
                <c:if test="${user == null}">
                    <li><a href="login.html">Login</a></li>
                </c:if>
                <li><a href="descrizione.html">Homepage</a></li>
            </ul>
        </nav>
        <c:if test="${authError}">
            <div>
                <h1>Forbidden</h1>
                <p class='center'>Autenticazione fallita! <br/>Non sei autorizzato a visualizzare la risorsa!</p>
            </div>
        </c:if>
        <c:if test="${!authError}">
            <c:if test="${!itemSelled}">
                <c:if test="${!isEditing}">
                    <div>
                        <p class="center">
                            Buongiorno!<br/>
                            Se hai raggiunto questa pagina, vuol dire che hai degli oggetti da vendere!<br/>
                            Sfrutta il seguente form per iniziare a vendere!<br/>
                        </p>
                        <table>
                            <tr class="columns">
                                <th>Nome</th>
                                <th>Modifica</th>
                                <th>Rimuovi</th>
                            </tr>
                            <c:forEach var="item" items="${itemList}">
                                <c:if test="${itemList.indexOf(item) % 2 == 0}">
                                    <c:set var="class" value="dispari"/>
                                </c:if>
                                <c:if test="${!(itemList.indexOf(item) % 2 == 0)}">
                                    <c:set var="class" value="pari"/>
                                </c:if>
                                <tr class="${class}">
                                    <td>${item.getName()}</td>
                                    <td class="cart">
                                        <form action="venditore.html" method="post">
                                            <input type="hidden" name="editItem" value="${item.getId()}">
                                            <input type="submit" class="logout" name="Edit" value="Modifica"/>
                                        </form>
                                    </td>
                                    <td class="cart">
                                        <form action="venditore.html" method="post">
                                            <input type="hidden" name="removeItem" value="${item.getId()}">
                                            <input type="submit" class="logout" name="Remove" value="Rimuovi"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br/><br/><br/>
                        <form action="venditore.html" method="post">
                            <label for="nome">Nome:</label>
                            <input type="text" name="Name" id="nome" value=""/>
                            <em>${nameError}</em>
                            <br/>
                            <label for="url">Foto:</label>
                            <input type="text" name="Image" id="url" value=""/>
                            <em>${imageError}</em>
                            <br/>
                            <label for="price">Prezzo:</label>
                            <input type="text" name="Price" id="price" value=""/>
                            <em>${priceError}</em>
                            <br/>
                            <label for="amount">Quantità:</label>
                            <input type="number" name="Amount" id="amount" value=""/>
                            <em>${amountError}</em>
                            <br/>
                            <label for="desc">Descrizione:</label>
                            <textarea rows="5" cols="25" name="Desc" id="desc"></textarea>
                            <em id="derror">${descError}</em>
                            <br/>
                            <input type="submit" name="Sell" class="send" value="Vendi!">
                        </form>
                    </div>
                </c:if>
                <c:if test="${isEditing}">
                    <form action="venditore.html" method="post">
                        <label for="nome">Nome:</label>
                        <input type="text" name="Name" id="nome" value=""/>
                        <em>${nameError}</em>
                        <br/>
                        <label for="url">Foto:</label>
                        <input type="text" name="Image" id="url" value=""/>
                        <em>${imageError}</em>
                        <br/>
                        <label for="price">Prezzo:</label>
                        <input type="text" name="Price" id="price" value=""/>
                        <em>${priceError}</em>
                        <br/>
                        <label for="amount">Quantità:</label>
                        <input type="number" name="Amount" id="amount" value=""/>
                        <em>${amountError}</em>
                        <br/>
                        <label for="desc">Descrizione:</label>
                        <textarea rows="5" cols="25" name="Desc" id="desc"></textarea>
                        <em id="derror">${descError}</em>
                        <br/>
                        <input type="hidden" name="editItem" value="${editItem}">
                        <input type="hidden" name="isEditing" value="${isEditing}">
                        <input type="submit" name="Sell" class="send" value="Conferma">
                    </form>
                </c:if>
            </c:if>
            <c:if test="${itemSelled}">
                <p class="center">
                    Ecco il riassunto dei dettagli che riguardano l'oggetto che volevi inserire<br/>
                </p>
                <table>
                    <tr class="columns">
                        <th>Campo</th>
                        <th>Valore del nuovo oggetto</th>
                    </tr>
                    <tr class="dispari">
                        <td>Nome</td>
                        <td>${name}</td>
                    </tr>
                    <tr class="pari">
                        <td>Foto</td>
                        <td>${image}</td>
                    </tr>
                    <tr class="dispari">
                        <td>Descrizione</td>
                        <td>${desc}</td>
                    </tr>
                    <tr class="pari">
                        <td>Prezzo</td> 
                        <td>${price}</td>
                    </tr>
                    <tr class="dispari">
                        <td>Quantità</td> 
                        <td>${amount}</td>
                    </tr>
                </table>
                <form action="venditore.html" method="post">   
                    <input type="hidden" name="name" value="${name}">
                    <input type="hidden" name="image" value="${image}">
                    <input type="hidden" name="desc" value="${desc}">
                    <input type="hidden" name="price" value="${price}">
                    <input type="hidden" name="amount" value="${amount}">
                    <input type="hidden" name="isEditing" value="${isEditing}">
                    <input type="hidden" name="editItem" value="${editItem}">
                    <input type="submit" name="Back" class="send" value="Vendi un nuovo oggetto!"/>
                </form>
            </c:if>
        </c:if>
    </body>
</html>

