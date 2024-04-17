package fr.univtours.polytech.exam.dao;

import java.util.List;

import fr.univtours.polytech.exam.model.ArticleBean;

public interface ArticleDAO {
    public List<ArticleBean> getArticlesList();

    public void updateArticle(ArticleBean article);

    public ArticleBean getArticle(int id);
}