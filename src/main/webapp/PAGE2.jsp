<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Articles </title>
        </head>

        <body>
            <h1>Affichage des notes :</h1>
            <form action="addNote" method="get">
                <button name="button" value="">Ajouter une note</button>
            </form>
            <c:if test="${not empty requestScope.RESULTS_MEAN}">
                La moyenne est de ${requestScope.RESULTS_MEAN}
            </c:if>
            <fieldset>
                <legend>Liste des Articles</legend>
                <table>
                    <tr>
                        <th scope="col">Nom</th>
                        <th scope="col">Prix</th>
                        <th scope="col">Restant</th>
                        <th scope="col"> </th>
                        <th scope="col">Enlever 1 au panier</th>
                        <th scope="col">Actuellement dans votre panier</th>
                        <th scope="col">Ajouter 1 au panier</th>

                    </tr>
                    <c:forEach items="${requestScope.CART_USER.articlesKeep}" var="entry">
                        <tr>
                            <td>${entry.key.name}</td>
                            <td>${entry.key.price}</td>
                            <td>${entry.key.nbRestant}</td>
                            <td> </td>
                            <td>
                                <form action="updateCart" method="post">
                                    <button type="submit" name="button" value="minus">-</button>
                                </form>
                            </td>
                            <td> ${entry.value}</td>
                            <td>
                                <form action="updateCart" method="post">
                                    <button type="submit" name="button" value="plus">+</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </fieldset>
            <form action="displayCart" method="get">
                <button name="button" value="">Afficher le panier</button>
            </form>
        </body>

        </html>