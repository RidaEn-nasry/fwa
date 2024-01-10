
package fr.fortytwo.cinema.repositories;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import fr.fortytwo.cinema.models.AuthLogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

@Repository("AuthLogsRepository")
public class AuthLogsRepositoryImpl implements AuthLogsRepository {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthLogsRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    private class AuthLogsMapper implements RowMapper<AuthLogs> {
        @Override
        public AuthLogs mapRow(ResultSet rs, int rowNum) throws SQLException {
            AuthLogs authLogs = new AuthLogs();
            authLogs.setIpAddress(rs.getString("ip_address"));
            authLogs.setAttemptedAt(rs.getTimestamp("attempted_at"));
            authLogs.setTimeSpent(rs.getInt("time_spent"));
            authLogs.setUserId(rs.getLong("user_id"));
            return authLogs;
        }
    }

    @Override
    public List<AuthLogs> findByUserId(Long userId) {
        String sql = "SELECT * FROM auth_logs WHERE user_id = ?";
        List<AuthLogs> authLogs = jdbcTemplate.query(sql, new AuthLogsMapper(), userId);
        return authLogs;
    }

    @Override
    public List<AuthLogs> findAll() {
        String sql = "SELECT * FROM auth_logs";
        List<AuthLogs> authLogs = jdbcTemplate.query(sql, new AuthLogsMapper());
        return authLogs;
    }

    @Override
    public AuthLogs findById(Long id) {
        // ignore, (not used)
        return null;
    }

    @Override
    public AuthLogs save(AuthLogs entity) {
        String sql = "INSERT INTO auth_logs (ip_address, user_id, attempted_at) VALUES (?, ?, ?)";
        int rows = jdbcTemplate.update(sql, entity.getIpAddress(), entity.getUserId(), entity.getAttemptedAt());
        return entity;
    }

    @Override
    public AuthLogs update(AuthLogs entity) {
        String sql = "UPDATE auth_logs SET ip_ = ?, attempted_at = ?, time_spent = ?, user_id = ? WHERE (ip_ = ? AND attempted_at = ? AND time_spent = ? AND user_id = ?)";
        jdbcTemplate.update(sql, entity.getIpAddress(), entity.getAttemptedAt(), entity.getTimeSpent(),
                entity.getUserId(), entity.getIpAddress(), entity.getAttemptedAt(), entity.getTimeSpent(),
                entity.getUserId());
        return entity;
    }

    @Override
    public void delete(Long id) {
        // ignore (not used)
    }

    @Override
    public void updateTimeSpent(Long userId, Timestamp attemptedAt, String ipAddress, Integer timeSpent) {
        String sql = "UPDATE auth_logs SET time_spent = ? WHERE (user_id = ? AND attempted_at = ? AND ip_address = ?)";
        int rows = jdbcTemplate.update(sql, timeSpent, userId, attemptedAt, ipAddress);
    }

}
