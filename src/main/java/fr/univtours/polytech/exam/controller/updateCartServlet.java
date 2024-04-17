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

@WebServlet(name = "updateCartServlet", urlPatterns = { "/updateCart" })
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
            response.sendRedirect("articles");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");
        if (cart != null) {
            if (request.getParameter("buttonMinus") != null) {
                int idArticle = Integer.parseInt(request.getParameter("buttonMinus"));
                for (var entry : cart.getArticlesKeep().entrySet()) {
                    System.out.println(entry.getKey().getName());
                }
                business.removeOneArticle(cart, idArticle);
            } else {
                int idArticle = Integer.parseInt(request.getParameter("buttonPlus"));
                business.addOneArticle(cart, idArticle);
            }
            request.getSession().setAttribute("CART_USER", cart);
            response.sendRedirect("articles");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE1.jsp");
            dispatcher.forward(request, response);
        }
    }
}
