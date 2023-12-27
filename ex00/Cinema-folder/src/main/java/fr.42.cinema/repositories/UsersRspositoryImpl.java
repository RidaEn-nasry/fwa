package fr.fortytwo.cinema.repositories;

import fr.fortytwo.cinema.models.User;
import fr.fortytwo.cinema.repositories.UsersRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;

@Repository("usersRepositoryImpl")
public class UsersRspositoryImpl implements UsersRepository {

    DataSource ds;
    JdbcTemplate jdbcTemplate;

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPassword(rs.getString("password"));
            user.setPhoneNumber(rs.getString("phone_number"));
            return user;
        }
    }

    @Autowired
    public UsersRspositoryImpl(DataSource ds, JdbcTemplate jdbcTemplate) {
        this.ds = ds;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = (User) this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        return user;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        List<User> users = this.jdbcTemplate.query(sql, new UserRowMapper());
        return users;
    }

    @Override
    public User save(User newUser) {
        KeyHolder holder = new GeneratedKeyHolder();
        // saving and returning the new user, with the id set
        String sql = "INSERT INTO users (first_name, last_name, phone_number, password) VALUES (?, ?, ?, ?)";
        this.jdbcTemplate.update(sql, newUser.getFirstName(), newUser.getLastName(), newUser.getPhoneNumber(),
                newUser.getPassword(), holder);
        Long newUserId = holder.getKey().longValue();
        newUser.setId(newUserId);
        return newUser;
    }

    @Override
    public User update(User newUser) {
        // implement this later
        return null;
    }

    @Override
    public void delete(Long id) {
        // implement this later
    }

    @Override
    public User findByFirstName(String firstName) {
        // implement this later
        return null;
    }

}
