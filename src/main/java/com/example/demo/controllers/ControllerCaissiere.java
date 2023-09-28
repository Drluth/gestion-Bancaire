package com.example.demo.controllers;

import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;
import com.example.demo.entities.Virement;
import com.example.demo.repositories.RepositoryClient;
import com.example.demo.repositories.RepositoryOperation;
import com.example.demo.services.ServiceClient;
import com.example.demo.services.ServiceCompte;
import com.example.demo.services.ServiceVirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
@AllArgsConstructor
public class ControllerCaissiere {

    @Autowired
    private RepositoryClient repositoryClient;
    private ServiceClient serviceClient;

    private ServiceCompte serviceCompte;

    @Autowired
    private RepositoryOperation repositoryOperation;

    @GetMapping("/clientsCaissiere")
    public String Nouveauclient(Model model){
        Client client=new Client();
        model.addAttribute("Client",client);
        return "GcompteCaissiere";
    }



    @GetMapping("/compteCaissiere")
    public String Nouveaucompte(Model model){
        Compte compte=new Compte();
        model.addAttribute("Compte",compte);
        return "creationCompteCaissiere";
    }



    @PostMapping("/AjouterCompteCaissiere")
    public String AjoutCompte(@ModelAttribute("Compte") Compte compte){
        List<Client> clients=repositoryClient.findAll();
        Client client=new Client();
        System.out.println(compte);
        serviceCompte.CreationCompte(compte.getNum_compte(),compte.getSolde(),compte.getType_compte(),compte.getDecouvert(),compte.getTaux_interet(),clients.get(clients.size()-1).getCode_Client());
        return "redirect:/ListeClientCaissiere";
    }

    @PostMapping("/AjouterClientCaissiere")
    public String Ajouclient(@ModelAttribute("Client") Client client){
        // creation du client
        int code_Client =client.getCode_Client();
        String NomClient=client.getNom_Client();
        String Prenom_Client=client.getPrenom_Client();
        String Num_tel=client.getNum_tel();
        serviceClient.AjoutClient(code_Client,NomClient,Prenom_Client,Num_tel);
        return "redirect:/compteCaissiere";
    }



    @GetMapping("/listeTransactionCaissiere")
    public String ListTransaction(Model model){
        List<Operation> operations=repositoryOperation.findAll();
        model.addAttribute("List_operation",operations);
        return "indexCaissiere";
    }


    @GetMapping("/EffectuerDepotCaissiere")
    public String EffectuerDepotCaissiere(Model model){
        Compte compte= new Compte();
        model.addAttribute("Compte",compte);
        return "depotCaissiere";
    }


    @PostMapping("/DepotCaissiere")
    public String Transaction(@ModelAttribute("Compte") Compte  compte, Model model){
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
        return "depotCaissiere";
    }


    @GetMapping("/EffectuerRetraitCaissiere")
    public String EffectuerRetraitCaissiere(Model model){
        Compte compte= new Compte();
        model.addAttribute("compte",compte);
        return  "retraitCaissiere";
    }

    @PostMapping("/retraitCaissiere")
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
        return "retraitCaissiere";
    }

    @GetMapping("/indexCaissiere")
    public String ind(Model model){
        List <Operation> operations=repositoryOperation.findAll();
        model.addAttribute("List_operation",operations);
        return "indexCaissiere";
    }

    @GetMapping("/ListeClientCaissiere")
    public String listclienst(Model model){
        List<Client> client=repositoryClient.findAll();
        model.addAttribute("Listeclient",client);
        return "LclientCaissiere";
    }

}
