
package fr.fortytwo.cinema.repositories;

import java.sql.Timestamp;
import java.util.List;

import fr.fortytwo.cinema.models.AuthLogs;
import fr.fortytwo.cinema.repositories.CrudRepository;

public interface AuthLogsRepository extends CrudRepository<AuthLogs> {

    public List<AuthLogs> findByUserId(Long userId);

    public void updateTimeSpent(Long userId, Timestamp attemptedAt, String IpAddress, Integer timeSpent);

}
