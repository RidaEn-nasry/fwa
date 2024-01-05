package fr.fortytwo.cinema.repositories;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.fortytwo.cinema.models.FileMapping;
import fr.fortytwo.cinema.repositories.FileMappingRepository;
import fr.fortytwo.cinema.repositories.CrudRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

@Repository("fileMappingRepositoryImpl")
public class FileMappingRepositoryImpl implements FileMappingRepository {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

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

    @Override
    public List<FileMapping> findAll() {
        // elements are sorted by created_at DESC , so the first element is the last
        // added
        String sql = "SELECT * FROM file_mapping ORDER BY creadted_at DESC";
        return jdbcTemplate.query(sql, new FileMappingRowMapper());
    }

    @Override
    public FileMapping findById(Long id) {
        // we have no id in the file_mapping table
        // primary key is (original_file_name, generated_file_name)
        return null;
    }

    @Override
    public FileMapping save(FileMapping entity) {
        String sql = "INSERT INTO file_mapping (original_file_name, generated_file_name, mime_type, size, path, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, entity.getOriginalFileName(), entity.getGeneratedFileName(), entity.getMimeType(),
                entity.getSize(), entity.getPath(), entity.getUserId());
        return entity;
    }

    @Override
    public FileMapping update(FileMapping entity) {
        String sql = "UPDATE file_mapping SET original_file_name = ?, generated_file_name = ?, mime_type = ?, size = ?, path = ?, user_id = ? WHERE original_file_name = ? AND generated_file_name = ?";
        jdbcTemplate.update(sql, entity.getOriginalFileName(), entity.getGeneratedFileName(), entity.getMimeType(),
                entity.getSize(), entity.getPath(), entity.getUserId(), entity.getOriginalFileName(),
                entity.getGeneratedFileName());
        return entity;

    }

    @Override
    public void delete(Long id) {
        // we have no id in the file_mapping table
        // primary key is (original_file_name, generated_file_name)

        // String sql = "DELETE FROM file_mapping WHERE original_file_name = ? AND
        // generated_file_name = ?";
        // jdbcTemplate.update(sql, id);

    }

    @Override
    public FileMapping findByOriginalFileName(String originalFileName) {
        String sql = "SELECT * FROM file_mapping WHERE original_file_name = ?";
        FileMapping fileMapping = this.jdbcTemplate.queryForObject(sql, new FileMappingRowMapper(), originalFileName);
        return fileMapping;
    }

    @Override
    public List<FileMapping> findByUserId(Long userId) {
        String sql = "SELECT * FROM file_mapping WHERE user_id = ? ORDER BY created_at DESC";
        List<FileMapping> fileMapping = this.jdbcTemplate.query(sql, new FileMappingRowMapper(), userId);
        return fileMapping;
    }

    @Override
    public void deleteByOriginalFileNameAndGeneratedFileName(String originalFileName, String generatedFileName) {
        String sql = "DELETE FROM file_mapping WHERE original_file_name = ? AND generated_file_name = ?";
        jdbcTemplate.update(sql, originalFileName, generatedFileName);
    }

}