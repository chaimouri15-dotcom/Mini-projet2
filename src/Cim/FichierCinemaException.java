package Cim;
public class FichierCinemaException extends Exception {
	 public FichierCinemaException(String message) {
	     super(message);
	 }

	 // Chaînage d’exception pour conserver la cause originale (IOException par ex.)
	 public FichierCinemaException(String message, Throwable cause) {
	     super(message, cause);
	 }
	}