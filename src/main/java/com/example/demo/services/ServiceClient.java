package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.repositories.RepositoryClient;
import com.example.demo.repositories.RepositoryCompte;
import com.example.demo.repositories.RepositoryEmployer;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ServiceClient {

    @Autowired
    private RepositoryClient repositoryClient;
    @Autowired
    private RepositoryCompte repositoryCompte;
    @Autowired
    private RepositoryEmployer repositoryEmployer;
    @Autowired
    private ServiceOperation serviceOperation;

    public void Depot(double Montant, Compte compte){
        double AncienSolde=0;
        AncienSolde=compte.getSolde();
        AncienSolde += Montant;
        compte.setSolde(AncienSolde);
        repositoryCompte.save(compte);
        LocalDate dated=LocalDate.now();
        String date =dated.toString();
        String Numero_compte= compte.getNum_compte();
        int code_oper=1001;
        String dep="Depot";
        Client client=new Client();
        client=RechercheClient(compte);
        String Nom_client= client.getNom_Client()+" "+client.getPrenom_Client();
        serviceOperation.EnregistreOperation(code_oper,dep,Montant,date,Numero_compte,Nom_client);
    }

    public void Retrait(double Montant,Compte compte){
        double AncienSolde=0;
        AncienSolde=compte.getSolde();
        AncienSolde -= Montant;
        compte.setSolde(AncienSolde);
        String Numero_compte= compte.getNum_compte();
        int code_oper=1000;
        LocalDate dated=LocalDate.now();
        String date =dated.toString();
        String dep="retrait";
        Client client=new Client();
        client=RechercheClient(compte);
        String Nom_client= client.getNom_Client()+" "+client.getPrenom_Client();
        serviceOperation.EnregistreOperation(code_oper,dep,Montant,date,Numero_compte,Nom_client);
        repositoryCompte.save(compte);
    }

    public Client RechercheClient(Compte compte){
        Client cliente=null;
        List<Client> clientList=repositoryClient.findAll();
        for (Client client : clientList) {
            if(Objects.equals(client.getCode_Client(), compte.getId_client())){

                cliente= client;
            }
        }
        return cliente;
    }


    public Compte RechercheCompte(String Num_compte){
        Optional <Compte> comptedata=repositoryCompte.findById(Num_compte);
        return comptedata.get();
    }

//    public void CreatEmployer(  int Code_employer, String Nom_employer, String Prenom_employer, String Password_employer, Role Role_employer){
//       Employer employer = null;
//       employer.setCode_employer(Code_employer);
//       employer.setNom_employer(Nom_employer);
//       employer.setPrenom_employer(Prenom_employer);
//       employer.setPassword_employer(Password_employer);
//       employer.setRole_employer(Role_employer);
//       repositoryEmployer.save(employer);
//    }

    public void AjoutClient( int code_Client,String NomClient, String Prenom_Client,String Num_tel){
        Client client=new Client();
        client.setCode_Client(code_Client);
        client.setNom_Client(NomClient);
        client.setPrenom_Client(Prenom_Client);
        client.setNum_tel(Num_tel);
        repositoryClient.save(client);
    }

    public static int regener(Random random){
        return random.nextInt(100)+1;
    }

    public int genere(){
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        int ram = random.nextInt(100);
        if (list.contains(ram)){
            ram=regener(random);
        }
        list.add(ram);
        return ram;
    }

}
