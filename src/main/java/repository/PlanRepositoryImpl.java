package repository;

import model.Client;
import model.Plan;

import java.util.List;

public class PlanRepositoryImpl extends BaseRepositoryImpl<Plan, Integer> implements PlanRepository{

    public PlanRepositoryImpl() {
        super(Plan.class);
    }

    @Override
    public void logicalDelete(Integer id) {
        Plan plan = findById(id);
        if (plan != null) {
            plan.setActive(false);
            update(plan);
        }
    }

    @Override
    public List<Plan> findByName(String name) {
        return List.of();
    }

    @Override
    public List<Plan> findByLastName(String lastName) {
        return List.of();
    }

    @Override
    public List<Plan> findByIsActive(boolean isActive) {
        return List.of();
    }
}
