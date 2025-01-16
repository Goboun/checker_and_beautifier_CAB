package cli;

import exception.ArgumentInvalideException;
import exception.FichierInvalideException;
import exception.NombresParametresException;

/**
* Représente les paramètres donnés quand on tape la commande pour lancer le mode terminal 
*/
public class Parametre {
	/**
	* Représente l'aide (-h), un dossier (-d), ou un fichier (-f)
	*/
	private String type; //-h/-d/-f
	/**
	* Représente le nom du dossier ou du fichier, un fichier .py normalement
	*/
	private String nom; //.py ou repertoire
	
	//--type/--head//--pydoc//--sbutf8//--comment//-stat
	/**
	* Représente la 1ère option parmi:
	* --type pour le typage d'un fichier,
	* --head pour les deux lignes de commentaires d'un fichier,
	* --pydoc pour le pydoc d'un fichier,
	* --sbutf8 pour ajouter les deux lignes de commentaires d'un fichier,
	* --comment pour ajouter le pydoc à chaque fonction d'un fichier,
	* -stat pour analyser un dossier, uniquement disponible pour la 1ère option
	*/
	private String option1; 
	/**
	* Représente la 2ème option applicable au fichier parmi:
	* --type pour le typage,
	* --head pour les deux lignes de commentaires,
	* --pydoc pour le pydoc,
	* --sbutf8 pour ajouter les deux lignes de commentaires,
	* --comment pour ajouter le pydoc à chaque fonction,
	*/
	private String option2;
	/**
	* Représente la 3ème option applicable au fichier parmi:
	* --type pour le typage,
	* --head pour les deux lignes de commentaires,
	* --pydoc pour le pydoc,
	* --sbutf8 pour ajouter les deux lignes de commentaires,
	* --comment pour ajouter le pydoc à chaque fonction,
	*/
	private String option3;
	/**
	* Représente le nombre de paramètres lors du lancement de l'application en mode terminal, 
	* sert à invoquer le bon constructeur et définir seulement ce qu'il nous faut
	*/
	private int nombreParametre;
	
	/**
	* Instancie un objet Parametre quand le nombre de paramètre est 0 ou supérieur à 5, 
	* sert à lever une exception
	* @param nombreParametre le nombre de paramètre, ici 0 typiquement
	*/
	public Parametre(
		int nombreParametre) {
		this.nombreParametre = nombreParametre;
	}
	
	/**
	* Instancie un objet Parametre quand le nombre d'argument est 1, 
	* pour appeler le Help typiquement
	* @param type le type, ici -h typiquement
	* @param nombreParametre le nombre de paramètre, ici 1 typiquement
	*/
	public Parametre(
			String type, int nombreParametre){
		this.type = type;
		this.nombreParametre = nombreParametre;
	}
	
	/**
	* Instancie un objet Parametre quand le nombre d'argument est 2, 
	* pour afficher les fichiers du dossier donné ou les informations du fichier donné
	* @param type le type à analyser
	* @param nom le nom du dossier ou du fichier
	* @param nombreParametre le nombre de paramètre, ici 2 typiquement
	*/
	public Parametre(
			String type, String nom, int nombreParametre){
		this.type = type;
		this.nom = nom;
		this.nombreParametre = nombreParametre;
	}
	
	/**
	* Instancie un objet Parametre quand le nombre d'argument est 3, 
	* pour afficher les fichiers du dossier donné avec ces statistiques, 
	* ou les informations du fichier donné ainsi qu'appliquer 1 des options au fichier
	* @param type le type à analyser
	* @param nom le nom du dossier ou du fichier
	* @param option1 la seule option à appliquer
	* @param nombreParametre le nombre de paramètre, ici 3 typiquement
	*/
	public Parametre(
			String type, String nom,
			String option1, int nombreParametre){
		this.type = type;
		this.nom = nom;
		this.option1 = option1;
		this.nombreParametre = nombreParametre;
	}
	
	/**
	* Instancie un objet Parametre quand le nombre d'argument est 4, 
	* pour afficher les informations du fichier donné ainsi qu'appliquer 2 des options au fichier
	* @param type le type à analyser, -f typiquement
	* @param nom le nom du fichier
	* @param option1 la 1ère option à appliquer
	* @param option2 la 2ème option à appliquer
	* @param nombreParametre le nombre de paramètre, ici 4 typiquement
	*/
	public Parametre(
			String type, String nom,
			String option1, String option2, int nombreParametre){
		this.type = type;
		this.nom = nom;
		this.option1 = option1;
		this.option2 = option2;
		this.nombreParametre = nombreParametre;
	}
	
	/**
	* Instancie un objet Parametre quand le nombre d'argument est 5, 
	* pour afficher les informations du fichier donné ainsi qu'appliquer 3 des options au fichier
	* @param type le type à analyser, -f typiquement
	* @param nom le nom du fichier
	* @param option1 la 1ère option à appliquer
	* @param option2 la 2ème option à appliquer
	* @param option3 la 3ème option à appliquer
	* @param nombreParametre le nombre de paramètre, ici 5 typiquement
	*/
	public Parametre(
			String type, String nom,
			String option1, String option2, String option3, int nombreParametre){
		this.type = type;
		this.nom = nom;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.nombreParametre = nombreParametre;
	}
	
