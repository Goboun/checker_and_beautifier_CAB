package exception;

/**
* Représente une exception personnalisé quand une fonction n'a pas de type indiqué
*/
public class TypeException extends Exception {
	/**
	* Pas utilisé
	*/
	private static final long serialVersionUID = 1L;
	/**
	* Instancie un objet TypeException avec un message défini pour l'afficher
	*/
	public TypeException() {
		super("Exception: \nCertaines fonctions n'ont pas de type indiqué.");
	}
}
