package pobj.tme5;

public class InvalidMultiSetFormat extends Exception {
	
	/**
	 * classe d'exception qui gère les cas où le format d'affichage d'un MultiSet est invalide
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMultiSetFormat(String message) {
		super(message);
	}
	
	public InvalidMultiSetFormat(String message, Throwable cause) {
		super(message, cause);
	}

}
