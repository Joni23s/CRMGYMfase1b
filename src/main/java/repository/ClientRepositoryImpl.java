package repository;

import model.Client;

import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl extends BaseRepositoryImpl<Client, Integer> implements ClientRepository {

    public ClientRepositoryImpl() {
        super(Client.class);
    }

    @Override
    public void logicalDelete(Integer id) {
        Client client = findById(id);
        if (client != null) {
            client.setActive(false);
            update(client);
        }
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT c FROM Client c WHERE c.email = :email", Client.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<Client> findByName(String name) {
        return entityManager.createQuery(
                        "SELECT c FROM Client c WHERE c.name = :name", Client.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Client> findByLastName(String lastName) {
        return entityManager.createQuery(
                        "SELECT c FROM Client c WHERE c.lastName = :lastName", Client.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<Client> findByIsActive(boolean isActive) {
        return entityManager.createQuery(
                        "SELECT c FROM Client c WHERE c.isActive = :isActive", Client.class)
                .setParameter("isActive", isActive)
                .getResultList();
    }
}
