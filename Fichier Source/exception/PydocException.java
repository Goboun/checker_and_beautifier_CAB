package exception;

/**
* Représente une exception personnalisé quand une fonction d'un fichier n'a pas de pydoc
*/
public class PydocException extends Exception {
	/**
	* Pas utilisé
	*/
	private static final long serialVersionUID = 1L;

	/**
	* Instancie un objet PydocException avec un message défini pour l'afficher
	*/
	public PydocException() {
		super("Exception: \nLe fichier renseigné manque de commentaire de fonction.");
	}
}
