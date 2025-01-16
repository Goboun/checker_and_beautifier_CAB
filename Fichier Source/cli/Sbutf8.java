package cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import exception.Sbutf8Exception;

/**
* Représente un fichier auquel on ajoute les deux premières lignes de commentaires
*/
public class Sbutf8 {
	/**
	* Représente le nom du fichier à traiter
	*/
	private String nom;
	
	/**
	* Instancie un objet Sbutf8 à partir du nom d'un fichier
	* @param nom le nom du fichier
	*/
	public Sbutf8(String nom){
		this.nom = nom;
	}
	
	/**
	* Ajoute les deux premières lignes de commentaires si elles ne sont pas présentes, 
	* ou seulement l'une des deux s'il y en a qu'une seule
	* @exception Sbutf8Exception lève une exception personnalisé si les deux lignes sont déjà présentes
	*/
	public void ajouterDeuxPremieresLignesDeCommentaire() throws Sbutf8Exception, IOException {
		//1ER TRAITEMENT: LEVER EXCEPTION S'IL Y A LES 2 LIGNES
		BufferedReader br = new BufferedReader(new FileReader(nom));
		int exception = 0;
		String line = br.readLine();
		if(line.equals("#!/usr/bin/python3")) {
			exception = exception + 1;
		}
		line = br.readLine();
		if(line.equals("# -*- coding: utf-8 -*-")) {
			exception = exception + 1;
		}
		br.close();
		if(exception == 2) {
			throw new Sbutf8Exception();
		}
 
		//2ND TRAITEMENT: SAUVEGARDER LE CONTENU ORIGINAL
		br = new BufferedReader(new FileReader(nom));
		line = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (line!=null) {
			sb.append(line + "\n");
			line = br.readLine();
		}
		br.close();
		
		//3EME TRAITEMENT: AJOUTER #!/usr/bin/python3 SI PAS PRESENT
		br = new BufferedReader(new FileReader(nom));
		line = br.readLine();
		br.close();
		if(!(line.equals("#!/usr/bin/python3"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(nom));
			bw.write("#!/usr/bin/python3");
			bw.newLine();
			bw.write(sb.toString());
			bw.close();
		}
		
		//4EME TRAITEMENT: RESAUVEGARDER LE CONTENU, MODIFIER OU NON
		br = new BufferedReader(new FileReader(nom));
		line = br.readLine();
		line = br.readLine();
		StringBuffer sb1 = new StringBuffer();
		while (line!=null) {
			sb1.append(line + "\n");
			line = br.readLine();
		}
		br.close();
		
		//5EME TRAITEMENT: AJOUTER # -*- coding: utf-8 -*- SI PAS PRESENT
		br = new BufferedReader(new FileReader(nom));
		line = br.readLine();
		line = br.readLine();
		br.close();
		if(!(line.equals("# -*- coding: utf-8 -*-"))) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(nom));
			bw.write("#!/usr/bin/python3");
			bw.newLine();
			bw.write("# -*- coding: utf-8 -*-");
			bw.newLine();
			bw.write(sb1.toString());
			bw.close();
		}
	}
}
