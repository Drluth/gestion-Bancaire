package com.example.demo.controllers;

import com.example.demo.entities.Employer;
import com.example.demo.repositories.RepositoryEmployer;
import com.example.demo.services.ServiceEmployer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ControllerEmployer {

    private RepositoryEmployer repositoryEmployer;
    @Autowired
    private ServiceEmployer serviceEmployer;


    @GetMapping("/sign")
    public String Sign( Model model){
        Employer employer=new Employer();
        model.addAttribute("Employer",employer);
        return "signin";
    }



    @PostMapping("/Connexion")
    public String Connxion(@ModelAttribute("Employer") Employer employer){
        int code=employer.getCode_employer();
        String caiss="Caissière";
        String chef_guichef="Chef de guichet";
        String chef="Chef d'agence";
        Optional<Employer> employer1=repositoryEmployer.findById(code);
        if(employer.getPassword_employer().equals(employer1.get().getPassword_employer())){
            if (employer1.get().getRole_employer().equals(chef)){
                return "index";
            }
            if (employer1.get().getRole_employer().equals(caiss)){
                return "indexCaissiere";
            }

        }
        return "error";
    }


    @GetMapping("/AjouterPersonnel")
    public String Pagecreation(Model model){
        Employer employer= new Employer();
        model.addAttribute("Employer",employer);
        return "Gemploye";
    }

    @PostMapping("/PersonnelAjouter")
    public String Afficherperso(@ModelAttribute("Employer") Employer employer,Model model){
        System.out.println("Bienvenue");
         int Code_employer= employer.getCode_employer();
         String Nom_employer= employer.getNom_employer();
         String Prenom_employer= employer.getPrenom_employer();
         String Password_employer= employer.getPassword_employer();
         String Role_employer= employer.getRole_employer();
        System.out.println(Code_employer +"\n"+ Nom_employer +"\n"+Prenom_employer+"\n"+Password_employer+"\n");

         serviceEmployer.CreatEmployer(Code_employer,Nom_employer,Prenom_employer,Password_employer,Role_employer);
         List<Employer> employerList=repositoryEmployer.findAll();
         model.addAttribute("ListeEmployer",employerList);
        System.out.println("Bienvenue");

         return "Gemploye";
    }

    @GetMapping("/Listpersonnel")
    public String ListPerso(Model model){
        List<Employer> employer=repositoryEmployer.findAll();
        model.addAttribute("ListeEmployer",employer);
        return "Lemploye";
    }



    @GetMapping("/delC/{Code_employer}")
    public String deleClient(@PathVariable(value ="Code_employer") int Code_employer){
        repositoryEmployer.deleteById(Code_employer);
        return "Lemploye";
    }


}
