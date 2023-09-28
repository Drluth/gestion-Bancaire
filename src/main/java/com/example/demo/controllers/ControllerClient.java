package com.example.demo.controllers;

import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;
import com.example.demo.entities.Employer;
import com.example.demo.entities.Operation;
import com.example.demo.repositories.RepositoryClient;
import com.example.demo.repositories.RepositoryCompte;
import com.example.demo.repositories.RepositoryOperation;
import com.example.demo.services.ServiceClient;
import com.example.demo.services.ServiceCompte;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ControllerClient {

    @Autowired
    private RepositoryClient repositoryClient;
    @Autowired
    private RepositoryCompte repositoryCompte;
    @Autowired
    private ServiceClient serviceClient;
    @Autowired
    private ServiceCompte serviceCompte;

    private RepositoryOperation repositoryOperation;




    @GetMapping("/cretionclient")
    public String home(){
        return "creationclient";
    }

    

    @PostMapping("/Affiche")
    public String Affiche_Compte(@ModelAttribute("compte") Compte compte,Model model){
        String Num_compte= compte.getNum_compte();
        Compte compte1 = new Compte();
        compte1= serviceClient.RechercheCompte(Num_compte);
       Client client= new Client();
       client= serviceClient.RechercheClient(compte1);
       model.addAttribute("Client",client);
       model.addAttribute("Compte",compte1);
        return  "depot";
    }

    @GetMapping("/index")
    public String Acueil(Model model){
        List <Operation> operations=repositoryOperation.findAll();
        model.addAttribute("List_operation",operations);
        return "index";
    }




    @GetMapping("/clients")
    public String Nouveauclient(Model model){
        Client client=new Client();
        model.addAttribute("Client",client);
        return "Gcompte";
    }



    @GetMapping("/compte")
    public String Nouveaucompte(Model model){
        Compte compte=new Compte();
        model.addAttribute("Compte",compte);
        return "creationCompte";
    }





    @PostMapping("/AjouterCompte")
    public String AjoutCompte(@ModelAttribute("Compte") Compte compte){
        List<Client> clients=repositoryClient.findAll();
        Client client=new Client();
        System.out.println(compte);
        serviceCompte.CreationCompte(compte.getNum_compte(),compte.getSolde(),compte.getType_compte(),compte.getDecouvert(),compte.getTaux_interet(),clients.get(clients.size()-1).getCode_Client());
        return "redirect:/ListeClient";
    }

    @PostMapping("/AjouterClient")
    public String Ajouclient(@ModelAttribute("Client") Client client){
        // creation du client
         int code_Client =client.getCode_Client();
         String NomClient=client.getNom_Client();
         String Prenom_Client=client.getPrenom_Client();
         String Num_tel=client.getNum_tel();
         serviceClient.AjoutClient(code_Client,NomClient,Prenom_Client,Num_tel);
         return "redirect:/compte";
    }




    @PostMapping("//delC/{code_Client}")
    public String deleClient(@PathVariable(value = "code_Client") int code_Client){
        repositoryClient.deleteById(code_Client);
        return "";
    }

    @GetMapping("/ListeClient")
    public String listclient(Model model){
        List<Client> client=repositoryClient.findAll();
        List<Compte> comptes=repositoryCompte.findAll();
        model.addAttribute("Listeclient",client);

//        model.addAttribute("ListeCompte",comptes);
        return "Lclient";
    }




}
