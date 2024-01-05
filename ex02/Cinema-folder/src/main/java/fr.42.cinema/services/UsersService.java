package fr.fortytwo.cinema.services;

import fr.fortytwo.cinema.models.FileMapping;
import fr.fortytwo.cinema.models.User;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import fr.fortytwo.cinema.models.AuthLogs;

public interface UsersService {

    /************************
     * Sign in and sign up
     *************************/

    public User signUp(String firstName, String lastName, String password, String phoneNumber, String email);

    public User signIn(String email, String password);

    /************************
     * Other methods
     *************************/

    public FileMapping updateProfilePicture(Part filePart, Long userId, String storagePath)
            throws IOException, ServletException;

    public List<FileMapping> getProfilePictures(Long userId);

    public FileMapping getProfilePictureByName(String originalFileName);

    public List<AuthLogs> getAuthLogs(Long userId);

    public AuthLogs addAuthLog(AuthLogs authLog);

    // add time spent to auth log

    public void updateUsersTimeSpent(Long userId, Integer timeSpent);
}
