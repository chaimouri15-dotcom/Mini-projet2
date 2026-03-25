package Cim;

public class SalleNormale extends Salle {
	 public SalleNormale(int numero, String nom, int capacite) {
	     super(numero, nom, capacite);
	 }
	 @Override
	 public int getPrixPlace() { return 30; }
	}