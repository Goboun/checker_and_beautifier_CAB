package exception;

/**
* Représente une exception personnalisé quand le répertoire indiqué n'est pas un répertoire
*/
public class StatsException extends Exception {
	/**
	* Pas utilisé
	*/
	private static final long serialVersionUID = 1L;
	/**
	* Instancie un objet StatsException avec un message défini pour l'afficher
	*/
	public StatsException() {
		super("Exception: \nLe chemin spécifié n'est pas un répertoire valide.");
		//
	}
}
