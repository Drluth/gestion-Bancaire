package com.example.demo.controllers;

import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;
import com.example.demo.entities.Virement;
import com.example.demo.repositories.RepositoryOperation;
import com.example.demo.services.ServiceClient;
import com.example.demo.services.ServiceOperation;
import com.example.demo.services.ServiceVirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@AllArgsConstructor
public class ControllerOperation {
    @Autowired
   private RepositoryOperation repositoryOperation;
    private ServiceClient serviceClient;
    @Autowired
    private ServiceVirement serviceVirement;
    @GetMapping("/listeTransaction")
    public String ListTransaction(Model model){
        List<Operation> operations=repositoryOperation.findAll();
        model.addAttribute("List_operation",operations);
        return "historique";
    }


    @GetMapping("/EffectuerDepot")
    public String EffectuerDepot(Model model){
        Compte compte= new Compte();
        model.addAttribute("compte",compte);
        return  "depot";
    }

    @GetMapping("/test")
    public String teste(){
        return "indexCaissiere";
    }

    @PostMapping("/Depot")
    public String Transaction(@ModelAttribute("compte") Compte  compte, Model model){
        String Num_compte= compte.getNum_compte();
        double Solde=compte.getSolde();
        System.out.println(Solde);
        Compte compte1 = new Compte();
        compte1= serviceClient.RechercheCompte(Num_compte);
        serviceClient.Depot(Solde,compte1);
        Client client= new Client();
        client= serviceClient.RechercheClient(compte1);
        model.addAttribute("Client",client);
        model.addAttribute("Compte",compte1);
        return "depot";
    }


    @GetMapping("/EffectuerRetrait")
    public String EffectuerRetrait(Model model){
        Compte compte= new Compte();
        model.addAttribute("compte",compte);
        return  "retrait";
    }

    @PostMapping("/retrait")
    public String TransactionRetrait(@ModelAttribute("compte") Compte compte,Model model){
        String Num_compte= compte.getNum_compte();
        double Solde=compte.getSolde();
        System.out.println(Solde);
        Compte compte1 = new Compte();
        compte1= serviceClient.RechercheCompte(Num_compte);
        serviceClient.Retrait(Solde,compte1);
        Client client= new Client();
        client= serviceClient.RechercheClient(compte1);
        model.addAttribute("Client",client);
        model.addAttribute("Compte",compte1);
        return "retrait";
    }

    @GetMapping("/virements")
    public String virements(Model model){
        Virement virement=new Virement();
        model.addAttribute("Virement",virement);
        return "virement";
    }

    @PostMapping("/virement")
    public String virement(@ModelAttribute("Virement") Virement virement) {
        Compte compte1 = new Compte();
        Compte compte2 = new Compte();

        int code= serviceClient.genere();
        String cod= String.valueOf(code);
        virement.setNumero_virement(cod);
        compte2 = serviceClient.RechercheCompte(virement.getCompte_depart());
        compte1 = serviceClient.RechercheCompte(virement.getCompte_arrive());
        serviceClient.Depot(virement.getMontant(), compte1);
        serviceClient.Retrait(virement.getMontant(), compte2);
        serviceVirement.Virements(virement.getNumero_virement(), virement.getCompte_depart(), virement.getCompte_arrive(), virement.getMontant());

        return "virement";
    }




}
