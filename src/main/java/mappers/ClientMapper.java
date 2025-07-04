package mappers;

import dto.ClientDTO;
import model.Client;
import model.HistoricalPlan;

import java.util.List;

public class ClientMapper {
    public ClientDTO toDTO(Client client) {
        String status = client.isActive() ? "Activo" : "Inactivo";
        String namePlan = "Sin plan activo";

        List<HistoricalPlan> history = client.getHistoricalPlans();
        if (history != null && !history.isEmpty()) {
            // Busca el plan activo
            HistoricalPlan activePlan = history.stream()
                    .filter(HistoricalPlan::isActive)
                    .findFirst()
                    .orElse(null);
            if (activePlan != null) {
                namePlan = activePlan.getPlan().getNamePlan();
            }
        }

        return ClientDTO.builder()
                .documentId(client.getDocumentId())
                .name(client.getName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phoneNumber(client.getPhoneNumber())
                .status(status)
                .namePlan(namePlan)
                .build();
    }


    public Client toEntity(ClientDTO clientDTO, List<HistoricalPlan> historicalPlan) {
        return new Client(
                clientDTO.getDocumentId(),
                clientDTO.getName(),
                clientDTO.getLastName(),
                clientDTO.getEmail(),
                clientDTO.getPhoneNumber(),
                "activo".equalsIgnoreCase(clientDTO.getStatus()),
                historicalPlan);
    }
}