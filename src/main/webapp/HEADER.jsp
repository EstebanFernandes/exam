<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <h1>Bonjour ${sessionScope.CART_USER.currentUser.name}</h1>
        <form action="deconnexion" method="get" style="display: inline-block; margin-right: 30px;">
            <button
                style="padding: 10px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 6px; cursor: pointer;">Déconnexion</button>
        </form>
    </head>
    <body>
        <div class="container" style="margin-top: 80px;">