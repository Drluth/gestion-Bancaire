package com.example.demo.services;

import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;
import com.example.demo.repositories.RepositoryClient;
import com.example.demo.repositories.RepositoryCompte;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCompte {
    
    @Autowired
    private RepositoryCompte repositoryCompte;
    public void CreationCompte(String Num_compte,double Solde,String type_compte,double decouvert,double Taux_interet,int Id_client){
        Compte compte=new Compte();
        compte.setNum_compte(Num_compte);
        compte.setSolde(Solde);
        compte.setType_compte(type_compte);
        compte.setDecouvert(decouvert);
        compte.setTaux_interet(Taux_interet);
        compte.setId_client(Id_client);
        System.out.println(compte);
        repositoryCompte.save(compte);
    }
}
