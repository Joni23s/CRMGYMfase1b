package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "plan")
    private List<HistoricalPlan> historicalPlans;
}
