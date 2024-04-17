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
        Integer id = idArticle;
        // On regarde d'abord si l'article est dans le panier, si non on l'ajoute
        if (cart.getArticlesKeep().get(id) == null) {
            cart.getArticlesKeep().put(id, 1);
        }
        // Ensuite on regarde s'il reste des articles en stock
        ArticleBean currentArticle = articleDAO.getArticle(idArticle);
        if (currentArticle.getNbRestant() >= 1) {
            Integer value = cart.getArticlesKeep().get(id);
            cart.getArticlesKeep().put(id, value + 1);
            currentArticle.setNbRestant(currentArticle.getNbRestant() - 1);
            articleDAO.updateArticle(currentArticle);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeOneArticle(CartBean cart, int idArticle) {
        Integer id = idArticle;
        // On regarde d'abord si l'article est dans le panier, si non on return false,
        // on ne peut pas remove un truc pas présent
        if (cart.getArticlesKeep().get(id) == null) {
            return false;
        }
        //Ensuite on remet une quantité dans la bdd
        ArticleBean currentArticle = articleDAO.getArticle(idArticle);

        Integer value = cart.getArticlesKeep().get(id);
        cart.getArticlesKeep().put(id, value - 1);
        currentArticle.setNbRestant(currentArticle.getNbRestant() + 1);
        articleDAO.updateArticle(currentArticle);
        return true;
    }

    @Override
    public List<ArticleBean> getArticleList() {
        return articleDAO.getArticlesList();
    }

    @Override
    public CartBean computeTotalPrice(CartBean cart) {
        //On itère sur la map, la clé correspond à l'id de l'article, la valeur correspond à la quantité
        for (Map.Entry<Integer, Integer> entry : cart.getArticlesKeep().entrySet()) {
           float price =  articleDAO.getArticle(entry.getKey()).getPrice().floatValue()*(float)entry.getValue();
           cart.setTotalPrice(cart.getTotalPrice()+price);
        }
        return cart;
    }

}
