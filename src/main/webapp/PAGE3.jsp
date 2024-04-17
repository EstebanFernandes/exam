<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Panier</title>
        </head>

        <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">

            <h1 style="text-align: center;">Contenu du Panier :</h1>

            <fieldset style="border: 1px solid #ccc; border-radius: 8px; padding: 20px; overflow-x: auto;">
                <legend style="font-weight: bold; color: #333;">Panier</legend>
                <table style="width: 100%; border-collapse: collapse;">
                    <thead>
                        <tr style="background-color: #007bff; color: #fff;">
                            <th style="border: 1px solid #ccc; padding: 10px;">Article</th>
                            <th style="border: 1px solid #ccc; padding: 10px;">Prix</th>
                            <th style="border: 1px solid #ccc; padding: 10px;">Quantité</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.CART_USER.articlesKeep}" var="entry">
                            <tr>
                                <td style="border: 1px solid #ccc; padding: 10px;">${entry.key.name}</td>
                                <td style="border: 1px solid #ccc; padding: 10px;">${entry.key.price}</td>
                                <td style="border: 1px solid #ccc; padding: 10px;">${entry.key.value}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </fieldset>

            <div style="margin-top: 20px; text-align: center;">
                <form action="displayCart" method="get">
                    <button type="submit"
                        style="padding: 10px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 4px; cursor: pointer; margin-right: 10px;">Payer</button>
                </form>
                <form action="articles" method="get">
                    <button type="submit"
                        style="padding: 10px 20px; background-color: #ccc; color: #333; border: none; border-radius: 4px; cursor: pointer;">Revenir</button>
                </form>
            </div>

        </body>

        </html>