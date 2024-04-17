package fr.univtours.polytech.exam.controller;

import java.io.IOException;
import java.util.List;

import fr.univtours.polytech.exam.dao.ArticleDAO;
import fr.univtours.polytech.exam.model.ArticleBean;
import fr.univtours.polytech.exam.model.CartBean;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "displayArticlesServlet", urlPatterns = { "/articles" })
public class DisplayArticlesServlet extends HttpServlet {

    @Inject
    private ArticleDAO articleDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");
        if (cart == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("connexion");
            dispatcher.forward(request, response);
        } else {
            List<ArticleBean> temp = articleDAO.getArticlesList();
            for (ArticleBean article : temp) {
                cart.getArticlesKeep().put(article, 0);
                System.out.println(article.getName());
            }
            request.getSession().setAttribute("CART_USER",cart);
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE2.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");
        if (cart == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE1.jsp");
            dispatcher.forward(request, response);
        } else {
            List<ArticleBean> temp = articleDAO.getArticlesList();
<<<<<<< HEAD

=======
>>>>>>> 2b0e2857a9d11f0f53e4ba63aad3d05d53b865e9
            for (ArticleBean article : temp) {
                cart.getArticlesKeep().put(article, 0);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE2.jsp");
            dispatcher.forward(request, response);
        }
    }
}
