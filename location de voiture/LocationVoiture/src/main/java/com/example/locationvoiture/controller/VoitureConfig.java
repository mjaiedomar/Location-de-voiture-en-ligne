package com.example.locationvoiture.controller;

import com.example.locationvoiture.entity.Voiture;
import com.example.locationvoiture.repository.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class VoitureConfig {
    @Bean
    CommandLineRunner commandLineRunner(VoitureRepository voitureRepository) {
        return args -> {
            Voiture voiture1 = new Voiture(
                    "Nissan","Altima",2015,"Beige",195000,99.89,"Loué");
            Voiture voiture2 = new Voiture(
                    "Honda","Civic",2020,"Blanche",90000,99.89,"Loué");
            Voiture voiture3 = new Voiture(
                    "Toyota","Matrix",2012,"Bleu",230000,99.89,"Loué");
            Voiture voiture4 = new Voiture(
                    "Dodge","Caravan",2011,"Rouge",192000,99.89,"Loué");
            Voiture voiture5 = new Voiture(
                    "Hyundai","Tucson",2013,"Grise",125000,99.89,"Loué");
            Voiture voiture6 = new Voiture(
                    "Nissan","Altima",2015,"Grise",195000,99.89,"Disponible");
            Voiture voiture7 = new Voiture(
                    "Honda","Civic",2020,"Noir",90000,99.89,"Disponible");
            Voiture voiture8 = new Voiture(
                    "Toyota","Matrix",2012,"Noir",230000,99.89,"Disponible");
            Voiture voiture9 = new Voiture(
                    "Dodge","Caravan",2011,"Noir",192000,99.89,"Disponible");
            Voiture voiture10 = new Voiture(
                    "Hyundai","Tucson",2013,"Rouge",125000,99.89,"Disponible");

            voitureRepository.saveAll(List.of(voiture1,voiture2,voiture3,voiture4,voiture5,voiture6,voiture7,voiture8,voiture9,voiture10));
        };
    }
}