package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "document_id")
    private int documentId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "client")
    private List<HistoricalPlan> historicalPlans;

    public Client(int documentId, String name, String lastName, String email, String phoneNumber, boolean isActive) {
        this.documentId = documentId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

}
