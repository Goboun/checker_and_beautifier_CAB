package cli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exception.TypeException;

/**
* Représente un fichier et on vérifie si toutes les fonctions ont un type indiqué
*/
public class Type {
	/**
	* Représente le nom du fichier à traiter
	*/
	private String nom;

	/**
	* Instancie un objet Type à partir du nom d'un fichier
	* @param nom le nom du fichier
	*/
	public Type(String nom){
		this.nom = nom;
	}
	
	/**
	* Vérifie si chaque fonction du fichier a un type indiqué
	* @exception TypeException lève une exception personnalisé si une fonction n'a pas de type indiqué
	*/
	public void verifierErreurTypage() throws TypeException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(nom));
		int annotation = 1;
		
		String line = br.readLine();
		while(line != null) {
			if(line.contains("def")){
				if(!line.contains("->")){
					annotation = 0;
				}
			}

			line = br.readLine();
		}
		br.close();
		
		if(annotation == 0) {
			throw new TypeException();
		}
	}	
}
