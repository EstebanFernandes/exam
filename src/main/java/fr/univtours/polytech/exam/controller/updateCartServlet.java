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

/**
 * Servlet pour la mise à jour du panier de l'utilisateur.
 */
@WebServlet(name = "updateCartServlet", urlPatterns = { "/updateCart" })
public class UpdateCartServlet extends HttpServlet {

    @Inject
    private StoreBusiness business;

    /**
     * Méthode GET pour afficher le panier ou rediriger vers la page de connexion si
     * l'utilisateur n'est pas connecté.
     *
     * @param request  requête HTTP.
     * @param response réponse HTTP.
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération du panier de l'utilisateur depuis la session
        CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");

        if (cart == null) {
            // Si le panier est vide ou n'existe pas, rediriger vers la page de connexion
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE1.jsp");
            dispatcher.forward(request, response);
        } else {
            // Rediriger vers la page d'articles
            response.sendRedirect("articles");
        }
    }

    /**
     * Méthode POST pour mettre à jour le panier de l'utilisateur en ajoutant ou en
     * retirant des articles.
     *
     * @param request  requête HTTP.
     * @param response réponse HTTP.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération du panier de l'utilisateur depuis la session
        CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");

        if (cart != null) {
            // Vérification du bouton cliqué
            if (request.getParameter("buttonMinus") != null) {
                int idArticle = Integer.parseInt(request.getParameter("buttonMinus"));
                business.removeOneArticle(cart, idArticle);
            } else {
                int idArticle = Integer.parseInt(request.getParameter("buttonPlus"));
                business.addOneArticle(cart, idArticle);
            }

            // Mise à jour du panier dans la session
            request.getSession().setAttribute("CART_USER", cart);

            // Rediriger vers la page d'articles
            response.sendRedirect("articles");
        } else {
            // Si l'utilisateur n'est pas connecté, rediriger vers la page de connexion
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE1.jsp");
            dispatcher.forward(request, response);
        }
    }
}
