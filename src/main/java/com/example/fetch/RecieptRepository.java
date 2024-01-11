package com.example.fetch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecieptRepository extends JpaRepository<Reciept, Integer> {
    Optional<Reciept> findById(int id); // No need to redefine findById
}
