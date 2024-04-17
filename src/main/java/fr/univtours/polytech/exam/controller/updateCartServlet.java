package fr.univtours.polytech.exam.controller;

import java.io.IOException;

import fr.univtours.polytech.exam.business.StoreBusiness;
import fr.univtours.polytech.exam.model.CartBean;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "displayArticlesServlet", urlPatterns = { "/updateCart" })
public class updateCartServlet extends HttpServlet {
    @Inject
    private StoreBusiness business;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");
        if (cart == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE1.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE2.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");
        if (cart == null) {
            if (request.getAttribute("buttonMinus") != null) {
                int idArticle = (int) request.getAttribute("buttonMinus");
                business.removeOneArticle(cart, idArticle);
            } else {
                int idArticle = (int) request.getAttribute("buttonPlus");
                business.addOneArticle(cart, idArticle);
            }
            request.getSession().setAttribute("CART_USER", cart);
            RequestDispatcher dispatcher = request.getRequestDispatcher("articles");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE1.jsp");
            dispatcher.forward(request, response);
        }
    }
}
