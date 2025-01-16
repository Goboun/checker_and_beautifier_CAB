package gui;

import cli.Comment;
import cli.Fichier;
import cli.Repertoire;
import cli.Sbutf8;
import cli.Type;

import exception.CommentException;
import exception.Sbutf8Exception;
import exception.TypeException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
* Classe Application qui représente l'interface graphique
*/
public class Application {

	/**
	* Attribut de classe JFrame qui représente la fenêtre de l'application
	*/
	private JFrame frmPythonCheckerIn;
	
	/**
	* Attribut de classe Fichier pour manipuler le fichier sélectionné
	*/
	private Fichier fichier;
	
	/**
	* Attribut de classe String pour afficher les informations dans le grand champs
	*/
	private String texte;
	
	/**
	* Attribut de classe String pour enregistrer 
	* le nom de l'auteur qui sera ajouté dans le pydoc quand on clique sur COMMENT
	*/
	private String nom = "";
	
	/**
	* Attribut de classe String pour enregistrer 
	* la version qui sera ajouté dans le pydoc quand on clique sur COMMENT
	*/
	private String version = "";
	
	/**
	 * Le main de l'interface graphique
	 * @param args ne sert à rien
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			/**
			 * Lance une nouvelle application
			 */
			public void run() {
				try {
					Application window = new Application();
					window.frmPythonCheckerIn.setVisible(true);
				} catch (Exception e) {}
			}
		});
	}

	/**
	 * Initialise l'application.
	 */
	public Application() {
		initialize();
	}
	
	/**
	 * Initialise les différents éléments composants l'application.
	 */
	private void initialize() {
		
		//FENETRE
		frmPythonCheckerIn = new JFrame();
		frmPythonCheckerIn.getContentPane().setBackground(new Color(0, 0, 102));
		frmPythonCheckerIn.setTitle("Checker & Beautifier in Java for python language");
		frmPythonCheckerIn.setBounds(100, 100, 520, 455);
		frmPythonCheckerIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPythonCheckerIn.getContentPane().setLayout(null);
		
		//CHAMPS DE TEXTE: INFORMATIONS
		JTextArea champs = new JTextArea();
		champs.setEditable(false);
		champs.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
		champs.setBounds(269, 28, 221, 175);
		frmPythonCheckerIn.getContentPane().add(champs);
		
		//LISTE: FICHIERS .PY
		JList<String> liste = new JList<String>();
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liste.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		liste.setFont(new Font("Helvetica Neue", Font.BOLD, 10));
		liste.setBounds(27, 150, 221, 250);
		liste.addMouseListener(new MouseAdapter() {
			/**
			* Affiche les informations du fichier sélectionné dans le grand champs
			*/
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String selectedItem = (String) liste.getSelectedValue();
					setFichier(selectedItem);
					champs.setText(getFichier().afficheInformations());
			    }
			}
		});
		frmPythonCheckerIn.getContentPane().add(liste);

		
		//TOUCHE: LISTER FICHIERS
		JButton toucheListerFichier = new JButton("Lister Fichiers");
		toucheListerFichier.setBackground(Color.WHITE);
		toucheListerFichier.setForeground(Color.BLACK);
		toucheListerFichier.setFont(new Font("Helvetica Neue", Font.BOLD | Font.ITALIC, 25));
		toucheListerFichier.setBounds(25, 25, 225, 125);
		toucheListerFichier.addActionListener(new ActionListener() {
			/**
			* Quand on clique sur la touche Lister Fichiers, cela affiche la liste des fichiers .py présent dans l'arborescence où se trouve le gui.jar
			*/
			public void actionPerformed(ActionEvent e) {
				try {
					//ON CREE UN REPERTOIRE MAIS ON VEUT JUSTE UTILISER LA METHODE GETEXTENSION QUI EST DANS REPERTOIRE
					Repertoire rep = new Repertoire(".");
					List<File> files = new ArrayList<File>(); 
					listf(".", files);
					//ON CREE UN TABLEAU AVEC LES NOMS DES FICHIERS .PY
					//GRANDE TAILLE MAIS PROBLEME: ON NE REMPLIT PAS TOUT, IL RESTE DES CASES VIDES
					String[] tmp = new String[100]; 
					int i = 0;
					for(Iterator<File> it = files.iterator(); it.hasNext(); ) {
						File file = it.next();
						if(rep.getExtension(file.getPath()).equals("py")) {
							tmp[i] = file.getPath();
							i++;
						}
					}
					//ON REFAIT UN AUTRE TABLEAU POUR ENLEVER LES CASES VIDES
					String[] fichiers = new String[i];
					for(int j = 0; j<i; j++) {
						fichiers[j] = tmp[j];
					}
					liste.setListData(fichiers);
					setTexte("Les fichiers sont tous listés.");
					champs.setText(texte);
					
				} catch (Exception ee) {}
			}
		});
		frmPythonCheckerIn.getContentPane().add(toucheListerFichier);
		
		//TOUCHE: SBUTF8
		JButton toucheSbutf8 = new JButton("SBUTF8");
		toucheSbutf8.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		toucheSbutf8.setBounds(269, 213, 100, 60);
		toucheSbutf8.addActionListener(new ActionListener() {
			/**
			* Quand on clique la touche SBUTF8, on ajoute les deux premières lignes de commentaires.
			* @exception S'il y a déjà les deux lignes, on lève une exception personnalisé Sbutf8Exception.
			* @exception Si aucun fichier est selectionné et qu'on clique, on lève exception générale.
			*/
			public void actionPerformed(ActionEvent e) {
				try {
					Sbutf8 sbutf8 = new Sbutf8(getFichier().getEmplacement());
					sbutf8.ajouterDeuxPremieresLignesDeCommentaire();
					setTexte("Les deux premières lignes de commentaires ont \nété ajoutés.");
					champs.setText(texte);
				} catch (Sbutf8Exception ee) {
					setTexte(ee.getMessage());
					champs.setText(texte);
				} catch (IOException ee) {
					setTexte(ee.getMessage());
					champs.setText(texte);
				} catch (Exception ee) {
					setTexte("Exception: \nAucun fichier est sélectionné.");
					champs.setText(texte);
				}
				
			}
		});
		frmPythonCheckerIn.getContentPane().add(toucheSbutf8);
		
		//BOUTON COMMENT
		JButton toucheComment = new JButton("COMMENT");
		toucheComment.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		toucheComment.setBounds(390, 213, 100, 60);
		toucheComment.addActionListener(new ActionListener() {
			/**
			* Quand on clique la touche COMMENT, on ajoute le squelette de commentaire pydoc.
			* @exception Si chaque fonction a déjà le pydoc, on lève une exception personnalisé CommentException.
			* @exception Si aucun fichier est selectionné et qu'on clique, on lève exception générale.
			*/
			public void actionPerformed(ActionEvent e) {
				try {
					Comment comment = new Comment(getFichier().getEmplacement(), getNom(), getVersion());
					comment.ajouterPydoc();
					setTexte("Le squelette de commentaires pydoc a été ajouté\npour chaque fonction (s'il y en a) sans pydoc.");
					champs.setText(texte);
				} catch (CommentException ee) {
					setTexte(ee.getMessage());
					champs.setText(texte);
				} catch (IOException ee) {
					setTexte(ee.getMessage());
					champs.setText(texte);
				} catch (Exception ee) {
					setTexte("Exception: \nAucun fichier est sélectionné.");
					champs.setText(texte);
				}
			}
		});
		frmPythonCheckerIn.getContentPane().add(toucheComment);
		
		//BOUTON TYPAGE
		JButton toucheTypage = new JButton("TYPAGE");
		toucheTypage.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		toucheTypage.setBounds(269, 280, 100, 60);
		toucheTypage.addActionListener(new ActionListener() {
			/**
			* Quand on clique la touche TYPAGE, on vérifie si chaque fonction a un type indiqué.
			* @exception Si une fonction n'a pas de type indiqué, on lève une exception personnalisé TypeException.
			* @exception Si aucun fichier est selectionné et qu'on clique, on lève exception générale.
			*/
			public void actionPerformed(ActionEvent e) {
				try {
					Type type = new Type(getFichier().getEmplacement());
					type.verifierErreurTypage();
					setTexte("Toutes les fonctions ont un type indiqué s'il y a\ndes fonctions.");
					champs.setText(texte);
				} catch (TypeException ee) {
					setTexte(ee.getMessage());
					champs.setText(texte);
				} catch (IOException ee) {
					setTexte(ee.getMessage());
					champs.setText(texte);
				} catch (Exception ee) {
					setTexte("Exception: \nAucun fichier est sélectionné.");
					champs.setText(texte);
				}
			}
		});
		frmPythonCheckerIn.getContentPane().add(toucheTypage);
		
		//TOUCHE: EDITER
		JButton toucheEditer = new JButton("EDITER");
		toucheEditer.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		toucheEditer.setBounds(390, 280, 100, 60);
		toucheEditer.addActionListener(new ActionListener() {
			//ON OUVRE L'EDITEUR DE TEXTE PAR DEFAUT SUR LE FICHIER
			/**
			* Quand on clique la touche EDITER, on ouvre le fichier selectionné avec TextEdit.
			*/
			public void actionPerformed(ActionEvent e) {
				try{
					Desktop.getDesktop().edit(getFichier().getFile());
				}catch(Exception ee) {
					champs.setText("Exception: \nImpossible d'ouvrir le fichier.");
				}
			}
		});
		frmPythonCheckerIn.getContentPane().add(toucheEditer);
		
		//CHAMPS: NOM
		JTextField champsNom = new JTextField();
		champsNom.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		champsNom.setBounds(363, 346, 100, 26);
		champsNom.setColumns(10);
		frmPythonCheckerIn.getContentPane().add(champsNom);
		
		//TOUCHE: NOM
		JButton toucheNom = new JButton("Nom");
		toucheNom.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		toucheNom.setBounds(289, 344, 74, 31);
		toucheNom.addActionListener(new ActionListener() {
			/**
			* Quand on clique la touche Nom, on récupère le texte qu'il y a dans le champs devant la touche Nom.
			* S'il n'y a rien dans le champs, le texte récupéré sera un vide
			*/
			public void actionPerformed(ActionEvent e) {
				setNom(champsNom.getText());
				setTexte("Vous avez enregistré le nom " + getNom() 
				+ "\nqui sera indiqué prochainement dans le pydoc si\nvous cliquez sur COMMENT.");
			champs.setText(texte);
			}
		});
		frmPythonCheckerIn.getContentPane().add(toucheNom);
				
		//CHAMPS: VERSION
		JTextField champsVersion = new JTextField();
		champsVersion.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		champsVersion.setBounds(363, 373, 100, 26);
		champsVersion.setColumns(10);
		frmPythonCheckerIn.getContentPane().add(champsVersion);
		
		//TOUCHE: VERSION
		JButton toucheVersion = new JButton("Version");
		toucheVersion.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		toucheVersion.setBounds(289, 371, 74, 31);
		toucheVersion.addActionListener(new ActionListener() {
			//ON RECUPERE LE TEXTE DANS LE CHAMPS VERSION
			/**
			* Quand on clique la touche Version, on récupère le texte qu'il y a dans le champs devant la touche Version.
			* S'il n'y a rien dans le champs, le texte récupéré sera un vide.
			*/
			public void actionPerformed(ActionEvent e) {
				setVersion(champsVersion.getText());
				setTexte("Vous avez enregistré la version " + getVersion() 
					+ "\nqui sera indiqué prochainement dans le pydoc si\nvous cliquez sur COMMENT.");
				champs.setText(texte);
			}
		});
		frmPythonCheckerIn.getContentPane().add(toucheVersion);
	}
	
	/**
	* Renvoie une liste contenant tous les fichiers d'une arborescence.
	* @param directoryName le nom du repertoire courant, typiquement "."
	* @param files une liste de File instancié avant l'appel de la méthode qui va récupéré chaque fichier d'une arborescence
	*/
	public void listf(String directoryName, List<File> files) {
	    File directory = new File(directoryName);
	    File[] fList = directory.listFiles();
	    if(fList != null){
	        for (File file : fList) {      
	            if (file.isFile()) {
	                files.add(file);
	            } else if (file.isDirectory()) {
	                listf(file.getPath(), files);
	            }
	        }
	    }
	}
	
	/**
	* Définir l'attribut Fichier
	* @param fichier le nom du fichier sélectionné
	*/
	public void setFichier(String fichier) {
		this.fichier = new Fichier(fichier);
	}
	
	/**
	* Définir le texte affiché dans le grand champs
	* @param champs le texte qui sera écrit dans le grand champs
	*/
	public void setTexte(String champs) {
		this.texte = champs;
	}
	
	/**
	* Définir le nom de l'auteur qui sera ajouté dans le pydoc quand on clique sur COMMENT
	* @param nom le nom de l'auteur d'une fonction
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	* Définir la version qui sera ajouté dans le pydoc quand on clique sur COMMENT
	* @param version la version d'une fonction
	*/
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	* Obtenir l'attribut fichier, notamment pour afficher les informations du fichier sélectionné
	* @return fichier retourne le fichier pour être utilisé
	*/
	public Fichier getFichier() {
		return this.fichier;
	}
	
	/**
	* Obtenir l'attribut texte, notamment pour afficher les informations dans le grand champs
	* @return texte retourne le texte pour être affiché dans le grand champs
	*/
	public String getChamps() {
		return this.texte;
	}
	
	/**
	* Obtenir l'attribut nom, notamment pour afficher que l'action d'enregistrer un nom est effectué
	* @return nom retourne le nom de l'auteur pour être ajouté au pydoc
	*/
	public String getNom() {
		return this.nom;
	}
	
	/**
	* Obtenir l'attribut version, notamment pour afficher que l'action d'enregistrer une version est effectué
	* @return version retourne le nom de l'auteur pour être ajouté au pydoc
	*/
	public String getVersion() {
		return this.version;}
}
