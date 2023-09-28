package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Compte")
public class Compte {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Num_compte")
    private String Num_compte;
    @Column(name = "Solde")
    private double Solde;
    @Column(name = "type_compte")
    private String type_compte;
    @Column(name = "decouvert")
    private double decouvert;
    @Column(name = "Taux_interet")
    private double Taux_interet;
    @Column(name = "Id_client")
    private int Id_client ;
}
