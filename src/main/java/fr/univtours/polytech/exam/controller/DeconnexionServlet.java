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
@WebServlet(name = "DeonnexionServlet", urlPatterns = { "/deconnexion" })
public class DeconnexionServlet extends HttpServlet{
    @Inject 
    private StoreBusiness business;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");
        if (cart == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("connexion");
            dispatcher.forward(request, response);
        } else {
            if(cart.getCurrentUser()!=null)
            {
                business.deconnexion(cart);
                if(request.getSession()!=null)
                request.getSession().invalidate();
            }
            response.sendRedirect("connexion");
        }
    }
}
