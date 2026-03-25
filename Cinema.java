package Cim;
import java.io.*;
import java.util.*;

public class Cinema implements IUserCinema, IVendeurCinema, IAdminCinema, Serializable {
    private List<Films> films = new ArrayList<>();
    private List<Salle> salles = new ArrayList<>();
    private List<Seance> seances = new ArrayList<>();
    private boolean authentifie = false;

    public void authentifier(String login, String password) {
        if (login.equals("admin") && password.equals("1234")) {
            authentifie = true;
        } else {
            throw new AuthenticationException("Identifiants incorrects !");
        }
    }

    @Override
    public Films consulterFilm(String titre) throws FilmIntrouvableException {
        return films.stream()
                .filter(f -> f.getTitre().equalsIgnoreCase(titre))
                .findFirst()
                .orElseThrow(() -> new FilmIntrouvableException("Film introuvable : " + titre));
    }

    @Override
    public Salle consulterSalle(int numero) throws SalleIntrouvableException {
        return salles.stream()
                .filter(s -> s.getNumero() == numero)
                .findFirst()
                .orElseThrow(() -> new SalleIntrouvableException("Salle introuvable : " + numero));
    }

    @Override
    public void acheterPlace(String titreFilm) throws FilmIntrouvableException, PlaceIndisponibleException {
        Seance seance = seances.stream()
                .filter(s -> s.getFilm().getTitre().equalsIgnoreCase(titreFilm))
                .findFirst()
                .orElseThrow(() -> new FilmIntrouvableException("Film introuvable : " + titreFilm));
        seance.vendrePlace(1);
    }

    @Override
    public void vendrePlace(String titreFilm, int nombre) throws FilmIntrouvableException, PlaceIndisponibleException {
        if (!authentifie) throw new AuthenticationException("Vendeur non authentifié !");
        Seance seance = seances.stream()
                .filter(s -> s.getFilm().getTitre().equalsIgnoreCase(titreFilm))
                .findFirst()
                .orElseThrow(() -> new FilmIntrouvableException("Film introuvable : " + titreFilm));
        seance.vendrePlace(nombre);
    }

    @Override
    public void ajouterFilm(Films film) {
        if (!authentifie) throw new AuthenticationException("Admin non authentifié !");
        films.add(film);
    }

    @Override
    public void ajouterSalle(Salle salle) {
        if (!authentifie) throw new AuthenticationException("Admin non authentifié !");
        salles.add(salle);
    }

    @Override
    public void ajouterSeance(Seance seance) {
        if (!authentifie) throw new AuthenticationException("Admin non authentifié !");
        seances.add(seance);
    }

    @Override
    public double consulterChiffreAffaires() {
        return seances.stream()
                .mapToDouble(s -> s.getPlacesVendues() * s.getSalle().getPrixPlace())
                .sum();
    }

    @Override
    public double tauxRemplissage(String titreFilm) throws FilmIntrouvableException {
        Seance seance = seances.stream()
                .filter(s -> s.getFilm().getTitre().equalsIgnoreCase(titreFilm))
                .findFirst()
                .orElseThrow(() -> new FilmIntrouvableException("Film introuvable : " + titreFilm));
        return (double) seance.getPlacesVendues() / seance.getSalle().getCapacite() * 100;
    }

    @Override
    public void chargerFilms(String fichier) throws FichierCinemaException {
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            int numLigne = 1;
            while ((ligne = br.readLine()) != null) {
                String[] parts = ligne.split(";");
                if (parts.length != 2) {
                    throw new FichierCinemaException("Erreur format ligne " + numLigne);
                }
                films.add(new Films(parts[0], parts[1]));
                numLigne++;
            }
        } catch (IOException e) {
            throw new FichierCinemaException("Erreur lecture fichier", e);
        }
    }

    @Override
    public void serialiserCinema(String fichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}