package repository;

import model.HistoricalPlan;

import java.util.List;

public class HistoricalPlanRepositoryImpl extends BaseRepositoryImpl<HistoricalPlan, Long> implements HistoricalPlanRepository {

    public HistoricalPlanRepositoryImpl() {
        super(HistoricalPlan.class);
    }

    @Override
    public void logicalDelete(Long id) {
        HistoricalPlan historicalPlan = findById(id);
        if (historicalPlan != null) {
            historicalPlan.setActive(false);
            update(historicalPlan);
        } else {
            System.out.println("⚠️ No se encontró el Plan con ID: " + id + " para darlo de baja.");
        }
    }

    @Override
    public List<HistoricalPlan> findByClient(Integer clientId) {
        return entityManager.createQuery(
                        "SELECT hp FROM HistoricalPlan hp WHERE hp.client.documentId = :clientId", HistoricalPlan.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }

    @Override
    public List<HistoricalPlan> findByPlan(Integer planId) {
        return entityManager.createQuery(
                        "SELECT hp FROM HistoricalPlan hp WHERE hp.plan.idPlan = :planId", HistoricalPlan.class)
                .setParameter("planId", planId)
                .getResultList();
    }

    @Override
    public List<HistoricalPlan> findByIsActive(boolean status) {
        return entityManager.createQuery(
                        "SELECT hp FROM HistoricalPlan hp WHERE hp.isActive = :status", HistoricalPlan.class)
                .setParameter("status", status)
                .getResultList();
    }
}
