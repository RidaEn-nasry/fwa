package fr.fortytwo.cinema.repositories;

import fr.fortytwo.cinema.models.FileMapping;
import fr.fortytwo.cinema.models.User;
import fr.fortytwo.cinema.repositories.UsersRepository;

import java.sql.Statement;
import java.io.File;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.postgresql.jdbc.FieldMetadata.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import fr.fortytwo.cinema.models.User;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;

@Repository("usersRepositoryImpl")
public class UsersRspositoryImpl implements UsersRepository {

    DataSource ds;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRspositoryImpl(DataSource ds, JdbcTemplate jdbcTemplate) {
        this.ds = ds;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**** FileMapping ****/
    private class FileMappingRowMapper implements RowMapper<FileMapping> {
        @Override
        public FileMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
            FileMapping fileMapping = new FileMapping();
            fileMapping.setOriginalFileName(rs.getString("original_file_name"));
            fileMapping.setGeneratedFileName(rs.getString("generated_file_name"));
            fileMapping.setMimeType(rs.getString("mime_type"));
            fileMapping.setSize(rs.getLong("size"));
            fileMapping.setPath(rs.getString("path"));
            fileMapping.setUserId(rs.getLong("user_id"));
            return fileMapping;

        }
    }

    public List<FileMapping> findByUserId(Long userId) {
        String sql = "SELECT * FROM file_mapping WHERE user_id = ?";
        List<FileMapping> fileMapping = this.jdbcTemplate.query(sql, new FileMappingRowMapper(), userId);
        System.out.println("We got " + fileMapping.size() + " fileMapping(s) for user " + userId);
        return fileMapping;
    }

    /**** User ****/
    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPassword(rs.getString("user_password"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setEmail(rs.getString("email"));
            System.out.println("user id: " + user.getId());
            user.setFileMapping(findByUserId(user.getId()));
            return user;
        }
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
        String sql = "INSERT INTO users (first_name, last_name, phone_number, user_password, email) VALUES (?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newUser.getFirstName());
            ps.setString(2, newUser.getLastName());
            ps.setString(3, newUser.getPhoneNumber());
            ps.setString(4, newUser.getPassword());
            ps.setString(5, newUser.getEmail());

            return ps;
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");

        newUser.setId(id.longValue());

        return newUser;
    }

    @Override
    public User update(User newUser) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "UPDATE users SET first_name = ?, last_name = ?, phone_number = ?, user_password = ?, email = ? WHERE id = ?";
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newUser.getFirstName());
            ps.setString(2, newUser.getLastName());
            ps.setString(3, newUser.getPhoneNumber());
            ps.setString(4, newUser.getPassword());
            ps.setString(5, newUser.getEmail());
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
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = (User) this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), email);
        return user;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM users WHERE phone_number = ?";
        User user = (User) this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), phoneNumber);
        return user;
    }

}
