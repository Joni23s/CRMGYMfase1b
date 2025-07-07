package repository;

import model.Plan;
import java.util.List;
import java.util.Optional;

public interface PlanRepository extends BaseRepository<Plan, Integer> {

    void logicalDelete(Integer id);
    Optional<Plan> findByName(String name);
    List<Plan> findByNameLike(String name);
    List<Plan> findByIsActive(boolean isActive);
}
