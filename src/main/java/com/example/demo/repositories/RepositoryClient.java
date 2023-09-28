package com.example.demo.repositories;

import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryClient extends JpaRepository<Client, Integer> {
    @Override
    Optional<Client> findById(Integer integer);
}
