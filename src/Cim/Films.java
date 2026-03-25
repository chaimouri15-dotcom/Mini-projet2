package Cim;


public class Films {
    private String titre;
    private String realisateur;

    public Films(String titre, String realisateur) {
        this.titre = titre;
        this.realisateur = realisateur;
    }

    public String getTitre() { return titre; }
    public String getRealisateur() { return realisateur; }

    @Override
    public String toString() {
        return titre + " - " + realisateur;
    }
}