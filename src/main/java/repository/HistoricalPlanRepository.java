package repository;
import model.HistoricalPlan;

import java.util.List;

public interface HistoricalPlanRepository extends BaseRepository<HistoricalPlan, Long>{
    void logicalDelete(Long id);
    /**
     * Retorna todos los registros de historial asociados a un cliente.
     * @param clientId DNI del cliente.
     * @return Lista de registros históricos.
     */
    List<HistoricalPlan> findByClient(Integer clientId);

    List<HistoricalPlan> findByPlan(Integer planId);
    List<HistoricalPlan> findByIsActive(boolean status);
}
