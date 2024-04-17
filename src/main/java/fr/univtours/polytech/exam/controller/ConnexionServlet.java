package fr.univtours.polytech.exam.controller;

import java.io.IOException;

import fr.univtours.polytech.exam.dao.UserDAO;
import fr.univtours.polytech.exam.model.CartBean;
import fr.univtours.polytech.exam.model.UserBean;
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
    private UserBean user = new UserBean();
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
            // Ajouter l'utilisateur à la session
            HttpSession mySession = request.getSession();
            mySession.setAttribute("CART_USER", cart);
            user = userDAO.getUserByLogin(login);
            cart.setCurrentuser(user);
            // Rediriger vers la page "articles"
            response.sendRedirect("articles");
        } else {
            // Utilisateur incorrect, continuer à afficher la page de connexion
            request.setAttribute("notConnected", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE1.jsp");
            dispatcher.forward(request, response);
        }
    }
}
