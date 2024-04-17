package fr.univtours.polytech.exam.business;

import java.util.List;

import fr.univtours.polytech.exam.model.ArticleBean;
import fr.univtours.polytech.exam.model.CartBean;

public interface StoreBusiness {
    public boolean addOneArticle(CartBean cart,int idArticle);

    public boolean removeOneArticle(CartBean cart,int idArticle);

    public List<ArticleBean> getArticleList();
    public CartBean computeTotalPrice (CartBean cart);
    public void updateListArticles(CartBean cart);
    public void deconnexion(CartBean cart);

}
