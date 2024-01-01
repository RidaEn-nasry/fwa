package fr.fortytwo.cinema.repositories;

import fr.fortytwo.cinema.models.User;
import fr.fortytwo.cinema.repositories.UsersRepository;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.postgresql.jdbc.FieldMetadata.Key;


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
            user.setPassword(rs.getString("user_password"));
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
        String sql = "INSERT INTO users (first_name, last_name, phone_number, user_password) VALUES (?, ?, ?, ?)";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newUser.getFirstName());
            ps.setString(2, newUser.getLastName());
            ps.setString(3, newUser.getPhoneNumber());
            ps.setString(4, newUser.getPassword());
            return ps;
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");

        newUser.setId(id.longValue());

        return newUser;
    }

    @Override
    public User update(User newUser) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "UPDATE users SET first_name = ?, last_name = ?, phone_number = ?, user_password = ? WHERE id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newUser.getFirstName());
            ps.setString(2, newUser.getLastName());
            ps.setString(3, newUser.getPhoneNumber());
            ps.setString(4, newUser.getPassword());
            ps.setLong(5, newUser.getId());
            return ps;
        }, holder);
        return newUser;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public User findByFirstName(String firstName) {
        String sql = "SELECT * FROM users WHERE first_name = ?";
        User user = (User) this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), firstName);
        return user;

    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM users WHERE phone_number = ?";
        User user = (User) this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), phoneNumber);
        return user;
    }

}
