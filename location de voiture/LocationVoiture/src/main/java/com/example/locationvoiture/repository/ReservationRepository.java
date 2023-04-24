package com.example.locationvoiture.repository;

import com.example.locationvoiture.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository <Reservation,Integer> {

}
