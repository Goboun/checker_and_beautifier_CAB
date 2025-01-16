package cli;

import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;

/**
* Représente un répertoire
*/
public class Repertoire {
	/**
	* Représente un repertoire à partir duquel on affiche la liste des fichiers
	*/
	private File repertoire;
	/**
	* Représente le nom du repertoire
	*/
	private String nom;
	/**
	* Représente une liste de fichier, à utiliser pour la classe Statistique, 
	* à ne pas confondre avec l'autre attribut repertoire qui n'a pas de s à la fin
	*/
	private ArrayList<Fichier> repertoires;
	
	/**
	* Instancie un objet Repertoire à partir du nom d'un répertoire
	* @param nom le nom du répertoire
	*/
	public Repertoire(String nom){
		this.repertoire = new File(nom);
		this.nom = nom;
		
		//POUR HENRI ET SA CLASSE STAT
			this.repertoires = new ArrayList<Fichier>();
			for (File fileEntry : repertoire.listFiles()) {
				repertoires.add(new Fichier(fileEntry.getName()));
				//if (!(fileEntry.isDirectory())) {	}
			}
	}
	
	/**
	* Rassemble les fichiers .py présents dans l'arborescence du répertoire pour l'afficher
	* @param rep le répertoire à traiter
	* @return sb la liste des fichiers
	*/
	public String afficheRepertoire(File rep) throws IOException {
		StringBuffer sb = new StringBuffer();
		for (File fileEntry : rep.listFiles()) {
			if (fileEntry.isDirectory()) {
				sb.append(afficheRepertoire(fileEntry));
			}
			else {
				if(getExtension(fileEntry.getPath()).equals("py")) {
					sb.append(fileEntry.getPath() + "\n");
				}
			}	
		}
		return sb.toString();
	}
	
	/**
	* Obtenir l'extension d'un fichier pour vérifier que c'est un fichier .py
	* @param nom le nom du fichier
	* @return extension l'extension du fichier
	*/
	public String getExtension(String nom) {;
		int i = nom.lastIndexOf(".");
		String extension = "";
		if (i > 0) {
			extension = nom.substring(i+1);
		}
		return extension;
	}

	/**
	* Obtenir le répertoire
	* @return repertoire le repertoire
	*/
	public File getRep() {
		return repertoire;
	}
	
	/**
	* Obtenir la liste des fichiers du répertoire
	* @return repertoires la liste des fichiers du répertoire
	*/
	public ArrayList<Fichier> getRepertoires(){
		return repertoires;
	}
	
	/**
	* Obtenir le nom du répertoire
	* @return nom le nom du répertoire
	*/
	public String getNom() {
		return nom;
	}
}
