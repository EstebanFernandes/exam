package fr.univtours.polytech.exam.controller;

import java.io.IOException;

import fr.univtours.polytech.exam.dao.UserDAO;
import fr.univtours.polytech.exam.model.CartBean;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "connexionServlet", urlPatterns = { "/connexion" })
public class ConnexionServlet extends HttpServlet {
    @Inject
    private UserDAO userDAO;
    private CartBean cart = new CartBean();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("notConnected", false);
        RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE1.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (!userDAO.checkUser(login, password)) {
            HttpSession mySession = request.getSession();
            mySession.setAttribute("CART_USER",cart);
            System.out.println("C'est bon, on renvoie vers les articles");
            RequestDispatcher dispatcher = request.getRequestDispatcher("articles");
            dispatcher.forward(request, response);

        } else {
            request.setAttribute("notConnected", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("connexion");
            dispatcher.forward(request, response);
        }
    }

}
