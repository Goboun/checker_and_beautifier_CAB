package exception;

/**
* Représente une exception personnalisé quand le fichier n'est pas un fichier .py
*/
public class FichierInvalideException extends Exception{

	/**
	* Pas utilisé
	*/
	private static final long serialVersionUID = 1L;

	/**
	* Instancie un objet FichierInvalideException avec un message défini pour l'afficher
	*/
	public FichierInvalideException() {
		super("Exception: \nLe fichier indiqué n'est pas un fichier .py");
	}
}

