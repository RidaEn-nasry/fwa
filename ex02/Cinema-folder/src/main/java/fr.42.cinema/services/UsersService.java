package fr.fortytwo.cinema.services;

import fr.fortytwo.cinema.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
    import jakarta.servlet.ServletException;
public interface UsersService {

    public User signUp(String firstName, String lastName, String password, String phoneNumber, String email);

    public User signIn(String email, String password);

    public String updateProfilePicture(String originalFileName, Long userId, String storagePath,
            HttpServletRequest request) throws ServletException, IOException;

}
