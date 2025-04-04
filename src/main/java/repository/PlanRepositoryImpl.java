package repository;

import model.Plan;

public class PlanRepositoryImpl extends BaseRepositoryImpl<Plan, Integer> implements PlanRepository{

    public PlanRepositoryImpl() {
        super(Plan.class);
    }
}
