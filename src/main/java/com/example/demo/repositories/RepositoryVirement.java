package com.example.demo.repositories;

import com.example.demo.entities.Virement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryVirement extends JpaRepository<Virement,String> {
    @Override
    Optional<Virement> findById(String String);
}
