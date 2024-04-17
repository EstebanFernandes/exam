<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8" />
            <title>Page de connexion</title>
        </head>

        <body
            style="font-family: Arial, sans-serif; background-color: #f4f4f4; display: flex; justify-content: center; align-items: center; height: 100vh;">

            <div
                style="background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
                <c:if test="${notConnected}">
                    <p style="color: red; text-align: center; margin-bottom: 10px;">Nom d'utilisateur ou mot de passe
                        incorrect</p>
                </c:if>
                <h1 style="text-align: center; color: #333;">Page de connexion</h1>
                <form method="post" action="connexion">
                    <table style="width: 100%; border-collapse: collapse;">
                        <tr>
                            <td style="padding: 10px; text-align: left;">Login</td>
                            <td style="padding: 10px;"><input type="text" name="login" required /></td>
                        </tr>
                        <tr>
                            <td style="padding: 10px; text-align: left;">Mot de passe</td>
                            <td style="padding: 10px;"><input type="password" name="password" required /></td>
                        </tr>
                        <tr>
                            <td style="padding: 10px;"></td>
                            <td style="padding: 10px;"><input type="submit" value="Valider"
                                    style="width: 100%; padding: 10px; background-color: #007bff; color: #fff; border: none; border-radius: 4px; cursor: pointer; transition: background-color 0.3s ease;" />
                            </td>
                        </tr>
                    </table>
                </form>
            </div>

        </body>

        </html>