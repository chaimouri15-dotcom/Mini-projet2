package Cim;

public interface IUserCinema {
    Films consulterFilm(String titre) throws FilmIntrouvableException;
    Salle consulterSalle(int numero) throws SalleIntrouvableException;
    void acheterPlace(String titreFilm) throws FilmIntrouvableException, PlaceIndisponibleException;
}
