package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Viremrnt")
public class Virement {
    @Id
    @Column(name = "Numero_virement")
    private String Numero_virement;
    @Column(name = "Compte_depart")
    private String Compte_depart;
    @Column(name = "Compte_arrive")
    private String Compte_arrive;

    @Column(name = "Montant")
    private double montant;
}
