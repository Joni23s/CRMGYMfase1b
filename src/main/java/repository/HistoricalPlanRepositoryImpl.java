package repository;

import model.HistoricalPlan;

public class HistoricalPlanRepositoryImpl extends BaseRepositoryImpl<HistoricalPlan, Long> implements HistoricalPlanRepository{

    public HistoricalPlanRepositoryImpl() {
        super(HistoricalPlan.class);
    }
}
