package fr.univtours.polytech.exam.dao;

import fr.univtours.polytech.exam.model.UserBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 * Implémentation de l'interface UserDAO pour l'accès aux données des
 * utilisateurs.
 */
@Stateless
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(unitName = "Exam")
    private EntityManager em;

    /**
     * Vérifie si un utilisateur avec le login et le mot de passe donnés existe dans
     * la base de données.
     * 
     * @param login    Le login de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return true si l'utilisateur existe, false sinon.
     */
    public boolean checkUser(String login, String password) {
        Query requete = em.createNativeQuery("select * from user where login = ? and password = ?", UserBean.class);
        requete.setParameter(1, login);
        requete.setParameter(2, password);
        return requete.getResultList().isEmpty(); // Si la liste est vide, l'utilisateur n'est pas dans la base
    }

    /**
     * Récupère un utilisateur à partir de son login.
     * 
     * @param login Le login de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant au login, ou null si aucun utilisateur
     *         trouvé.
     */
    public UserBean getUserByLogin(String login) {
        UserBean user = em.find(UserBean.class, login);
        try {
            return user;
        } catch (Exception e) {
            return null; // Retourne null si aucun utilisateur trouvé
        }
    }
}
