package fr.fortytwo.cinema.repositories;

import fr.fortytwo.cinema.models.FileMapping;
import fr.fortytwo.cinema.repositories.CrudRepository;
import java.util.List;

public interface FileMappingRepository extends CrudRepository<FileMapping> {

    public FileMapping findByOriginalFileName(String originalFileName);

    public List<FileMapping> findByUserId(Long userId);

    public void deleteByOriginalFileNameAndGeneratedFileName(String originalFileName, String generatedFileName);

}
