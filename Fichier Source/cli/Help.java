package cli;

/**
* Représente une méthode pour afficher le manuel d'utilisation
*/
public class Help {

	/**
	* Instancie un objet Help pour seulement utiliser la méthode d'affichage
	*/
    public Help(){}

    /**
    * Retourne le manuel d'utilisation pour l'afficher
    * @return le manuel d'utilisation
    */
    public String help() {
        return "-h ou --help: Affiche les différentes des commandes ( taper help pour avoir help n'est pas malin)\n" 
                + "-d: Affiche la liste des fichiers avec les chemins relatifs\n" 
                + "-stats: Je pense que ça affiche les stats d'un fichier\n" 
                + "-f: Pour renseigner un fichier\n" 
                + "--type: Vérifie les erreurs de typage\n" 
                + "--head: Vérifie si l'entête est présente, sinon remplace\n" 
                + "--pydoc: Pour afficher la documentation python\n" 
                + "--sbutf8: Vérifie les fichiers avec shebang python et encodage UTF-8\n" 
                + "--comment: Vérifie les commentaires, et/ou possibilité d'ajoute\n" ;
    }
}