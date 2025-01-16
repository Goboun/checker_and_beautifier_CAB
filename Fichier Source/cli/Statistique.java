package cli; 

import java.io.FileReader;
import java.io.IOException;

import exception.StatsException;

import java.io.BufferedReader;
import java.io.File;

/**
* Affiche les statistiques de qualité d'un répertoire
*/
public class Statistique{
	/**
	* Initialise un objet Statistique
	*/
	public Statistique() {}
	
    //Affichage des statistiques d'un repertoire
	
	/**
	* Affiche les stats d'un répertoire
	* @param nom le nom du répertoire
	* @exception StatsException lève une exception personnalisé ce n'est pas un répertoire valide
	* @return sb retourne l'affichage des stats du répertoire
	*/
    public String afficheStats(String nom)throws StatsException, IOException{
        File fichier = new File(nom);
        if (!fichier.isDirectory()) {
			throw new StatsException();
        }
        
		StringBuffer sb = new StringBuffer();
        sb.append(sousAffStats(nom) + "\n");
        
        File[] files = fichier.listFiles();
        if (files != null) {
	        for (File file : files) {
	        	// On vérifie si il y a un sous dossier (carte magie)
	            if (file.isDirectory()) {
	                sb.append(afficheStats(file.getPath()));
	            }
	        }
        }
        return sb.toString();
    }
	
	/**
	* Sert pour la méthode afficheStats
	* @param nom le nom du répertoire
	* @return retourne l'affichage des stats du répertoire
	*/
	public String sousAffStats(String nom){
        return "Statistiques du répertoire : " + nom
        + "\n" + " - nombre de fichier analysés : " + nbrFichier(nom) 
        + "\n" + " - nombre de fonctions : " + nbrFonction(nom)
        + "\n" + " - pourcentage des fonctions ayant des annotations de typage : " + pctTypage(nom) * 100 / nbrFonction(nom)
        + "\n" + " - pourcentage des fichiers avec shebang python et encodage UTF-8 : " + pctShebang_Encoding(nom) * 100 / nbrFichier(nom)
        + "\n" + " - pourcentage des fonctions avec commentaires pydoc : " + pctCommentaires(nom) * 100 / nbrFichier(nom);
	}
	
    //Divisons pour mieux régner
    
	/**
	* Sert pour la méthode afficheStats
	* @param nom le nom du répertoire
	* @return retourne si le répertoire en est un ou non
	*/
    public static int estFichier(String nom) {
        File fichier = new File(nom);
        
        if (!fichier.isDirectory()) {
            System.out.println("Erreur : Ce n'est pas un répertoire.");
            return -1;
            // Retourne le code d'erreur -1
        }
        
        // On crée une liste des fichiers présents dans le répertoire
        File[] files = fichier.listFiles();

        // On vérifie si il y a un minimum de fichiers dans le répertoire
        if (files == null) {
            System.out.println("Erreur : Pas de fichiers retrouvés.");
            return -1;
        }
        
        //si ça c'est bien passé on retourne un
        //je factorise le code
        return 1;
    }
    
    // Obtention du nombre de fichier
    
	/**
	* Sert pour la méthode afficheStats
	* @param nom le nom du répertoire
	* @return somme retourne le nombre de fichier dans le répertoire 
	*/
    public int nbrFichier(String nom){
        File fichier = new File(nom);

        if(estFichier(nom) != 1){
        	//erreur ducoup :)
        	return -1;
        }
        
        // On crée une liste des fichiers présents dans le répertoire
        File[] files = fichier.listFiles();
        
        // Bah sinon, ez, on fait la somme du tableau de fichiers
        int somme = 0;
        for (File file : files) {
        	// On vérifie bien que c'est un fichier et pas un sous répertoire (carte piège)
            if (file.isFile()) {
            	somme++;
            }
        }

        return somme;
    }

    // Obtention du nombre de fonctions
    
