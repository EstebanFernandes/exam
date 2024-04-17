<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Panier </title>
        </head>

        <body>
            <h1>Contenu du Panier :</h1>
            <form action="displayCart" method="get">
            </form>
            <fieldset>
                <table border="1" cellpadding="5">
                    <caption>
                        <h3>Panier</h3>
                    </caption>
                    <tr>
                        <th scope="col">Article</th>
                        <th scope="col">Prix</th>
                        <th scope="col">Quantite</th>
                    </tr>
                    <c:forEach items="${sessionScope.CART_USER.articlesKeep}" var="entry">
                        <tr>
                            <td>${entry.key.name}</td>
                            <td>${entry.key.price}</td>
                            <td>${entry.key.value}</td>
                            <td> </td>
                        </tr>
                    </c:forEach>
                </table>
            </fieldset>
            <button name="button" value="">Payer</button>
        </body>

        </html>