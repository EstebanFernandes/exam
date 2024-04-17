package fr.univtours.polytech.exam.dao;

import javax.management.Query;

public class UserDAOImpl implements UserDAO{

    public boolean checkUser(String login, String password){
    //    Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password");
        return false;
    }
    
}
