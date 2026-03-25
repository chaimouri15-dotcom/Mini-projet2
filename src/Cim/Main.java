package Cim;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        Scanner scanner = new Scanner(System.in);

        try {
            // Authentification
            System.out.print("Login (admin) : ");
            String login = scanner.nextLine();
            System.out.print("Mot de passe(1234) : ");
            String password = scanner.nextLine();

            cinema.authentifier(login, password);
            System.out.println("Authentification réussie !");

            boolean continuer = true;
            while (continuer) {
                System.out.println("\n=== MENU CINÉMA ===");
                System.out.println("1. Charger les films depuis fichier");
                System.out.println("2. Ajouter un film");
                System.out.println("3. Ajouter une salle");
                System.out.println("4. Ajouter une séance");
                System.out.println("5. Acheter une place");
                System.out.println("6. Vendre des places");
                System.out.println("7. Consulter chiffre d'affaires");
                System.out.println("8. Consulter taux de remplissage");
                System.out.println("9. Consulter un film");
                System.out.println("10. Consulter une salle");
                System.out.println("0. Quitter");
                System.out.print("Choix : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // consommer le retour ligne

                switch (choix) {
                    case 1:
                        try {
                            cinema.chargerFilms("cinema.txt");
                            System.out.println("Films chargés !");
                        } catch (FichierCinemaException e) {
                            System.out.println("Erreur : " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Titre du film : ");
                        String titre = scanner.nextLine();
                        System.out.print("Réalisateur : ");
                        String realisateur = scanner.nextLine();
                        cinema.ajouterFilm(new Films(titre, realisateur));
                        System.out.println("Film ajouté !");
                        break;

                    case 3:
                        System.out.print("Numéro de salle : ");
                        int numSalle = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nom de salle : ");
                        String nomSalle = scanner.nextLine();
                        System.out.print("Capacité : ");
                        int capacite = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Type (N=Normale, V=VIP) : ");
                        String type = scanner.nextLine();
                        Salle salle = type.equalsIgnoreCase("V") ?
                                new SalleVIP(numSalle, nomSalle, capacite) :
                                new SalleNormale(numSalle, nomSalle, capacite);
                        cinema.ajouterSalle(salle);
                        System.out.println("Salle ajoutée !");
                        break;

                    case 4:
                        System.out.print("Titre du film pour la séance : ");
                        String titreSeance = scanner.nextLine();
                        try {
                            Films film = cinema.consulterFilm(titreSeance);
                            System.out.print("Numéro de salle : ");
                            int num = scanner.nextInt();
                            scanner.nextLine();
                            Salle salleSeance = cinema.consulterSalle(num);
                            Seance seance = new Seance(film, salleSeance, new Date());
                            cinema.ajouterSeance(seance);
                            System.out.println("Séance ajoutée !");
                        } catch (FilmIntrouvableException | SalleIntrouvableException e) {
                            System.out.println("Erreur : " + e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.print("Titre du film : ");
                        String filmAchat = scanner.nextLine();
                        try {
                            cinema.acheterPlace(filmAchat);
                            System.out.println("Place achetée !");
                        } catch (FilmIntrouvableException | PlaceIndisponibleException e) {
                            System.out.println("Erreur : " + e.getMessage());
                        }
                        break;

                    case 6:
                        System.out.print("Titre du film : ");
                        String filmVente = scanner.nextLine();
                        System.out.print("Nombre de places : ");
                        int nb = scanner.nextInt();
                        scanner.nextLine();
                        try {
                            cinema.vendrePlace(filmVente, nb);
                            System.out.println(nb + " places vendues !");
                        } catch (FilmIntrouvableException | PlaceIndisponibleException e) {
                            System.out.println("Erreur : " + e.getMessage());
                        }
                        break;

                    case 7:
                        System.out.println("Chiffre d'affaires : " + cinema.consulterChiffreAffaires() + " DH");
                        break;

                    case 8:
                        System.out.print("Titre du film : ");
                        String filmTaux = scanner.nextLine();
                        try {
                            System.out.println("Taux de remplissage : " + cinema.tauxRemplissage(filmTaux) + "%");
                        } catch (FilmIntrouvableException e) {
                            System.out.println("Erreur : " + e.getMessage());
                        }
                        break;

                    case 9:
                        System.out.print("Titre du film : ");
                        String filmConsulte = scanner.nextLine();
                        try {
                            System.out.println("Film : " + cinema.consulterFilm(filmConsulte));
                        } catch (FilmIntrouvableException e) {
                            System.out.println("Erreur : " + e.getMessage());
                        }
                        break;

                    case 10:
                        System.out.print("Numéro de salle : ");
                        int numConsult = scanner.nextInt();
                        scanner.nextLine();
                        try {
                            Salle salleConsult = cinema.consulterSalle(numConsult);
                            System.out.println("Salle : " + salleConsult.getNom() + " (Capacité : " + salleConsult.getCapacite() + ")");
                        } catch (SalleIntrouvableException e) {
                            System.out.println("Erreur : " + e.getMessage());
                        }
                        break;

                    case 0:
                        continuer = false;
                        break;

                    default:
                        System.out.println("Choix invalide !");
                }
            }

        } catch (AuthenticationException e) {
            System.out.println("Erreur d'authentification : " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Fin du programme - ressources libérées.");
        }
    }
}