package fr.fortytwo.cinema.repositories;

import fr.fortytwo.cinema.models.User;
import fr.fortytwo.cinema.repositories.CrudRepository;

public interface UsersRepository extends CrudRepository<User> {

    public User findByFirstName(String firstName);

    public User findByEmail(String email);

    public User findByPhoneNumber(String phoneNumber);

    public String updateProfileImg(String imageUrl, Long userId);

}
