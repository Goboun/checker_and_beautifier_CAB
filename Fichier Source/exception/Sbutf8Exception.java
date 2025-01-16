package exception;

/**
* Représente une exception personnalisé quand les deux lignes de commentaires sont déjà présentes dans un fichier
*/
public class Sbutf8Exception extends Exception {
	/**
	* Pas utilisé
	*/
	private static final long serialVersionUID = 1L;
	/**
	* Instancie un objet Sbutf8Exception avec un message défini pour l'afficher
	*/
	public Sbutf8Exception() {
		super("Exception: \nLes deux lignes de commentaires sont déjà là.");
	}
}
