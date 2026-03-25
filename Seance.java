package Cim;

import java.util.Date;

public class Seance {
 private Films film;
 private Salle salle;
 private Date dateProjection;
 private int placesVendues;

 public Seance(Films film, Salle salle, Date dateProjection) {
     if (salle.getCapacite() <= 0) {
         throw new IllegalArgumentException("Capacité de salle invalide !");
     }
     this.film = film;
     this.salle = salle;
     this.dateProjection = dateProjection;
     this.placesVendues = 0;
 }

 public void vendrePlace(int nombre) throws PlaceIndisponibleException {
     if (placesVendues + nombre > salle.getCapacite()) {
         throw new PlaceIndisponibleException("Pas assez de places disponibles !");
     }
     placesVendues += nombre;
 }

 public int getPlacesVendues() { return placesVendues; }
 public Films getFilm() { return film; }
 public Salle getSalle() { return salle; }
}