	/**
	* Vérifie si les paramètres donnés sont correctes, que la ligne de commande ait un sens
	* @return true si la commande a un sens ou false sinon
	* @exception NombresParametresException lève une exception personnalisé si le nombre de paramètres est égale à 0 ou supérieur à 5
	* @exception FichierInvalideException lève une exception personnalisé si le fichier n'est pas un fichier .py
	* @exception ArgumentInvalideException lève une exception personnalisé si un argument ne correspond à rien de ce qui est attendu
	*/
	public boolean verification() throws NombresParametresException, FichierInvalideException, ArgumentInvalideException{
		if(nombreParametre == 1 
				&& (type.equals("-h") || type.equals("--help"))){
			return true;
		}
		if(nombreParametre == 2) {
			if(type.equals("-d")) {
				return true;
			}
			if(type.equals("-f")) {
				if (getExtension().equals("py")){
					return true;
				}
				else {
					throw new FichierInvalideException();
				}
			} 
		}
		if(nombreParametre == 3) {
			if(type.equals("-d") && option1.equals("-stat")) {
				return true;
			}
			if(type.equals("-f")) {
				if (getExtension().equals("py")){
					if(option1.equals("--type") || option1.equals("--head") || option1.equals("--pydoc")
						|| option1.equals("--sbutf8") ||option1.equals("--comment")) {
						return true;
					}
				}
				else {
					throw new FichierInvalideException();
				}
			}
		}
		if(nombreParametre == 4) {
			if(type.equals("-f")) {
				if (getExtension().equals("py")){
					if((option1.equals("--type") || option1.equals("--head") || option1.equals("--pydoc")
						|| option1.equals("--sbutf8") ||option1.equals("--comment"))
					&& (option2.equals("--type") || option2.equals("--head") || option2.equals("--pydoc")
						|| option2.equals("--sbutf8") ||option2.equals("--comment"))) {
						return true;
					}
				}
				else {
					throw new FichierInvalideException();
				}
			}
		}
		if(nombreParametre == 5) {
			if(type.equals("-f")) {
				if (getExtension().equals("py")){
					if((option1.equals("--type") || option1.equals("--head") || option1.equals("--pydoc")
						|| option1.equals("--sbutf8") ||option1.equals("--comment"))
					&& (option2.equals("--type") || option2.equals("--head") || option2.equals("--pydoc")
						|| option2.equals("--sbutf8") ||option2.equals("--comment"))
					&& (option3.equals("--type") || option3.equals("--head") || option3.equals("--pydoc")
						|| option3.equals("--sbutf8") ||option3.equals("--comment"))) {
						return true;
					}
				}
				else {
					throw new FichierInvalideException();
				}
			}
		}
		if(nombreParametre > 5) {
			throw new NombresParametresException();
		}
		throw new ArgumentInvalideException();
	}

	/**
	* Obtenir le type de la ligne de commande
	* @return type -h, -d, ou -f
	*/
	public String getType() {
		return type;
	}
	/**
	* Obtenir le nom du dossier ou le nom du fichier
	* @return nom le nom du dossier ou le nom du fichier
	*/
	public String getNom() {
		return nom;
	}
	/**
	* Obtenir l'extension d'un fichier pour vérifier que c'est un fichier .py
	* @return extension l'extension du fichier
	*/
	public String getExtension() {
		String nom = getNom();
		int i = nom.lastIndexOf(".");
		String extension = "";
		if (i > 0) {
			extension = nom.substring(i+1);
		}
		return extension;
	}
	
	/**
	* Obtenir la 1ère option de la ligne de commande
	* @return option1 --type, --head, --pydoc, --sbutf8, --comment, -stat
	*/
	public String getOption1() {
		return option1;
	}
	/**
	* Obtenir la 2ème option de la ligne de commande
	* @return option2 --type, --head, --pydoc, --sbutf8, --comment
	*/
	public String getOption2() {
		return option2;
	}
	/**
	* Obtenir la 3ème option de la ligne de commande
	* @return option3 --type, --head, --pydoc, --sbutf8, --comment
	*/
	public String getOption3() {
		return option3;
	}
	/**
	* Changer le type
	* @param type le type à changer
	*/
	public void setType(String type) {
		this.type = type;
	}
	/**
	* Changer le nom
	* @param nom le nom à changer
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	* Changer l'option 1
	* @param option1 la 1ère option à changer
	*/
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	/**
	* Changer l'option 2
	* @param option2 la 2ème option à changer
	*/
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	/**
	* Changer l'option 3
	* @param option3 la 3ème option à changer
	*/
	public void setOption3(String option3) {
		this.option3 = option3;
	}

}
