package fr.univtours.polytech.exam.dao;

import fr.univtours.polytech.exam.model.UserBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UserDAOImpl implements UserDAO {
    @PersistenceContext(unitName = "Exam")
    private EntityManager em;

    // verify if the user with login and the password exists is in the database
    public boolean checkUser(String login, String password) {
        Query requete = em.createNativeQuery("select * from user where login = ? and password = ?", UserBean.class);
        requete.setParameter(1, login);
        requete.setParameter(2, password);
        return (requete.getResultList().isEmpty());// if the list is empty, the user is not in the database
    }

    public UserBean getUserByLogin(String login) {
        Query requete = em.createQuery("SELECT * FROM user WHERE login = ?", UserBean.class);
        requete.setParameter(1, login);
        try {
            return (UserBean) requete.getSingleResult();
        } catch (Exception e) {
            return null; // return null if no user found
        }
    }

}
