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
 * Servlet pour gérer la déconnexion de l'utilisateur.
 */
@WebServlet(name = "DeconnexionServlet", urlPatterns = { "/deconnexion" })
public class DeconnexionServlet extends HttpServlet {

    @Inject
    private StoreBusiness business;

    /**
     * Méthode GET pour gérer la demande de déconnexion de l'utilisateur.
     *
     * @param request  La requête HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException Si une erreur servlet se produit.
     * @throws IOException      Si une erreur d'entrée/sortie se produit.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération du panier de l'utilisateur depuis la session
        CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");

        if (cart == null) {
            // Si le panier est vide ou n'existe pas, rediriger vers la page de connexion
            RequestDispatcher dispatcher = request.getRequestDispatcher("connexion");
            dispatcher.forward(request, response);
        } else {
            // Si un utilisateur est connecté, procéder à la déconnexion
            if (cart.getCurrentUser() != null) {
                business.deconnexion(cart);

                // Invalidier la session
                if (request.getSession() != null)
                    request.getSession().invalidate();
            }

            // Rediriger vers la page de connexion après la déconnexion
            response.sendRedirect("connexion");
        }
    }
}
