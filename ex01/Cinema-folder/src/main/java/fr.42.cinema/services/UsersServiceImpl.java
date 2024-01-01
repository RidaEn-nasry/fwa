
package fr.fortytwo.cinema.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.fortytwo.cinema.repositories.UsersRepository;
import fr.fortytwo.cinema.services.UsersService;
import fr.fortytwo.cinema.models.User;

@Service("usersServiceImpl")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository UsersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User signUp(String firstName, String lastName, String password, String phoneNumber) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);
        return UsersRepository.save(user);
    }

    @Override
    public User signIn(String phoneNumber, String password) {
        User user = UsersRepository.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return user;
    }
}