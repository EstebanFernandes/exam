package fr.univtours.polytech.exam.controller;

import java.io.IOException;
import java.util.List;

import fr.univtours.polytech.exam.business.StoreBusiness;
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

@WebServlet(name = "displayArticlesServlet", urlPatterns = { "/articles" })
public class DisplayArticlesServlet extends HttpServlet {

    @Inject
    private ArticleDAO articleDAO;
    @Inject 
    private StoreBusiness business;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartBean cart = (CartBean) request.getSession().getAttribute("CART_USER");
        if (cart == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("connexion");
            dispatcher.forward(request, response);
        } else {
            if (cart.getArticlesKeep().size() == 0) {
                List<ArticleBean> temp = articleDAO.getArticlesList();
                for (ArticleBean article : temp) {
                    cart.getArticlesKeep().put(article, 0);
                    System.out.println(article.getName());
                }
            } else {
                business.updateListArticles(cart);
            }
            request.getSession().setAttribute("CART_USER", cart);
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

            for (ArticleBean article : temp) {
                cart.getArticlesKeep().put(article, 0);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("PAGE2.jsp");
            dispatcher.forward(request, response);
        }
    }
}
