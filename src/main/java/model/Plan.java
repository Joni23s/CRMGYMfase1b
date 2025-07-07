package model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @Column(name = "id_plan", nullable = false)
    private int idPlan;

    @Column(name = "name_plan", nullable = false, unique = true)
    private String namePlan;

    @Column(name = "days_enabled", nullable = false)
    private int daysEnabled;

    @Column(name = "hours_enabled", nullable = false)
    private int hoursEnabled;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status")
    private boolean isActive;

    @OneToMany(mappedBy = "plan", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<HistoricalPlan> historicalPlans;

    // Constructor manual útil para inicializar sin lista de historial
    public Plan(int idPlan, String namePlan, int daysEnabled, int hoursEnabled, BigDecimal value, String notes, boolean isActive) {
        this.idPlan = idPlan;
        this.namePlan = namePlan;
        this.daysEnabled = daysEnabled;
        this.hoursEnabled = hoursEnabled;
        this.value = value;
        this.notes = notes;
        this.isActive = isActive;
    }
}
