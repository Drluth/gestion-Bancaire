package com.example.demo.repositories;

import com.example.demo.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryCompte extends JpaRepository<Compte,String> {
    @Override
    Optional<Compte> findById(String s);
}
