package com.example.locationvoiture.controller;

import com.example.locationvoiture.entity.Client;
import com.example.locationvoiture.entity.Reservation;
import com.example.locationvoiture.entity.Voiture;
import com.example.locationvoiture.service.ClientService;
import com.example.locationvoiture.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.locationvoiture.service.VoitureService;


import java.util.List;
import java.util.Objects;

@Controller
public class VoitureController {
    @Autowired
    private VoitureService voitureService;
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;


    @GetMapping("/")
    //Affiche la liste complete ou bien la liste selon la recherche saisie
    public String welcome(Model model, @Param("keyword") String keyword) {
        List<Voiture> listeVoiture = voitureService.listAll(keyword);
        model.addAttribute("listeVoiture", listeVoiture);
        return "ListeComplete";
    }

    @GetMapping("/louer")
    //Affiche la liste des voitures qui ont le statut loué
    public String welcome2(Model model, @Param("keyword") String keyword) {
        List<Voiture> listeVoitureLouer = voitureService.listAllLouer(keyword);
        model.addAttribute("listeVoitureLouer", listeVoitureLouer);
        return "ListeLouer";
    }

    @GetMapping("/edit/{id}")
    //Permet d'envoyer les donnees de la voiture a modifier
    public String updateVoiture(Model model, @PathVariable(name = "id") Integer id) {
        Voiture voitureAModifier = voitureService.findById(id);
        model.addAttribute("voitureAModifier", voitureAModifier);
        return "updateVoiture";
    }


    @GetMapping(value = "/update/{id}")
    //Permet de modifier les données d'une voiture
    public String update(@PathVariable(name = "id") Integer id, @RequestParam String marque, @RequestParam String modele,
                         @RequestParam int annee, @RequestParam String couleur,
                         @RequestParam int kilometrage, @RequestParam double prix,
                         @RequestParam String statut) {
        voitureService.update(id, marque, modele, annee, couleur, kilometrage, prix, statut);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    //Permet de supprimer une voiture
    public String deleteVoiture(@PathVariable(name = "id") Integer id) {
        voitureService.deleteById(id);
        return "redirect:/";
    }


    @GetMapping("/Reservation/{id}")
    //Redirige a la page de reservation
    public String reserver(@PathVariable(name = "id") Integer id) {
        return "reservation";
    }

    @GetMapping("/Sauvegarder/{id}")
    //Permet de sauvegarder une reservation avec les différent relations entre chaque classes
    public String sauvegarder(@PathVariable(name = "id") Integer id, @RequestParam("Nom") String nom,
                          @RequestParam("Prenom") String prenom, @RequestParam("Email") String email,
                          @RequestParam("DateReservation") String dateRes, @RequestParam("DateRetour") String dateRet){
        if(Objects.equals(voitureService.findById(id).getStatut(), "Disponible")) {
            //Nouvelle Reservation
            Reservation reservation = new Reservation();
            reservation.setDateReservation(dateRes);
            reservation.setDateRetour(dateRet);

            reservationService.save(reservation);
            //Nouveau Client
            Client client = new Client();
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setEmail(email);

            clientService.save(client);

            //Lier le client a la reservation
            reservation.setClient(client);
            reservationService.save(reservation);

            //Lier la reservation a la voiture
            Voiture voiture = voitureService.findById(id);
            voiture.setReservation(reservation);
            voitureService.save(voiture);

            //Lier la voiture au client
            client.setVoiture(voiture);

            clientService.save(client);

            voitureService.updateStatut(id,"Loué");
        }else{
            return "/error";
        }

        return "redirect:/";
    }

    @GetMapping("/Retourner/{id}")
    //Permet de retourner un vehicule et de le mettre a nouveau sur le marché
    public String retourner(@PathVariable(name="id") Integer id){
        if(Objects.equals(voitureService.findById(id).getStatut(), "Loué")) {
            voitureService.updateStatut(id, "Disponible");
            // reservationService.deleteById(voitureService.findById(id).getReservation().getId());
            // clientService.deleteById(voitureService.findById(id).getReservation().getClient().getId());

        }else{
            return "/error";
        }
        return "redirect:/";
    }


}