	/**
	* Sert pour la méthode afficheStats
	* @param nom le nom du répertoire
	* @return somme retourne le nombre de fonctions dans le répertoire 
	*/
    public int nbrFonction(String nom){
        File fichier = new File(nom);
        
        if(estFichier(nom) != 1){
        	//erreur ducoup :)
        	return -1;
        }
        
        // On crée une liste des fichiers présents dans le répertoire
        File[] files = fichier.listFiles();
        
        int somme = 0;
        
        // Parcours itératif de tous les fichiers du répertoire
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".py")) {
            	
            	//Divisons pour mieux reigner
                somme += sommeFonction(file);
            }
        }

        return somme;
    }

	/**
	* Sert pour la méthode afficheStats
	* @param file le nom du répertoire
	* @return estFonction retourne le nombre de fichier dans le répertoire 
	*/
    public int sommeFonction(File file) {
        int estFonction = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // Parcours itératif de chaque ligne du fichier
            while ((line = reader.readLine()) != null) {
                // Vérifier si la ligne contient un def, élement signature d'une fonction Python
                if (line.contains("def ")) {
                	estFonction++;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur : Lecture du fichier " + file.getName());
        }

        return estFonction;
    }
    
    // Obtention du nombre de fonctions
    
	/**
	* Sert pour la méthode afficheStats
	* @param nom le nom du répertoire
	* @return somme retourne le pourcentage de typage
	*/
    public int pctTypage(String nom){
        File fichier = new File(nom);
        
        if(estFichier(nom) != 1){
        	//erreur ducoup :)
        	return -1;
        }
        
        // On crée une liste des fichiers présents dans le répertoire
        File[] files = fichier.listFiles();
        
        int somme = 0;
        
        // Parcours itératif de tous les fichiers du répertoire
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".py")) {
            	
            	//Divisons pour mieux reigner
                somme += estTypee(file);
            }
        }

        return somme;
    }
    
	/**
	* Sert pour la méthode afficheStats
	* @param file le nom du répertoire
	* @return somme sert pour la méthode pctTypage
	*/
    public static int estTypee(File file) {
        int somme = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // Parcours itératif (pas récursif eheh) de chaque ligne du fichier
            while ((line = reader.readLine()) != null) {
                // Vérifions si la ligne contient une annotation de typage en Python
                if (line.contains("def ") 
                		&& line.contains("->") 
                		&& (line.contains("int") 
                		||line.contains("float")
                		||line.contains("complex")
                		||line.contains("list")
                		||line.contains("tuple")
                		||line.contains("range")
                		||line.contains("bytes")
                		||line.contains("bytearray")
                		||line.contains("memoryview")
                		||line.contains("dict")
                		||line.contains("bool")
                		||line.contains("set")
                		||line.contains("frozenset")
                		||line.contains("str"))
                		&& line.contains(":")) {
                	//line.contains(":") && line.trim().endsWith(":")
                	somme++;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur : Lecture du fichier " + file.getName());
        }

        return somme;
    }
    
	/**
	* Sert pour la méthode afficheStats
	* @param nom le nom du répertoire
	* @return somme retourne le pourcentage de shebang
	*/
    public static int pctShebang_Encoding(String nom){
        File fichier = new File(nom);
        
        if(estFichier(nom) != 1){
        	//erreur ducoup :)
        	return -1;
        }
        
        // On crée une liste des fichiers présents dans le répertoire
        File[] files = fichier.listFiles();
        
        int somme = 0;

        // Parcours itératif (pas récursif eheh) de chaque ligne du fichier
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".py")) {
            	//BIEN s'assurer que c'est un fichier Python avec l'extension
                if (estUTF8(file)) {
                    somme++;
                }
            }
        }

        return somme;
    }

	/**
	* Sert pour la méthode afficheStats
	* @param file le nom du répertoire
	* @return retourne si une fonction possède le shebang Python
	*/
    public static boolean estUTF8(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Vérif la première ligne pour le shebang Python
            String firstLine = reader.readLine();
            if (firstLine != null && firstLine.contains("#!/usr/bin/python3")) {
                // Vérif la deuxième ligne pour l'encodage UTF-8
                String secondLine = reader.readLine();
                return secondLine != null && secondLine.contains("# -*- coding: utf-8 -*-");
            }
        } catch (IOException e) {
            System.out.println("Erreur : Lecture du fichier " + file.getName());
        }
        //cas échéant
        return false;
    }
    
	/**
	* Sert pour la méthode afficheStats
	* @param nom le nom du répertoire
	* @return somme retourne le pourcentage de commentaires
	*/
    public static int pctCommentaires(String nom){
        File fichier = new File(nom);
        
        if(estFichier(nom) != 1){
        	//erreur ducoup :)
        	return -1;
        }
        
        // On crée une liste des fichiers présents dans le répertoire
        File[] files = fichier.listFiles();
        
        int somme = 0;

        // Parcours itératif (pas récursif eheh) de chaque ligne du fichier
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".py")) {
            	//BIEN s'assurer que c'est un fichier Python avec l'extension
                if (estCommentaire(file)) {
                    somme++;
                }
            }
        }

        return somme;
    }
    
	/**
	* Sert pour la méthode afficheStats
	* @param file le nom du répertoire
	* @return retourne si une fonction possède des commentaires de fonction pydoc
	*/
    public static boolean estCommentaire(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // Vérifier si une ligne commence par ''' ou """ pour les doc strings ect 
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("'''") || line.startsWith("\"\"\"")) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur : Lecture du fichier " + file.getName());
        }

        return false;
    }
}