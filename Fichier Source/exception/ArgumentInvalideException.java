package exception;

/**
* Représente une exception personnalisé quand un paramètre ne correspond à rien de ce qui est attendu
*/
public class ArgumentInvalideException extends Exception{
	/**
	* Pas utilisé
	*/
	private static final long serialVersionUID = 1L;

	/**
	* Instancie un objet ArgumentInvalideException avec un message défini pour l'afficher
	*/
	public ArgumentInvalideException() {
		super("Exception: \nArgument(s) Invalide(s)");
	}

}
