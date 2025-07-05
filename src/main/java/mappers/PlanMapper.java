package mappers;

import dto.PlanDTO;
import model.HistoricalPlan;
import model.Plan;

import java.util.List;

public class PlanMapper {

    public PlanDTO toDTO(Plan plan) {
        return new PlanDTO(
                plan.getIdPlan(),
                plan.getNamePlan(),
                plan.getDaysEnabled(),
                plan.getHoursEnabled(),
                plan.getValue(),
                plan.getNotes()
        );
    }

    public Plan toEntity(PlanDTO planDTO, List<HistoricalPlan> historicalPlan) {
        return new Plan(
                planDTO.getIdPlan(),
                planDTO.getNamePlan(),
                planDTO.getDaysEnabled(),
                planDTO.getHoursEnabled(),
                planDTO.getValue(),
                planDTO.getNotes(),
                historicalPlan
        );
    }
}