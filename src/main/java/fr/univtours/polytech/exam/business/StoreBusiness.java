package fr.univtours.polytech.exam.business;

import java.util.List;

import fr.univtours.polytech.exam.model.ArticleBean;

public interface StoreBusiness {
    public boolean addOneArticle(int idArticle);

    public boolean removeOneArticle(int idArticle);

    public List<ArticleBean> getArticleList();

}
