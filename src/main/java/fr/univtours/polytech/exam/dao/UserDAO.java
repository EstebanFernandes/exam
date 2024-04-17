package fr.univtours.polytech.exam.dao;

public interface UserDAO {
    // verify if the user is in the database
    public boolean checkUser(String login, String password);

    
}
