
package fr.fortytwo.cinema.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.fortytwo.cinema.repositories.UsersRepository;
import fr.fortytwo.cinema.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import fr.fortytwo.cinema.models.User;
import jakarta.servlet.ServletException;
@Service("usersServiceImpl")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository UsersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User signUp(String firstName, String lastName, String password, String phoneNumber, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        return UsersRepository.save(user);
    }

    @Override
    public User signIn(String email, String password) {
        User user = UsersRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return user;
    }

    @Override
    public String updateProfilePicture(String originalFileName, Long userId, String storagePath,
            HttpServletRequest request) throws ServletException, IOException {
        String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
        originalFileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String generatedFileName = originalFileName + userId + System.currentTimeMillis() + ext;
        request.getPart("file").write(storagePath + generatedFileName);
        return UsersRepository.updateProfileImg(originalFileName + ext, generatedFileName, userId);
    }
}