package fr.univtours.polytech.exam.dao;

import java.util.List;

import fr.univtours.polytech.exam.model.ArticleBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ArticleDAOImpl implements ArticleDAO {
    @PersistenceContext(unitName = "Exam")
    private EntityManager em;

    public List<ArticleBean> getArticlesList() {
        // On crée une requête afin de récupérer la liste des article présent en BDD
        Query requete = em.createNativeQuery("select * from ARTICLE", ArticleBean.class);
        return requete.getResultList();
    }

    @Override
    public void updateArticle(ArticleBean article) {

        em.getTransaction().begin();
        ArticleBean articleToUpdate = em.find(ArticleBean.class, article.getId());
        articleToUpdate = article;
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public ArticleBean getArticle(int id) {
        return em.find(ArticleBean.class, id);
    }

}
