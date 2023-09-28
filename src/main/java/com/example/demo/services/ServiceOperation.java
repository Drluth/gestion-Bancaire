package com.example.demo.services;

import com.example.demo.entities.Operation;
import com.example.demo.entities.opera;
import com.example.demo.repositories.RepositoryOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@Service
public class ServiceOperation {

    @Autowired
    private RepositoryOperation repositoryOperation;

    public void EnregistreOperation(int Code_operation, String Des_operation, double montant, String date_Naissance, String Id_compte,String Nom_client){
        Operation operation=new Operation();
        operation.setCode_operation(Code_operation);
        operation.setDes_operation(Des_operation);
        operation.setMontant(montant);
        operation.setDate_Naissance(date_Naissance);
        operation.setId_compte(Id_compte);
        operation.setNom_client(Nom_client);
        repositoryOperation.save(operation);

    }


}
