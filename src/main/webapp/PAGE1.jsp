<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8" />
            <title>Page de connexion</title>
        </head>

        <body>
            <c:if test="${notConnected}">
                <p style='color:red;'>Nom d'utilisateur ou mot de passe incorrect</p>
            </c:if>
            <h1>Page de connexion</h1>
            <form method="post" action="connexion">
                <table>
                    <tr>
                        <td>Login</td>
                        <td><input type="text" name="login" /></td>
                    </tr>
                    <tr>
                        <td>Mot de passe</td>
                        <td><input type="password" name="password" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Valider" /></td>
                    </tr>
                </table>
            </form>
        </body>

        </html>