package exception;

/**
* Représente une exception personnalisé quand le fichier n'a aucune fonction
*/
public class FonctionException extends Exception {
	/**
	* Pas utilisé
	*/
	private static final long serialVersionUID = 1L;

	/**
	* Instancie un objet FonctionException avec un message défini pour l'afficher
	*/
	public FonctionException() {
		super("Exception: \nLe fichier ne possède aucune fonction.");
	}
}