package mappers;

import dto.HistoricalPlanDTO;
import model.Client;
import model.HistoricalPlan;
import model.Plan;

public class HistoricalPlanMapper {

    public HistoricalPlanDTO toDTO(HistoricalPlan historicalPlan) {
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
        return HistoricalPlan.builder()
                .idHistorical(dto.getIdHistorical())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .isActive("Activo".equalsIgnoreCase(dto.getIsActive()))
                .client(client)
                .plan(plan)
                .build();
    }


}
