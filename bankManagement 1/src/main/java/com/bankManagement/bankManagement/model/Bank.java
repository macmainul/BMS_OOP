package com.bankManagement.bankManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "bank")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountNo; 

    @Column(nullable = false)
    private String status = "Pending";

    @Column(nullable = false)
    private Double amount; // Account balance

    @ManyToOne // Changed from OneToOne to ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Allows multiple rows with the same user_id
    private User user; // Relationship with the User table

    @Column(nullable = false)
    private String depositeAt; // Correct field name

}
