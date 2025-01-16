package cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import exception.CommentException;

/**
* Représente un fichier auquel on va ajouter le pydoc
*/
public class Comment {
	
	/**
	* Représente le nom du fichier
	*/
	private String nom;
	/**
	* Pour l'application graphique, représente le nom de l'utilisateur enregistré
	* Par défaut, le nom est vide, et donc inutile pour le mode terminal
	*/
	private String auteur = "";
	/**
	* Pour l'application graphique, représente la version enregistré
	* Par défaut, la version est vide
	*/
	private String version = "";
	
	/**
	* Instancie un objet Comment
	* @param nom le nom du fichier
	*/
	public Comment(String nom){
		this.nom = nom;
	}
	
	/**
	* Pour l'application graphique, instancie un objet Comment pour appliquer la méthode avec le nom et la version enregistré
	* @param nom le nom du fichier
	* @param auteur le nom de l'auteur
	* @param version le numéro de la version d'un code
	*/
	public Comment(String nom, String auteur, String version) {
		this.nom = nom;
		this.auteur = auteur;
		this.version = version;
	}
	
	/**
	* Ajoute le squelette de commentaire pydoc pour chaque fonction qui ne l'ont pas
	* @exception CommentException lève une exception personnalisé si chaque fonction a un pydoc
	*/
	public void ajouterPydoc() throws CommentException, IOException {
		//1ER TRAITEMENT: LEVER EXCEPTION S'IL Y A PYDOC A CHAQUE FONCTION
		BufferedReader br = new BufferedReader(new FileReader(nom));
		int fonction = 0;
		int exception = 0;
		String line = br.readLine();
		while(line != null) {
			if(line.contains("def")) {
				fonction = fonction + 1;
				line = br.readLine();
				if(line.equals("\t\"\"\"!")) {
					line = br.readLine();
					if(line.contains("\t@")) {
						line = br.readLine();
						while(!(line.equals("\t\"\"\"")) && line != null) {
							line = br.readLine();
						}
						if (line.equals("\t\"\"\"")) {
							exception = exception + 1;
						}
					}
				}
			}
			line = br.readLine();
		}
		br.close();
		if(fonction == exception && fonction != 0) {
			throw new CommentException();
		}
		
		int nbFctTrueOrigines = fonction;
		while(fonction != 0) {
			int nbFctOrigines = nbFctTrueOrigines;
			int nbFctOriginesCopie = nbFctTrueOrigines;
			//2ND TRAITEMENT: SAUVEGARDER LA 1ERE PARTIE
			br = new BufferedReader(new FileReader(nom));
			line = br.readLine();
			StringBuffer sb1 = new StringBuffer();
			while(nbFctOrigines != fonction - 1) {
				while (!(line.contains("def")) && line!=null) {
					sb1.append(line + "\n");
					line = br.readLine();
				}
				sb1.append(line + "\n");
				line = br.readLine();
				nbFctOrigines = nbFctOrigines - 1;
			}
			
			//3EME TRAITEMENT: SAUVEGARDER LA 2NDE PARTIE
			StringBuffer sb2 = new StringBuffer();
			while (line!=null) {
				sb2.append(line + "\n");
				line = br.readLine();
			}
			br.close();
				
			//4EME TRAITEMENT: AJOUTER pydoc SI PAS PRESENT
			br = new BufferedReader(new FileReader(nom));
			line = br.readLine();
			while(nbFctOriginesCopie != fonction - 1) {
				while (!(line.contains("def")) && line!=null) {
					line = br.readLine();
				}
				line = br.readLine();
				nbFctOriginesCopie = nbFctOriginesCopie - 1;
			}
			if(!(line.equals("\t\"\"\"!"))) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(nom));
				bw.write(sb1.toString());
				bw.write("\t\"\"\"!");
				bw.newLine();
				bw.write("\t@brief");
				bw.newLine();
				bw.write("\t@version " + version);
				bw.newLine();
				bw.write("\t@author " + auteur);
				bw.newLine();
				bw.write("\t@param");
				bw.newLine();
				bw.write("\t@return");
				bw.newLine();
				bw.write("\t@see");
				bw.newLine();
				bw.write("\t\"\"\"");
				bw.newLine();
				bw.write(sb2.toString());
				bw.close();
			}
			br.close();
			fonction = fonction - 1;
		}
	}
}
