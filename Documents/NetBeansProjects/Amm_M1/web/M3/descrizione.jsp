<%-- 
    Document   : descrizione
    Created on : 28-apr-2016, 18.22.56
    Author     : utente
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Homepage</title>
        <jsp:include page= "meta.jsp" />
        <meta name="keywords" content="descrizione">
    </head>
    <body>
        <header>
            <h1>
                Descrizione
            </h1>
            <p>
                Benvenuto su UNICommerce.com!
                <br/><br/>
                Qui potrai, tramite il nostro esteso catologo di merci disponibili, quel che più desiderie con le modalità che più ti aggradano!
                <br/>
                Di seguito troverai qualche informazione utile che ti aiuterà nella navigazione del sito!
            </p>
            
            <c:if test="${user != null}">
                <div class="log_in">Bentornato ${user.getName()}</div>
            </c:if>
            <c:if test="${user == null}">
                <a href="login.html" class="log_in">Vai al login!</a>
            </c:if>
        </header>
        <nav class="summary">
            <h2>
                Sommario
            </h2>
            <ul>
                <li>
                    <a href="#oggetti">Oggetti</a>
                </li>
                <li>
                    <a href="#funzionalità">Funzionalità</a>
                </li>
                <li>
                    <a href="#venditore">Per il venditore</a>
                </li>
                <li>
                    <a href="#cliente">Per il cliente</a>
                </li>
            </ul>
        </nav>
        <article>
            <section>
                <h2 id="oggetti">
                    Oggetti
                </h2>
                <p>
                    Grazie all'ampia varietà di merce trattata, possiamo soddisfare le più svariate necessità!<br/>
                    Dalla tecnologia ai prodotti per la cura della casa, fino ad arrivare alla cancelleria e all'abbigliamento!<br/>
                    Vedi di seguito che ruolo vorresti ricoprire: sei solo di passaggio? Ti interessa acquistare qualcosa? O vuoi mettere qualcosa in vendita?
                </p>
            </section>
            <section>
                <h2 id="funzionalità">
                    Funzionalità
                </h2>
                <section>
                    <h3 id="venditore">
                        Per il venditore
                    </h3>
                    <p>
                        Non hai più bisogno di un oggetto e vuoi guadagnarci?<br/>
                        Allora questa è la sezione giusta!<br/>
                        Nell'apposito form potrai specificare cosa hai intenzione di vendere, le sue caratteristiche che ritieni più importanti, il prezzo e in che quantità desideri vendere! <br/>
                        Se ti interessa mettere in vendita qualcosa che non ti serve più visita pure <a href='venditore.html'>questa pagina</a>!
                    </p>
                </section>
                <section>
                    <h3 id="cliente">
                        Per il cliente
                    </h3>
                    <p>
                        Ti interessa qualcosa? Bene!<br/>
                        Avrai la possibilità di scoprire la grande quantità di beni che questo sito mette a disposizione, e tutto per soddisfare il maggior numero di clienti possibili!<br/>
                        Vedrai inoltre, nel catalogo rapido, una serie di oggetti che potrebbero interessarti!<br/>
                        Se hai intenzione di acquistare, sei invitato a raggiungere <a href='cliente.html'>questa pagina</a>!
                    </p>
                </section>
            </section>
        </article>
    </body>
</html>
