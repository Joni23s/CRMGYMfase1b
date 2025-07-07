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
                plan.getNotes(),
                (plan.isActive() ? "Activo" : "Inactivo")
        );
    }

    public static Plan toEntity(PlanDTO dto) {
        return new Plan(
                dto.getIdPlan(),
                dto.getNamePlan(),
                dto.getDaysEnabled(),
                dto.getHoursEnabled(),
                dto.getValue(),
                dto.getNotes(),
                "Activo".equalsIgnoreCase(dto.getStatus())
        );
    }
}