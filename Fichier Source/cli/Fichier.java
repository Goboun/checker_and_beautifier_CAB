package cli;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
* Représente un fichier comme un objet File mais manipulable plus facilement avec seulement ce qu'il nous faut et les dates
*/
public class Fichier {
	/**
	* Représente le fichier à partir duquel on va initialiser les autres attributs
	*/
	private File file;
	/**
	* Représente le nom du fichier
	*/
	private String nom;
	/**
	* Représente la taille du fichier en bytes
	*/
	private long taille;
	/**
	* Représente le chemin d'accès du fichier à partir de l'emplacement du cli.jar
	*/
	private String emplacement;
	/**
	* Représente la date de création du fichier
	*/
	private FileTime creation;
	/**
	* Représente la date de dernière modification du fichier
	*/
	private FileTime modification;
	/**
	* Représente la date du dernier accès du fichier, à ne pas confondre avec la date de modification
	*/
	private FileTime dernierAcces;
	
	/**
	* Instancie un objet Fichier
	* @param nom le nom du fichier
	*/
	public Fichier(String nom) {
		try {
			this.file = new File(nom);
			this.nom = file.getName();
			this.taille = file.length();
			this.emplacement = file.getPath();
			Path path = Paths.get(this.file.getPath());
			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
			this.creation = attr.creationTime();
			this.modification = attr.lastModifiedTime();
			this.dernierAcces = attr.lastAccessTime();
		} catch (Exception e) {}
	}
	
	/**
	* Rassemble dans une chaine de caractère les informations du fichier
	* @return informations les informations du fichier à afficher
	*/
	public String afficheInformations() {
		String informations = "Nom: " + getNom() + "\n" 
			+ "Taille: " + getTaille() + " bytes" + "\n" 
			+ "Emplacement: " + getEmplacement() + "\n"
			+ "Création: " + getCreation() + "\n"
			+ "Modification: " + getModification() + "\n"
			+ "Dernier accès: " + getDernierAcces();
		return informations;
	}

	/**
	* Obtenir le fichier
	* @return file
	*/
	public File getFile() {
		return file;}
	/**
	* Obtenir le nom du fichier
	* @return nom
	*/
	public String getNom() {
		return nom;}
	/**
	* Obtenir la taille du fichier
	* @return taille
	*/
	public long getTaille() {
		return taille;}
	/**
	* Obtenir le chemin du fichier
	* @return emplacement
	*/
	public String getEmplacement() {
		return emplacement;}
	/**
	* Obtenir la date de création du fichier
	* @return creation
	*/
	public FileTime getCreation() {
		return creation;}
	/**
	* Obtenir la date de dernière modification du fichier
	* @return modification
	*/
	public FileTime getModification() {
		return modification;}
	/**
	* Obtenir la date de dernier accès du fichier
	* @return dernierAcces
	*/
	public FileTime getDernierAcces() {
		return dernierAcces;}
}
