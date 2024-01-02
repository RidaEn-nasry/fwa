package fr.fortytwo.cinema.services;

import fr.fortytwo.cinema.models.User;

public interface UsersService {

    public User signUp(String firstName, String lastName, String password, String phoneNumber, String email);

    public User signIn(String email, String password);

}
