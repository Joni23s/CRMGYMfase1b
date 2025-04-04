package repository;

import jakarta.persistence.EntityManager;
import util.JpaUtil;

import java.util.List;

/**
 * Implementación genérica de un repositorio CRUD utilizando JPA.
 * General implementation of a CRUD repository using JPA.
 *
 * @param <T>  Tipo de la entidad (ejemplo: Client, Plan).
 *             Entity type (e.g., Client, Plan).
 * @param <ID> Tipo de la clave primaria (ejemplo: Integer).
 *             Primary key type (e.g., Integer).
 */

public class BaseRepositoryImpl<T, ID> implements BaseRepository<T,ID> {

    private final EntityManager entityManager = JpaUtil.getEntityManager();
    private final Class<T> entityClass;

    /**
     * Constructor que recibe la clase de la entidad para realizar consultas dinámicas.
     * Constructor that receives the entity class to perform dynamic queries.
     *
     * @param entityClass Clase de la entidad (ejemplo: Client.class).
     *                    Entity class (e.g., Client.class).
     */
    public BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery(
                "SELECT e FROM " + entityClass.getSimpleName() +
                        " e", entityClass).getResultList();
    }

    @Override
    public void update(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(ID id) {
        T entity = findById(id);
        if (entity != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(entity));
            entityManager.getTransaction().commit();
        }
    }
}
