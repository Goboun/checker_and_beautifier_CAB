package cli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exception.PydocException;
import exception.FonctionException;

/**
* Représente un fichier qu'on parcourt pour vérifier si chaque fonction a le squelette de commentaire pydoc
*/
public class Pydoc {
	/**
	* Représente le nom du fichier à parcourir
	*/
	private String nom;
	
	/**
	* Instancie un objet Pydoc à partir du nom d'un fichier
	* @param nom le nom du fichier
	*/
	public Pydoc(String nom) {
		this.nom = nom;
	}
	
	/**
	* Vérifie que toutes les fonctions du fichier ont un squelette de commentaire pydoc
	* @exception PydocException lève une exception personnalisée quand une fonction n'a pas le pydoc
	* @exception FonctionException lève une exception personnalisée si le fichier n'a pas de fonction
	*/
	public void verifierPyDoc() throws PydocException, FonctionException, IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(nom));
		int fonction = 0; // compteur de nombre de fonctions
		int commentaire = 0; // compteur de fonctions avec un commentaire
		String line = br.readLine();
		while(line != null) {
			if(line.contains("def")){
				// Si on détecte minimum une fonction (true)
				fonction += 1;
				
				line = br.readLine();
				if(line.equals("\t\"\"\"!")) {
					line = br.readLine();
					if(line.contains("\t@")) {
						line = br.readLine();
						while(!(line.equals("\t\"\"\"")) && line != null) {
							line = br.readLine();
						}
						if (line.equals("\t\"\"\"")) {
							//Après avoir vérifier que le squelette PyDoc est présent
							commentaire += 1;
						}
					}
				}
			}
			line = br.readLine();
		}
		br.close();

		if(fonction == 0){
			throw new FonctionException();
		}
		if(fonction != commentaire) {
			throw new PydocException();
		}
	}
}
