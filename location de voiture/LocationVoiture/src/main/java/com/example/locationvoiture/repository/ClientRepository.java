package com.example.locationvoiture.repository;

import com.example.locationvoiture.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
