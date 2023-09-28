package com.example.demo.services;

import com.example.demo.entities.Virement;
import com.example.demo.repositories.RepositoryVirement;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceVirement {

    @Autowired
    private RepositoryVirement repositoryVirement;
    private ServiceClient serviceClient;
    public void Virements(String Numero_virement, String Compte_depart,String Compte_arrive,double montant){
        Virement virement=new Virement();
        virement.setNumero_virement(Numero_virement);
        virement.setCompte_depart(Compte_depart);
        virement.setCompte_arrive(Compte_arrive);
        virement.setMontant(montant);
        repositoryVirement.save(virement);
    }
}
