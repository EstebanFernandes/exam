package fr.univtours.polytech.exam.dao;

import java.util.List;

import fr.univtours.polytech.exam.model.ArticleBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 * Implémentation de l'interface ArticleDAO pour l'accès aux données des
 * articles.
 */
@Stateless
public class ArticleDAOImpl implements ArticleDAO {

    @PersistenceContext(unitName = "Exam")
    private EntityManager em;

    /**
     * Récupère la liste des articles depuis la base de données.
     * 
     * @return Liste des articles.
     */
    public List<ArticleBean> getArticlesList() {
        // On crée une requête afin de récupérer la liste des articles présents en BDD
        Query requete = em.createNativeQuery("select * from ARTICLE", ArticleBean.class);
        return requete.getResultList();
    }

    /**
     * Met à jour un article dans la base de données.
     * 
     * @param article L'article à mettre à jour.
     */
    @Override
    public void updateArticle(ArticleBean article) {
        Query query = em.createQuery(
                "UPDATE ARTICLE SET nbRestant = :nb , price = :pr, name = :na " +
                        "WHERE id = :id");
        int updateCount = query.setParameter("nb", article.getNbRestant())
                .setParameter("pr", article.getPrice())
                .setParameter("na", article.getName())
                .setParameter("id", article.getId())
                .executeUpdate();
    }

    /**
     * Récupère un article à partir de son identifiant.
     * 
     * @param id L'identifiant de l'article.
     * @return L'article correspondant à l'identifiant.
     */
    @Override
    public ArticleBean getArticle(int id) {
        return em.find(ArticleBean.class, id);
    }
}
