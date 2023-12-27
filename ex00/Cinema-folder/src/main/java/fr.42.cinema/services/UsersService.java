package fr.fortytwo.cinema.services;

import fr.fortytwo.cinema.models.User;

public interface UsersService {

    public void signUp(String firstName, String lastName, String password, String phoneNumber);

    public void signIn(String firstName, String lastName);
}
