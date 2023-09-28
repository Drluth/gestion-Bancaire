package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Employer")
public class Employer {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code_employer")
    private int Code_employer;
    @Column(name = "Nom_employer")
    private String Nom_employer;
    @Column(name = "Prenom_employer")
    private String Prenom_employer;
    @Column(name = "Password_employer")
    private String Password_employer;
   @Column(name = "Role_employer")
   private String Role_employer;
}
