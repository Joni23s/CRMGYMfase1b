package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricalPlanDTO {

    private Long idHistorical;
    private LocalDate startDate;
    private LocalDate endDate;
    private String isActive;
    private String clientName;
    private String planName;
}
