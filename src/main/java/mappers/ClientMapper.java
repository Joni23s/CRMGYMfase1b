package mappers;

import dto.ClientDTO;
import model.Client;
import model.HistoricalPlan;

import java.util.List;

public class ClientMapper {

    public ClientDTO toDTO(Client client) {
        String planName = (client.getCurrentPlan() != null)
                ? client.getCurrentPlan().getNamePlan()
                : "Sin plan";

        return new ClientDTO(
                client.getDocumentId(),
                client.getName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.isActive() ? "Activo" : "Inactivo",
                planName
        );
    }


    public static Client toEntity(ClientDTO dto, List<HistoricalPlan> historicalPlans) {
        return new Client(
                dto.getDocumentId(),
                dto.getName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                "Activo".equals(dto.getStatus()),
                (historicalPlans != null && !historicalPlans.isEmpty())
                        ? historicalPlans.getLast().getPlan()
                        : null
        );
    }
}