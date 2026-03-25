package Cim;

public interface IVendeurCinema extends IUserCinema {
    void vendrePlace(String titreFilm, int nombre) throws FilmIntrouvableException, PlaceIndisponibleException;
}