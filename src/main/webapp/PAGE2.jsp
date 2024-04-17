<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Articles</title>
            <jsp:include page="HEADER.jsp"/>
        </head>

        <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">

            <h1 style="text-align: center;">Liste des Articles</h1>

            <fieldset
                style="border: 1px solid #ccc; border-radius: 8px; padding: 20px; background-color: #fff; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
                <legend style="font-weight: bold; color: #333;">Articles
                </legend>
                <table style="width: 100%; border-collapse: collapse;">
                    <thead>
                        <tr style="background-color: #007bff; color: #fff;">
                            <th style="border: 1px solid #ccc; padding: 10px;">Nom</th>
                            <th style="border: 1px solid #ccc; padding: 10px;">Prix</th>
                            <th style="border: 1px solid #ccc; padding: 10px;">Restant</th>
                            <th style="border: 1px solid #ccc; padding: 10px;"></th>
                            <th style="border: 1px solid #ccc; padding: 10px;">Enlever 1 au panier</th>
                            <th style="border: 1px solid #ccc; padding: 10px;">Actuellement dans votre panier</th>
                            <th style="border: 1px solid #ccc; padding: 10px;">Ajouter 1 au panier</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.CART_USER.articlesKeep}" var="entry">
                            <tr>
                                <td style="border: 1px solid #ccc; padding: 10px;">${entry.key.name}</td>
                                <td style="border: 1px solid #ccc; padding: 10px;">${entry.key.price}</td>
                                <td style="border: 1px solid #ccc; padding: 10px;">${entry.key.nbRestant}</td>
                                <td style="border: 1px solid #ccc; padding: 10px;"></td>
                                <td style="border: 1px solid #ccc; padding: 10px;">
                                    <form method="post" action="updateCart">
                                        <button type="submit" name="buttonMinus" value="${entry.key.id}"
                                            style="padding: 5px 10px; background-color: #ff1100; color: #fff; border: none; border-radius: 4px; cursor: pointer;">-</button>
                                    </form>
                                </td>
                                <td style="border: 1px solid #ccc; padding: 10px;">${entry.value}</td>
                                <td style="border: 1px solid #ccc; padding: 10px;">
                                    <form method="post" action="updateCart">
                                        <button type="submit" name="buttonPlus" value="${entry.key.id}"
                                            style="padding: 5px 10px; background-color: #007bff; color: #fff; border: none; border-radius: 4px; cursor: pointer;">+</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </fieldset>

            <div style="margin-top: 20px; text-align: center;">
                <form action="cart" method="get" style="display: inline-block; margin-right: 30px;">
                    <button
                        style="padding: 10px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 6px; cursor: pointer;">Afficher
                        le panier</button>
                </form>
            </div>

        </body>

        </html>