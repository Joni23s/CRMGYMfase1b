package repository;

import model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends BaseRepository<Client, Integer> {

    void logicalDelete(Integer id);
    Optional<Client> findByEmail(String email);
    List<Client> findByName(String name);
    List<Client> findByLastName(String lastName);
    List<Client> findByIsActive(boolean isActive);
}

