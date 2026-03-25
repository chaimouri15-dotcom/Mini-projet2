package Cim;


public interface IAdminCinema extends IVendeurCinema {
    void ajouterFilm(Films film);
    void ajouterSalle(Salle salle);
    void ajouterSeance(Seance seance);
    double consulterChiffreAffaires();
    double tauxRemplissage(String titreFilm) throws FilmIntrouvableException;
    void chargerFilms(String fichier) throws FichierCinemaException;
    void serialiserCinema(String fichier);
	Films consulterFilm(String titre) throws FilmIntrouvableException;
}