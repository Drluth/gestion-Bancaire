package com.example.demo.services;

import com.example.demo.entities.Employer;
import com.example.demo.repositories.RepositoryEmployer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceEmployer {

    @Autowired
    private RepositoryEmployer repositoryEmployer;



    public void CreatEmployer(  int Code_employer, String Nom_employer, String Prenom_employer, String Password_employer,String Role_employer){
        Employer employer = new Employer();
        employer.setCode_employer(Code_employer);
        employer.setNom_employer(Nom_employer);
        employer.setPrenom_employer(Prenom_employer);
        employer.setPassword_employer(Password_employer);
        employer.setRole_employer(Role_employer);
        System.out.println(employer);
         Optional<Employer> employer1=repositoryEmployer.findById(Code_employer);
        if (employer1.isPresent()) {

        }else {
            repositoryEmployer.save(employer);
        }

    }

}
