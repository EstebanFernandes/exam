package fr.univtours.polytech.exam.model;

import java.io.Serializable;
import java.util.Map;

public class CartBean implements Serializable {
    // Cette map nous permet d'associer un id d'article à une quantité
    private Map<Integer, Integer> articlesKeep;
    // On garde aussi l'id de l'utilisateur
    private int idUser;
    // Le prix total du panier
    private float TotalPrice;

    public float getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        TotalPrice = totalPrice;
    }

    public Map<Integer, Integer> getArticlesKeep() {
        return articlesKeep;
    }

    public void setArticlesKeep(Map<Integer, Integer> articlesKeep) {
        this.articlesKeep = articlesKeep;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

}
