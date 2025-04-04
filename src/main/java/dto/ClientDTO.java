package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {

    private int documentId;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String isActive;
    private String namePlan;
}
