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
 * Servlet pour afficher le contenu du panier de l'utilisateur.
 */
@WebServlet(name = "displayCartServlet", urlPatterns = { "/cart" })
public class DisplayCartServlet extends HttpServlet {

    @Inject
    private StoreBusiness storeBusiness;

    /**
     * Méthode GET pour afficher le contenu du panier de l'utilisateur.
     *
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

        if (cart != null) {
            // Calcul du prix total du panier
            cart = storeBusiness.computeTotalPrice(cart);
            request.setAttribute("RESULTS_LIST", cart.getArticlesKeep());

            // Affichage de la page du panier
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE3.jsp");
            dispatcher.forward(request, response);
        } else {
            // Redirection vers la page de connexion si l'utilisateur n'est pas connecté
            response.sendRedirect("connexion");
        }
    }

    /**
     * Méthode POST pour rediriger vers la page des articles.
     *
     * @param request  requête HTTP.
     * @param response réponse HTTP.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Redirection vers la page des articles
        RequestDispatcher dispatcher = request.getRequestDispatcher("articles");
        dispatcher.forward(request, response);
    }
}
