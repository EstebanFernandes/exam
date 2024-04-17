package fr.univtours.polytech.exam.model;

import java.io.Serializable;
import java.util.Map;

import fr.univtours.polytech.exam.dao.ArticleDAO;
import fr.univtours.polytech.exam.dao.ArticleDAOImpl;

public class CartBean implements Serializable {
    // Cette map nous permet d'associer un id d'article à une quantité
    private Map<ArticleBean, Integer> articlesKeep;
    // On garde aussi l'id de l'utilisateur
    private UserBean currentUser;
    // Le prix total du panier
    private float TotalPrice;

    public CartBean(){
        ArticleDAO articles = new ArticleDAOImpl();
        for(ArticleBean article : articles.getArticlesList())
        {
            articlesKeep.put(article,0);
        }
        currentUser=null;
        TotalPrice = 0;
    }
    public float getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        TotalPrice = totalPrice;
    }

    public Map<ArticleBean, Integer> getArticlesKeep() {
        return articlesKeep;
    }

    public void setArticlesKeep(Map<ArticleBean, Integer> articlesKeep) {
        this.articlesKeep = articlesKeep;
    }

    public UserBean getUser() {
        return currentUser;
    }

    public void setIdUser(UserBean user) {
        currentUser = user;
    }

}
