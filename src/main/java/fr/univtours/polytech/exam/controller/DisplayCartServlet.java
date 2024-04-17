package fr.univtours.polytech.exam.controller;

import java.io.IOException;

import fr.univtours.polytech.exam.business.StoreBusiness;
import fr.univtours.polytech.exam.dao.ArticleDAO;
import fr.univtours.polytech.exam.model.CartBean;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "displayCartServlet", urlPatterns = { "/cart" })
public class DisplayCartServlet extends HttpServlet {

    @Inject
    private ArticleDAO articleDAO;
    private CartBean cart;
    @Inject
    private StoreBusiness storeBusiness;

    @SuppressWarnings("unused")
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");
        cart = storeBusiness.computeTotalPrice(cart);
        request.setAttribute("RESULTS_LIST", cart.getArticlesKeep());
        RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE3.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("articles");
        dispatcher.forward(request, response);
    }
}
