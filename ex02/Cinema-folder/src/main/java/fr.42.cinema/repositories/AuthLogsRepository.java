
package fr.fortytwo.cinema.repositories;

import java.util.List;

import fr.fortytwo.cinema.models.AuthLogs;
import fr.fortytwo.cinema.repositories.CrudRepository;

public interface AuthLogsRepository extends CrudRepository<AuthLogs> {

    // get all auth logs of a user
    public List<AuthLogs> findByUserId(Long userId);

    // update time spent of an auth log
    public void updateTimeSpent(Long id, Integer timeSpent);
    
}
