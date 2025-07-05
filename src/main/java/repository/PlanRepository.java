package repository;

import model.Plan;

import java.util.List;

public interface PlanRepository extends BaseRepository<Plan, Integer>{

    void logicalDelete(Integer id);
    List<Plan> findByName(String name);
    List<Plan> findByLastName(String lastName);
    List<Plan> findByIsActive(boolean isActive);
}
