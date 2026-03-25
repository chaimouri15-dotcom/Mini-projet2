package Cim;

public abstract class Salle {
	 protected int numero;
	 protected String nom;
	 protected int capacite;

	 public Salle(int numero, String nom, int capacite) {
	     this.numero = numero;
	     this.nom = nom;
	     this.capacite = capacite;
	 }

	 public int getNumero() { return numero; }
	 public String getNom() { return nom; }
	 public int getCapacite() { return capacite; }

	 public abstract int getPrixPlace();
	}