package fr.univtours.polytech.exam.business;

import java.util.List;
import java.util.Map;

import fr.univtours.polytech.exam.dao.ArticleDAO;
import fr.univtours.polytech.exam.dao.UserDAO;
import fr.univtours.polytech.exam.model.ArticleBean;
import fr.univtours.polytech.exam.model.CartBean;
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
            Integer value = cart.getArticlesKeep().get(article);
            cart.getArticlesKeep().put(article, value + 1);
            article.setNbRestant(article.getNbRestant() - 1);
            articleDAO.updateArticle(article);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeOneArticle(CartBean cart, int idArticle) {
        ArticleBean article = articleDAO.getArticle(idArticle);
        if (!cart.getArticlesKeep().containsKey(article)) {
            return false;
        }
        Integer value = cart.getArticlesKeep().get(article);
        cart.getArticlesKeep().put(article, value + 1);
        article.setNbRestant(article.getNbRestant() - 1);
        articleDAO.updateArticle(article);
        return true;
    }

    @Override
    public List<ArticleBean> getArticleList() {
        return articleDAO.getArticlesList();
    }

    @Override
    public CartBean computeTotalPrice(CartBean cart) {
        // On itère sur la map, la clé correspond à l'id de l'article, la valeur
        // correspond à la quantité
        for (Map.Entry<ArticleBean, Integer> entry : cart.getArticlesKeep().entrySet()) {
            float price = entry.getKey().getPrice().floatValue() * (float) entry.getValue();
            cart.setTotalPrice(cart.getTotalPrice() + price);
        }
        return cart;
    }

}
