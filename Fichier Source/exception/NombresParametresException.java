package exception;

/**
* Représente une exception personnalisé quand le nombre de paramètres est 0 ou supérieur à 5
*/
public class NombresParametresException extends Exception{
	/**
	* Pas utilisé
	*/
	private static final long serialVersionUID = 1L;

	/**
	* Instancie un objet NombresParametresException avec un message défini pour l'afficher
	*/
	public NombresParametresException() {
		super("Exception: \nEntre 1 et 5 arguments sont attendus");
	}
	
}
