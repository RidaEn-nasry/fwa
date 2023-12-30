
package fr.fortytwo.cinema.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.fortytwo.cinema.repositories.UsersRepository;
import fr.fortytwo.cinema.services.UsersService;

@Service("usersServiceImpl")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository UsersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(String firstName, String lastName, String password, String phoneNumber) {
        // implement this later
    }

    @Override
    public void signIn(String firstName, String lastName) {
        // implement this later
    }
}