package cli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
* Représente un objet BufferedReader qui va lire un fichier donné 
* et vérifier la présence des deux premières lignes de commentaires
*/
public class Head {
	
	/**
	* Réprésente un objet BufferedReader qui va parcourir le fichier donné
	*/
	private BufferedReader br;
	
	/**
	* Instancie un objet Head qui va initialiser le BufferedReader à partir du nom d'un fichier donné
	* @param nom le nom du fichier
	*/
	public Head(String nom) throws IOException {
		br = new BufferedReader(new FileReader(nom));
	}
	
	/**
	* Vérifie si les deux premières lignes de commentaires sont présentes
	* @return sb les informations de la présence des deux premières lignes de commentaires
	*/
	public String verifierDeuxPremieresLignesDeCommentaires() throws IOException {
		StringBuffer sb = new StringBuffer();
		String line = br.readLine();
		int i = 0;
		if (!(line.equals("#!/usr/bin/python3"))) {
			sb.append("La première ligne ''#!/usr/bin/python3'' n'existe pas \n");
		}
		else {
			i++;
		}
		line = br.readLine();
		if (!(line.equals("# -*- coding: utf-8 -*-"))) {
			sb.append("La seconde ligne ''# -*- coding: utf-8 -*-'' n'existe pas \n");
		}
		else {
			i++;
		}
		br.close();
		if(i == 2) {
			sb.append("Les deux premières lignes de commentaires sont déjà présentes.");
		}
		return sb.toString();
	}
}
