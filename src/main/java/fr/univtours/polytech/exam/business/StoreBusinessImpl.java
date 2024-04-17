package fr.univtours.polytech.exam.business;

import java.util.List;

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
        if (cart.getArticlesKeep().get(id) != null) {
            // Si l'article est présent, on doit regarder si on peut ajouter, donc s'il
            // reste

        } else {
            cart.getArticlesKeep().put(id, 1);
        }
        return false;
    }

    @Override
    public boolean removeOneArticle(CartBean cart, int idArticle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOneArticle'");
    }

    @Override
    public List<ArticleBean> getArticleList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArticleList'");
    }

    @Override
    public CartBean computeTotalPrice(CartBean cart) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'computeTotalPrice'");
    }

}
