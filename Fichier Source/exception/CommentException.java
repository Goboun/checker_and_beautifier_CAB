package exception;

/**
* Représente une exception personnalisé quand chaque fonction d'un fichier a déjà un pydoc
*/
public class CommentException extends Exception {
	/**
	* Pas utilisé
	*/
	private static final long serialVersionUID = 1L;

	/**
	* Instancie un objet CommentException avec un message défini pour l'afficher
	*/
	public CommentException() {
		super("Exception: \nChaque fonction a déjà un pydoc.");
	}
}
