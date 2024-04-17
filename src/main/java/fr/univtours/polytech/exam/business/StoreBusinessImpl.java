package fr.univtours.polytech.exam.business;

import java.util.List;
import java.util.Map;

import fr.univtours.polytech.exam.dao.ArticleDAO;
import fr.univtours.polytech.exam.dao.UserDAO;
import fr.univtours.polytech.exam.model.ArticleBean;
import fr.univtours.polytech.exam.model.CartBean;
import fr.univtours.polytech.exam.model.UserBean;
import jakarta.inject.Inject;

public class StoreBusinessImpl implements StoreBusiness {

    @Inject
    private ArticleDAO articleDAO;
    @Inject
    private UserDAO userDAO;

    @Override
    public boolean addOneArticle(CartBean cart, int idArticle) {
        ArticleBean article = articleDAO.getArticle(idArticle);
        // Ensuite on regarde s'il reste des articles en stock
        if (article.getNbRestant() >= 1) {
            Integer value = cart.getNbById(idArticle);
            cart.setNbById(idArticle, value+1);
            Integer ancInteger= article.getNbRestant();
            article.setNbRestant(ancInteger - 1);
            articleDAO.updateArticle(article);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeOneArticle(CartBean cart, int idArticle) {
        ArticleBean article = articleDAO.getArticle(idArticle);
        int qtPrise = cart.getNbById(idArticle);
        if(qtPrise!=0)
        {
            cart.setNbById(idArticle, qtPrise-1);
            article.setNbRestant(article.getNbRestant() + 1);
            articleDAO.updateArticle(article);
            return true;
        }
        return false;
    }

    @Override
    public List<ArticleBean> getArticleList() {
        return articleDAO.getArticlesList();
    }

    @Override
    public CartBean computeTotalPrice(CartBean cart) {
        // On itère sur la map, la clé correspond à l'id de l'article, la valeur
        // correspond à la quantité
        cart.setTotalPrice(0);
        for (Map.Entry<ArticleBean, Integer> entry : cart.getArticlesKeep().entrySet()) {
            float price = entry.getKey().getPrice().floatValue() * (float) entry.getValue();
            cart.setTotalPrice(cart.getTotalPrice() + price);
        }
        return cart;
    }

    public void updateListArticles(CartBean cart){
        for(var entry : cart.getArticlesKeep().entrySet())
        {
            entry.getKey().setNbRestant(articleDAO.getArticle(entry.getKey().getId()).getNbRestant());
        }
    }

    @Override
    public void deconnexion(CartBean cart) {
        for(var entry : cart.getArticlesKeep().entrySet())
        {
            int nbPris = entry.getValue();
            for(int i=0;i<nbPris;i++)
            {
                removeOneArticle(cart, entry.getKey().getId());
            }
        }
        cart= null;

    }

    @Override
    public boolean checkUser(String login, String password) {
        return userDAO.checkUser(login, password);
    }

    @Override
    public UserBean getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

}
