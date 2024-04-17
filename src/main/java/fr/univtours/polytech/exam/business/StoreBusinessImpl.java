package fr.univtours.polytech.exam.business;

import java.util.List;
import java.util.Map;

import fr.univtours.polytech.exam.dao.ArticleDAO;
import fr.univtours.polytech.exam.dao.UserDAO;
import fr.univtours.polytech.exam.model.ArticleBean;
import fr.univtours.polytech.exam.model.CartBean;
import fr.univtours.polytech.exam.model.UserBean;
import jakarta.inject.Inject;

/**
 * Implémentation de l'interface StoreBusiness.
 */
public class StoreBusinessImpl implements StoreBusiness {

    @Inject
    private ArticleDAO articleDAO;
    @Inject
    private UserDAO userDAO;

    /**
     * Ajoute un article au panier.
     *
     * @param cart      Le panier de l'utilisateur.
     * @param idArticle L'ID de l'article à ajouter.
     * @return true si l'article a été ajouté avec succès, false sinon.
     */
    @Override
    public boolean addOneArticle(CartBean cart, int idArticle) {
        ArticleBean article = articleDAO.getArticle(idArticle);
        if (article.getNbRestant() >= 1) {
            Integer value = cart.getNbById(idArticle);
            cart.setNbById(idArticle, value + 1);
            Integer ancInteger = article.getNbRestant();
            article.setNbRestant(ancInteger - 1);
            articleDAO.updateArticle(article);
            return true;
        }
        return false;
    }

    /**
     * Supprime un article du panier.
     *
     * @param cart      Le panier de l'utilisateur.
     * @param idArticle L'ID de l'article à supprimer.
     * @return true si l'article a été supprimé avec succès, false sinon.
     */
    @Override
    public boolean removeOneArticle(CartBean cart, int idArticle) {
        ArticleBean article = articleDAO.getArticle(idArticle);
        int qtPrise = cart.getNbById(idArticle);
        if (qtPrise != 0) {
            cart.setNbById(idArticle, qtPrise - 1);
            article.setNbRestant(article.getNbRestant() + 1);
            articleDAO.updateArticle(article);
            return true;
        }
        return false;
    }

    /**
     * Récupère la liste de tous les articles.
     *
     * @return la liste des articles.
     */
    @Override
    public List<ArticleBean> getArticleList() {
        return articleDAO.getArticlesList();
    }

    /**
     * Calcule le prix total du panier.
     *
     * @param cart Le panier de l'utilisateur.
     * @return le panier mis à jour avec le prix total.
     */
    @Override
    public CartBean computeTotalPrice(CartBean cart) {
        cart.setTotalPrice(0);
        for (Map.Entry<ArticleBean, Integer> entry : cart.getArticlesKeep().entrySet()) {
            float price = entry.getKey().getPrice().floatValue() * (float) entry.getValue();
            cart.setTotalPrice(cart.getTotalPrice() + price);
        }
        return cart;
    }

    /**
     * Met à jour la liste des articles dans le panier.
     *
     * @param cart Le panier de l'utilisateur.
     */
    public void updateListArticles(CartBean cart) {
        for (var entry : cart.getArticlesKeep().entrySet()) {
            entry.getKey().setNbRestant(articleDAO.getArticle(entry.getKey().getId()).getNbRestant());
        }
    }

    /**
     * Déconnecte l'utilisateur en vidant le panier.
     *
     * @param cart Le panier de l'utilisateur.
     */
    @Override
    public void deconnexion(CartBean cart) {
        for (var entry : cart.getArticlesKeep().entrySet()) {
            int nbPris = entry.getValue();
            for (int i = 0; i < nbPris; i++) {
                removeOneArticle(cart, entry.getKey().getId());
            }
        }
        cart = null;
    }

    /**
     * Vérifie les informations de connexion de l'utilisateur.
     *
     * @param login    Le nom d'utilisateur.
     * @param password Le mot de passe.
     * @return true si l'utilisateur est authentifié, false sinon.
     */
    @Override
    public boolean checkUser(String login, String password) {
        return userDAO.checkUser(login, password);
    }

    /**
     * Récupère l'utilisateur par son login.
     *
     * @param login Le login de l'utilisateur.
     * @return L'objet UserBean correspondant au login.
     */
    @Override
    public UserBean getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }
}
