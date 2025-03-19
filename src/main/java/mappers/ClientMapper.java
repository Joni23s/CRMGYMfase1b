package mappers;

import dto.ClientDTO;
import model.Client;
import model.HistoricalPlan;

import java.util.List;

public class ClientMapper {
    public static ClientDTO toDTO(Client client) {
        return new ClientDTO(
                client.getDocumentId(),
                client.getName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.isActive() ? "Activo" : "Inactivo",
                client.getHistoricalPlans().getLast().getPlan().getNamePlan()
        );
    }

    public static Client toEntity(ClientDTO clientDTO, List<HistoricalPlan> historicalPlan) {
        return new Client(
                clientDTO.getDocumentId(),
                clientDTO.getName(),
                clientDTO.getLastName(),
                clientDTO.getEmail(),
                clientDTO.getPhoneNumber(),
                "Activo".equals(clientDTO.getIsActive()),
                historicalPlan

        );
    }
}