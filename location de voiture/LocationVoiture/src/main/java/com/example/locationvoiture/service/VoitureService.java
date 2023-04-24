package com.example.locationvoiture.service;

import com.example.locationvoiture.entity.Voiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.locationvoiture.repository.VoitureRepository;

import java.util.List;


@Service
@Transactional
public class VoitureService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public VoitureService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private VoitureRepository voitureRepository;

    public List<Voiture> listAll(String keyword) {
        if (keyword != null) {
            return voitureRepository.findAll(keyword);
        } else {
            return voitureRepository.findAll();
        }
    }

    public List<Voiture> listAllLouer(String keyword) {
        return voitureRepository.findAllLouer(keyword);
    }

    public List<Voiture> listAllJson() {
        return voitureRepository.findAll();
    }

    public Voiture findById(int id) {
        return voitureRepository.findById(id).get();
    }

    public boolean save(Voiture voiture) {
        voitureRepository.save(voiture);
        return true;
    }

    public void update(Integer id, String marque, String modele, int annee, String couleur, int kilometrage, double prix, String statut) {
        Voiture voiture = voitureRepository.findById(id).get();
        voiture.setMarque(marque);
        voiture.setModele(modele);
        voiture.setAnnee(annee);
        voiture.setCouleur(couleur);
        voiture.setKilometrage(kilometrage);
        voiture.setPrix(prix);
        voiture.setStatut(statut);
    }

    public void updateStatut(Integer id, String statut) {
        Voiture voiture = voitureRepository.findById(id).get();
        voiture.setStatut(statut);
    }

    public boolean deleteById(int id) {
        voitureRepository.deleteById(id);
        return true;
    }

    public boolean deleteAll() {
        voitureRepository.deleteAll();
        return true;
    }
}
