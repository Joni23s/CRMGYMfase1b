package mappers;

import dto.HistoricalPlanDTO;
import model.Client;
import model.HistoricalPlan;
import model.Plan;

public class HistoricalPlanMapper {

    public static HistoricalPlanDTO toDTO(HistoricalPlan historicalPlan) {
        return new HistoricalPlanDTO(
                historicalPlan.getIdHistorical(),
                historicalPlan.getStartDate(),
                historicalPlan.getEndDate(),
                historicalPlan.isActive() ? "Activo" : "Inactivo",
                historicalPlan.getClient().getName() + " " + historicalPlan.getClient().getLastName(),
                historicalPlan.getPlan().getNamePlan()
        );
    }

    public static HistoricalPlan toEntity(HistoricalPlanDTO dto, Client client, Plan plan) {
        return new HistoricalPlan(
                dto.getIdHistorical(),
                dto.getStartDate(),
                dto.getEndDate(),
                "Activo".equalsIgnoreCase(dto.getIsActive()),
                client,
                plan
        );
    }

}
