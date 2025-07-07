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
        } else {
            System.out.println("⚠️ No se encontró el cliente con ID: " + id + " para darlo de baja.");
        }
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%'))", Client.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst(); // sigue siendo solo uno
    }

    @Override
    public List<Client> findByName(String name) {
        return entityManager.createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))", Client.class)
                .setParameter("name", name)
                .getResultList();
    }


    @Override
    public List<Client> findByLastName(String lastName) {
        return entityManager.createQuery(
                        "SELECT c FROM Client c WHERE LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))", Client.class)
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
