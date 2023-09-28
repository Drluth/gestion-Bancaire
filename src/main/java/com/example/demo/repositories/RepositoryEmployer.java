package com.example.demo.repositories;

import com.example.demo.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryEmployer extends JpaRepository<Employer,Integer> {
    @Override
    Optional<Employer> findById(Integer aLong);
}
