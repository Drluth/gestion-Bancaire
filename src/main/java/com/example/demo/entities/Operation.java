package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Operation")
public class Operation {
    @Id
    @Column(name = "Code_operation")
    private int Code_operation;
    @Column(name = "Des_operation")
    private String Des_operation;
    @Column(name = "montant")
    private double montant;
    @Column(name = "date_Naissance")
    private String date_Naissance;
    @Column(name = "Id_compte")
    private String Id_compte;
    @Column(name = "Nom_client")
    private String Nom_client;
}
