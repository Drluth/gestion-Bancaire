package com.example.demo.repositories;

import com.example.demo.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryOperation extends JpaRepository<Operation, Integer> {
    @Override
    Optional<Operation> findById(Integer integer);
}
