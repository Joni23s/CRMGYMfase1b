package repository;

import model.Plan;

import java.util.List;
import java.util.Optional;

public class PlanRepositoryImpl extends BaseRepositoryImpl<Plan, Integer> implements PlanRepository {

    public PlanRepositoryImpl() {
        super(Plan.class);
    }

    @Override
    public void logicalDelete(Integer id) {
        Plan plan = findById(id);
        if (plan != null) {
            plan.setActive(false);
            update(plan);
        } else {
            System.out.println("⚠️ No se encontró el plan con ID: " + id + " para dar de baja.");
        }
    }

    @Override
    public Optional<Plan> findByName(String name) {
        return entityManager.createQuery(
                        "SELECT p FROM Plan p WHERE LOWER(p.namePlan) = LOWER(:name)", Plan.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<Plan> findByNameLike(String name) {
        return entityManager.createQuery(
                        "SELECT p FROM Plan p WHERE LOWER(p.namePlan) LIKE LOWER(CONCAT('%', :name, '%'))", Plan.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Plan> findByIsActive(boolean isActive) {
        return entityManager.createQuery(
                        "SELECT p FROM Plan p WHERE p.isActive = :isActive", Plan.class)
                .setParameter("isActive", isActive)
                .getResultList();
    }
}
