package fr.univtours.polytech.exam.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CartBean implements Serializable {
    // Cette map nous permet d'associer un id d'article à une quantité
    private Map<ArticleBean, Integer> articlesKeep = new HashMap<ArticleBean, Integer>();
    // On garde aussi l'id de l'utilisateur
    private UserBean currentUser;
    // Le prix total du panier
    private float TotalPrice;

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

    public Integer getNbById(int id) {
        for (var entry : articlesKeep.entrySet()) {
            if (entry.getKey().getId() == id)
                return entry.getValue();
        }
        return null;
    }
    public void setNbById(int id,int nb)
    {
        for (var entry : articlesKeep.entrySet()) {
            if (entry.getKey().getId() == id)
                entry.setValue(nb);
        }
    }

}
