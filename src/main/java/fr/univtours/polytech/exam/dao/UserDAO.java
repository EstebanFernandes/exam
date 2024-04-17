package fr.univtours.polytech.exam.dao;

import fr.univtours.polytech.exam.model.UserBean;

public interface UserDAO {
    // verify if the user is in the database
    public boolean checkUser(String login, String password);

    public UserBean getUserByLogin(String login);
}
