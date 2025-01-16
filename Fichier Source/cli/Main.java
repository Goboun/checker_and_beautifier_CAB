package cli;

import java.io.IOException;

import exception.ArgumentInvalideException;
import exception.CommentException;
import exception.FichierInvalideException;
import exception.NombresParametresException;
import exception.PydocException;
import exception.FonctionException;
import exception.Sbutf8Exception;
import exception.StatsException;
import exception.TypeException;

/**
* Représente l'application en mode terminal 
*/
public class Main { 
	
	/**
	* Représente le main de l'application en mode terminal 
	* @param args ne sert à rien
	*/
	public static void main(String[]args) throws IOException{
		try{
			Parametre parametre;
			
			//0 PARAMETRE OU +6 PARAMETRES 
			if(args.length == 0) {
				parametre = new Parametre(0);
				System.out.println("Veuillez taper <<java -jar cli.jar -h>> ou <<java -jar cli.jar --help>> pour afficher le manuel");
			}
			
			//1 PARAMETRE
			if(args.length == 1) {
				parametre = new Parametre(args[0], 1);
				//AIDE
				if(parametre.verification()) {
					Help h = new Help();
					System.out.println(h.help());
				}
			}
			
			//2 PARAMETRES
			if(args.length == 2) {
				parametre = new Parametre(args[0], args[1], 2);
				if(parametre.verification()) {
					//REPERTOIRE
					if(parametre.getType().equals("-d")){
						Repertoire rep = new Repertoire(parametre.getNom());
						System.out.println(rep.afficheRepertoire(rep.getRep()));
					}
					//FICHIER
					if(parametre.getType().equals("-f")) {
						Fichier fic = new Fichier(parametre.getNom());
						System.out.println(fic.afficheInformations());
					}
				}
			}
			
			//3 PARAMETRES
			if(args.length == 3) {
				parametre = new Parametre(args[0], args[1], args[2], 3);
				if(parametre.verification()) {
					//REPERTOIRE
					if(parametre.getType().equals("-d")){
						//Afficher la liste des fichiers avec leurs chemins relatifs
						Repertoire rep = new Repertoire(parametre.getNom());
						System.out.println(rep.afficheRepertoire(rep.getRep()));
						if(parametre.getOption1().equals("-stat")) {
							Statistique s = new Statistique();
							System.out.println(s.afficheStats(rep.getNom()));
						}
					}
					//FICHIER
					if(parametre.getType().equals("-f")) {
						Fichier fic = new Fichier(parametre.getNom());
						System.out.println(fic.afficheInformations());
						//OPTION 1
						if(parametre.getOption1().equals("--type")) {
							Type type = new Type(parametre.getNom());
							type.verifierErreurTypage();
							System.out.println("Toutes les fonctions ont un type indiqué (s'il y a des fonctions).");
						}
						if(parametre.getOption1().equals("--head")) {
							Head h = new Head(parametre.getNom());
							System.out.println(h.verifierDeuxPremieresLignesDeCommentaires());
						}
						if(parametre.getOption1().equals("--pydoc")) {
							Pydoc pydoc = new Pydoc(parametre.getNom());
							pydoc.verifierPyDoc();
							System.out.println("Le traitement pydoc a été effectué.");
						}
						if(parametre.getOption1().equals("--sbutf8")) {
							Sbutf8 sbutf8 = new Sbutf8(parametre.getNom());
							sbutf8.ajouterDeuxPremieresLignesDeCommentaire();
							System.out.println("Le traitement sbutf8 a été effectué.");
						}
						if(parametre.getOption1().equals("--comment")) {
							Comment comment = new Comment(parametre.getNom());
							comment.ajouterPydoc();
							System.out.println("Le traitement comment a été effectué.");
						}
					}
				}
			}
			
			//4 PARAMETRES
			if(args.length == 4) {
				parametre = new Parametre(args[0], args[1], args[2], args[3], 4);
				if(parametre.verification()) {
					//FICHIER
					if(parametre.getType().equals("-f")) {
						Fichier fic = new Fichier(parametre.getNom());
						System.out.println(fic.afficheInformations());
						//OPTION 1
						if(parametre.getOption1().equals("--type")) {
							Type type = new Type(parametre.getNom());
							type.verifierErreurTypage();
							System.out.println("Toutes les fonctions ont un type indiqué (s'il y a des fonctions).");
						}
						if(parametre.getOption1().equals("--head")) {
							Head h = new Head(parametre.getNom());
							System.out.println(h.verifierDeuxPremieresLignesDeCommentaires());
						}
						if(parametre.getOption1().equals("--pydoc")) {
							Pydoc pydoc = new Pydoc(parametre.getNom());
							pydoc.verifierPyDoc();
							System.out.println("Le traitement pydoc a été effectué.");
						}
						if(parametre.getOption1().equals("--sbutf8")) {
							Sbutf8 sbutf8 = new Sbutf8(parametre.getNom());
							sbutf8.ajouterDeuxPremieresLignesDeCommentaire();
							System.out.println("Le traitement sbutf8 a été effectué.");
						}
						if(parametre.getOption1().equals("--comment")) {
							Comment comment = new Comment(parametre.getNom());
							comment.ajouterPydoc();
							System.out.println("Le traitement comment a été effectué.");
						}
						//OPTION 2
						if(parametre.getOption2().equals("--type")) {
							Type type = new Type(parametre.getNom());
							type.verifierErreurTypage();
							System.out.println("Toutes les fonctions ont un type indiqué (s'il y a des fonctions).");
						}
						if(parametre.getOption2().equals("--head")) {
							Head h = new Head(parametre.getNom());
							System.out.println(h.verifierDeuxPremieresLignesDeCommentaires());
						}
						if(parametre.getOption2().equals("--pydoc")) {
							Pydoc pydoc = new Pydoc(parametre.getNom());
							pydoc.verifierPyDoc();
							System.out.println("Le traitement pydoc a été effectué.");
						}
						if(parametre.getOption2().equals("--sbutf8")) {
							Sbutf8 sbutf8 = new Sbutf8(parametre.getNom());
							sbutf8.ajouterDeuxPremieresLignesDeCommentaire();
							System.out.println("Le traitement sbutf8 a été effectué.");
						}
						if(parametre.getOption2().equals("--comment")) {
							Comment comment = new Comment(parametre.getNom());
							comment.ajouterPydoc();
							System.out.println("Le traitement comment a été effectué.");
						}
					}
				}
			}
			
			//5 PARAMETRES
			if(args.length == 5) {
				parametre = new Parametre(args[0], args[1], args[2], args[3], args[4], 5);
				if(parametre.verification()) {
					//FICHIER
					if(parametre.getType().equals("-f")) {
						Fichier fic = new Fichier(parametre.getNom());
						System.out.println(fic.afficheInformations());
						//OPTION 1
						if(parametre.getOption1().equals("--type")) {
							Type type = new Type(parametre.getNom());
							type.verifierErreurTypage();
							System.out.println("Toutes les fonctions ont un type indiqué (s'il y a des fonctions).");
						}
						if(parametre.getOption1().equals("--head")) {
							Head h = new Head(parametre.getNom());
							System.out.println(h.verifierDeuxPremieresLignesDeCommentaires());
						}
						if(parametre.getOption1().equals("--pydoc")) {
							Pydoc pydoc = new Pydoc(parametre.getNom());
							pydoc.verifierPyDoc();
							System.out.println("Le traitement pydoc a été effectué.");
						}
						if(parametre.getOption1().equals("--sbutf8")) {
							Sbutf8 sbutf8 = new Sbutf8(parametre.getNom());
							sbutf8.ajouterDeuxPremieresLignesDeCommentaire();
							System.out.println("Le traitement sbutf8 a été effectué.");
						}
						if(parametre.getOption1().equals("--comment")) {
							Comment comment = new Comment(parametre.getNom());
							comment.ajouterPydoc();
							System.out.println("Le traitement comment a été effectué.");
						}
						//OPTION 2
						if(parametre.getOption2().equals("--type")) {
							Type type = new Type(parametre.getNom());
							type.verifierErreurTypage();
							System.out.println("Toutes les fonctions ont un type indiqué (s'il y a des fonctions).");
						}
						if(parametre.getOption2().equals("--head")) {
							Head h = new Head(parametre.getNom());
							System.out.println(h.verifierDeuxPremieresLignesDeCommentaires());
						}
						if(parametre.getOption2().equals("--pydoc")) {
							Pydoc pydoc = new Pydoc(parametre.getNom());
							pydoc.verifierPyDoc();
							System.out.println("Le traitement pydoc a été effectué.");
						}
						if(parametre.getOption2().equals("--sbutf8")) {
							Sbutf8 sbutf8 = new Sbutf8(parametre.getNom());
							sbutf8.ajouterDeuxPremieresLignesDeCommentaire();
							System.out.println("Le traitement sbutf8 a été effectué.");
						}
						if(parametre.getOption2().equals("--comment")) {
							Comment comment = new Comment(parametre.getNom());
							comment.ajouterPydoc();
							System.out.println("Le traitement comment a été effectué.");
						}
						//OPTION 3
						if(parametre.getOption3().equals("--type")) {
							Type type = new Type(parametre.getNom());
							type.verifierErreurTypage();
							System.out.println("Toutes les fonctions ont un type indiqué (s'il y a des fonctions).");
						}
						if(parametre.getOption3().equals("--head")) {
							Head h = new Head(parametre.getNom());
							System.out.println(h.verifierDeuxPremieresLignesDeCommentaires());
						}
						if(parametre.getOption3().equals("--pydoc")) {
							Pydoc pydoc = new Pydoc(parametre.getNom());
							pydoc.verifierPyDoc();
							System.out.println("Le traitement pydoc a été effectué.");
						}
						if(parametre.getOption3().equals("--sbutf8")) {
							Sbutf8 sbutf8 = new Sbutf8(parametre.getNom());
							sbutf8.ajouterDeuxPremieresLignesDeCommentaire();
							System.out.println("Le traitement sbutf8 a été effectué.");
						}
						if(parametre.getOption3().equals("--comment")) {
							Comment comment = new Comment(parametre.getNom());
							comment.ajouterPydoc();
							System.out.println("Le traitement comment a été effectué.");
						}
					}
				}
			}
			
			//+5 PARAMETRES
			if(args.length > 5) {
				parametre = new Parametre(6);
				parametre.verification();
			}
			
		}catch (NombresParametresException e) {
			System.err.println(e.getMessage());
		}catch (FichierInvalideException e) {
			System.err.println(e.getMessage());
		}catch (TypeException e) {
			System.err.println(e.getMessage());
		}catch (Sbutf8Exception e) {
			System.err.println(e.getMessage());
		}catch (CommentException e) {
			System.err.println(e.getMessage());
		}catch (PydocException e) {
			System.err.println(e.getMessage());
		}catch (FonctionException e) {
			System.err.println(e.getMessage());
		}catch (StatsException e) {
			System.err.println(e.getMessage());
		}catch (ArgumentInvalideException e) {
			System.err.println(e.getMessage());
		}
	}
}
