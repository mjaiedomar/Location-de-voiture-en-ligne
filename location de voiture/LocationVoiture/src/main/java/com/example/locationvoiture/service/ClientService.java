package com.example.locationvoiture.service;

import com.example.locationvoiture.entity.Client;
import com.example.locationvoiture.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClientService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private ClientRepository clientRepository;

    public boolean save(Client client) {
        clientRepository.save(client);
        return true;
    }

    public boolean deleteById(Integer id) {
        clientRepository.deleteById(id);
        return true;
    }
}
