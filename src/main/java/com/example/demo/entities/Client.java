package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Client")
public class Client {
    @Id
    @Column(name = "code_Client")
    private int code_Client;
    @Column(name = "nom_Client")
    private String nom_Client;
    @Column(name = "Prenom_Client")
    private String Prenom_Client;
    @Column(name = "Num_tel")
    private String Num_tel;

}
