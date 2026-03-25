package Cim;
public class SalleVIP extends Salle {
	 public SalleVIP(int numero, String nom, int capacite) {
	     super(numero, nom, capacite);
	 }
	 @Override
	 public int getPrixPlace() { return 60; }
	}