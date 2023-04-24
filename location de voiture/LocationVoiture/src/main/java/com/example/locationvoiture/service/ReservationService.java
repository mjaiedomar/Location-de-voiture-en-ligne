package com.example.locationvoiture.service;

import com.example.locationvoiture.entity.Reservation;
import com.example.locationvoiture.repository.ReservationRepository;
import com.example.locationvoiture.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService {

        @Autowired
        private JdbcTemplate jdbcTemplate;

    public ReservationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation findbyId(Integer id){
        return reservationRepository.findById(id).get();

    }
    public boolean save(Reservation reservation){
        reservationRepository.save(reservation);
        return true;
    }

    public boolean deleteById(Integer id) {
        reservationRepository.deleteById(id);
        return true;
    }
}
