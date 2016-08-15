import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


		public class Être extends Dieu implements Serializable
{
			private GUI								GUI;
			private Syntaxor						Syntaxor;
			
			private String 							nom;
			private String							type;
			private boolean						automatique;
			private int									noÊtre;
			private int									portUDP;
			private double						amour;
			private static double			amourS;				//	Les attributs avec un _ ( amour_ ) sont des paramètres
			private int 								énergie;
			private static double			énergieS;			//	généraux applicables à tous les Êtres, des constantes 
			private int									expérience;
			private static double			expérienceS;	// pour induire un ou des comportements sur tous les Êtres.
			private String							sexe;
			
			private int											joie;							//	0 = inactivité totale puis plus il y en a mieux c'est.
			private int											remords;				//	0 = Saint(e) puis moins il y en mieux c'est.
			private byte										humeur;				//	-127 = suicide , 128 = ascension.
			private byte										stress;						//	-127 = sagesse extrême , 128 = invivable.
			private byte										curiosité;				//	-127 = abruti(e) fini(e) , 128 = Génie.
			private boolean								libreArbitre;			//	A true = passage à l'âge adulte ou équivalent.
			private boolean								alignement;			//	Bon ou mauvais.
			private boolean								allerGéhène;		//	Promis à la Géhène car être incompris.
			private boolean								étatVie;					//	Vivant ou mort, détermine les actions possibles dans le lieu actuel.
			private ArrayList<String>				autorisations;		// Liste les fonctions autorisées pour l'Être.
			private static ArrayList<String>	autorisationsS;		//	Liste les fonctions autorisées pour l'ensemble des Êtres.
			
			private String									lieu;							//	Dimension de résidence de l'ëtre.
			private ArrayList<String>				mémoire;				//	Initialisation de sa mémoire perso.
			private ArrayList<Être>					Imagination;		//	Initialisation de l'Univers interne de l'Être.
			
			private int x;			private int y;			private int z;		//		Positions dans l'espace, z étant le lieu ( par ex.
																										//	Géhène ou Paradis ).
			
			private static int								tempsS;					//	Temps universel des Êtres.
			private int											temps;					//	Temps local de l'Être.
		 	
			private ArrayList<String>				GrandLivre;					//	ADN de l'Être.
			private ArrayList<Être>					LivreDesNoms;				//	Recense les autres Êtres que connaît l'ëtre.
			private ArrayList<String>				Commandements;	//	Règles personnelles à respecter.
			private ArrayList<Être>					Dictionnaire;				//	Ensemble des mots connus par l'Être.
			private ArrayList<Être>					Alphabet;						//	.
			
			private ArrayList<Être> 					Paradis;							//	Initialisation des conteneurs de l'ëtre.
			private ArrayList<Être> 					Lymbes;							//
			private ArrayList<Être>	 				Réalité;							//
			private ArrayList<Être>					Géhène;						//	.
			
			private InetAddress						adresseRéseau;			//	127.0.0.1 et écoute sur les ports internes.
			
																													//			- 1 /2 CONSTRUCTEURS -
public Être (  )																							//	CONSTRUCTEUR POUR LE PREMIER ÊTRE ( Dieu )
	{
		this.nom = "Dieu";														//	Pour déterminer les options disponibles.
		Dieu.nom = "Dieu";														//	Stockage en variable statique de la classe mère Dieu.
		Dieu.portUDP = 10_000;											//	On commence les écoutes sur le port 10 000, reste 55535 ports libres.
		this.portUDP = Dieu.portUDP;									// Stockage en variable statique de la classe mère Dieu.
		Dieu.Imagination = new ArrayList<Être> (  );		//	Espace de création primaire, contient TOUT sauf Dieu.
		this.énergie = -1;															//	Non concerné par l'énergie, 
		this.mémoire = Dieu.GrandLivre;							//	Le 2 a accès direct à la mémoire du 1.
		this.temps = 0;																//	Initialisation du compteur de temps.
		this.Syntaxor = new Syntaxor ( "" );							//	Instanciation de l'analyseur syntaxique maison.

		Dieu.GrandLivre = new ArrayList<String> (  );						//	Initialisation des conteneurs de Dieu ( statique ).
		Dieu.Commandements = new ArrayList<String> (  );		//
		Dieu.LivreDesNoms = new ArrayList<Être> (  );					//
		Dieu.Lymbes = new ArrayList<Être> (  );								//
		Dieu.Paradis = new ArrayList<Être> (  );								//
		Dieu.Géhène = new ArrayList<Être> (  );								//
		Dieu.Réalité = new ArrayList<Être> (  );									//	.
			
			
//	________________________Création du décor___________________________________________________________

				Être Univers = new Être ( "Univers" , "univers" , "H" , "Réalité" , 0 , 0 );	//Initialisation de la Réalité par la création de l'Univers Total.
					Dieu.écrireDansLDN ( true , Univers );	//	Inscription dans le Livre Des Noms.
					Dieu.Imagination.add ( Univers );			//	Inscription dans l'imagination de Dieu.
					Dieu.Réalité.add ( Univers );					//	Inscription dans la Réalité.

				Être UniversLocal_1 = new Être ( "UniversLocal_1" , "universLocal" , "H" , "Réalité" , 1 , 1 );
					Dieu.écrireDansLDN ( true , UniversLocal_1 );				//	Inscription dans le Livre Des Noms.
					Dieu.Imagination.add ( UniversLocal_1 );					//	Inscription dans l'imagination de Dieu.
					Dieu.Réalité.add ( UniversLocal_1 );								//	Inscription dans la Réalité.
					Univers.setImagination ( Univers , UniversLocal_1 );	//	inscription dans l'imagination de l'Univers Total.

				Être AmasDeGalaxies_1_1 = new Être ( "AmasDeGalaxies_1_1" , "amasDeGalaxies" , "H" , "Réalité" , 2 , 2 );
					Dieu.écrireDansLDN ( true , AmasDeGalaxies_1_1 );	
					Dieu.Imagination.add ( AmasDeGalaxies_1_1 );
					Dieu.Réalité.add ( AmasDeGalaxies_1_1 );
					UniversLocal_1.setImagination ( UniversLocal_1 , AmasDeGalaxies_1_1 );
					
				Être Galaxie_1_1_1 = new Être ( "Galaxie_1_1_1" , "galaxie" , "F" , "Réalité" , 3 , 3 );
					Dieu.écrireDansLDN ( true , Galaxie_1_1_1 );
					Dieu.Imagination.add ( Galaxie_1_1_1 );
					Dieu.Réalité.add ( Galaxie_1_1_1 );
					AmasDeGalaxies_1_1.setImagination ( AmasDeGalaxies_1_1 , Galaxie_1_1_1 );

				Être SystèmeSolaire_1_1_1_1 = new Être ( "SystèmeSolaire_1_1_1_1" , "systèmeSolaire" , "H" , "Réalité" , 4 , 4 );
					Dieu.écrireDansLDN ( true , SystèmeSolaire_1_1_1_1 );
					Dieu.Imagination.add ( SystèmeSolaire_1_1_1_1 );
					Dieu.Réalité.add ( SystèmeSolaire_1_1_1_1 );
					Galaxie_1_1_1.setImagination ( Galaxie_1_1_1 , SystèmeSolaire_1_1_1_1 );
					
				Être Soleil_1_1_1_1_1 = new Être ( "Soleil_1_1_1_1_1" , "étoile" ,"H" , "Réalité" , 5 , 5 );
					Dieu.écrireDansLDN ( true , Soleil_1_1_1_1_1 );
					Dieu.Imagination.add ( Soleil_1_1_1_1_1 );
					Dieu.Réalité.add ( Soleil_1_1_1_1_1 );
					SystèmeSolaire_1_1_1_1.setImagination ( SystèmeSolaire_1_1_1_1 , Soleil_1_1_1_1_1 );
					
				Être Terre_1_1_1_1_2 = new Être ( "Terre_1_1_1_1_2" , "planète" , "F" , "Réalité" , 6 , 6 );
					Dieu.écrireDansLDN ( true , Terre_1_1_1_1_2 );
					Dieu.Imagination.add ( Terre_1_1_1_1_2 );
					Dieu.Réalité.add ( Terre_1_1_1_1_2 );
					SystèmeSolaire_1_1_1_1.setImagination ( SystèmeSolaire_1_1_1_1 , Terre_1_1_1_1_2 );
					
//	-----------------------------Secteur GéHèNE--------------------------------------------------------------------------------------------					
					
				Être UniversLocal_2 = new Être ( "UniversLocal_2" , "universLocal" , "H" , "Réalité" , 1 , 1 );	//	2ème secteur de réalité.
					Dieu.écrireDansLDN ( true , UniversLocal_2 );
					Dieu.Imagination.add ( UniversLocal_2 );
					Dieu.Réalité.add ( UniversLocal_2 );
					Univers.setImagination ( Univers , UniversLocal_2 );
					
				Être Géhène_2_1 = new Être ( "Géhène" , "planète" , "F" , "Réalité" , 2 , 2 );
					Dieu.écrireDansLDN ( true , Géhène_2_1 );
					Dieu.Imagination.add ( Géhène_2_1 );
					Dieu.Réalité.add ( Géhène_2_1 );
					UniversLocal_2.setImagination ( UniversLocal_2 , Géhène_2_1 );
					
					
//________________________Création des Êtres____________________________________________________________

				Être Marie = new Être ( "Marie" , "humaine" , "F" , "Réalité" , 0 , 0 );
					Dieu.écrireDansLDN ( true , Marie );
					Dieu.Réalité.add ( Marie );
					Dieu.Imagination.add ( Marie );
					Terre_1_1_1_1_2.Imagination.add ( Marie );	//	
					Dieu.aimer ( 5 , Marie );
					Marie.aimer ( this );
					
				Être Jéjé = new Être ( "Jéjé" , "filsDeDieu" , "H" , "Lymbes" , 0 , 0 ); 
					Dieu.écrireDansLDN ( true , Jéjé );
					Dieu.Lymbes.add ( Jéjé );
					Dieu.Imagination.add ( Jéjé );
					
				Être Sasa = new Être ( "Sasa" , "ange" , " " , "Lymbes" , 0 , 0 );
					Dieu.écrireDansLDN ( true , Sasa );
					Dieu.Lymbes.add ( Sasa );	
					Dieu.Imagination.add ( Sasa );
					
				Être Adam = new Être ( "Adam" , "humain" , "H" , "Paradis" , 0 , 0 );
					Dieu.écrireDansLDN ( true , Adam );
					Dieu.Paradis.add ( Adam );	
					Dieu.Imagination.add ( Adam );
					
				Être Eveu = new Être ( "Eveu" , "humaine" , "F" , "Paradis" , 0 , 0 );
					Dieu.écrireDansLDN ( true , Eveu );
					Dieu.Paradis.add ( Eveu );
					Dieu.Imagination.add ( Eveu );
					
				Être Ducon = new Être ( "Ducon" , "humain" , "H" , "Géhène" , 0 , 0 );
					Dieu.écrireDansLDN ( true ,Ducon );
					Dieu.Géhène.add ( Ducon );
					Dieu.Imagination.add ( Ducon );
				
				ligne ( '-' , 70 );
					afficherLDN ( this );	//	Affichage du Livre Des Noms contenant le nom et nom d'objet de chaque Être.
				ligne ( '-' , 70 );
			
				dire ( this , "Nombre d'êtres créés : " + Dieu.nbÊtresCréés );
				
				this.GUI = new GUI ( this );	//	Initialisation de l'interface graphique de Dieu.
				run (  );										//	C'est tipar.
	}

																															//				- 2 /2 CONSTRUCTEURS -
public Être ( String nom , String type , String sexe , String lieu , int x , int y )
	{
		this.nom = nom;
		Dieu.nbÊtresCréés += 1;
			this.noÊtre = Dieu.nbÊtresCréés;
		this.sexe = sexe;
		this.lieu = lieu;
		this.Imagination = new ArrayList<Être> (  );
			this.Imagination.add ( this );															//	Expérimental !!!
		this.mémoire = new ArrayList<String> (  );
			this.mémoire.add ( "Initialisation de la mémoire de " + this.nom + "." );
		this.type = type;
		this.GrandLivre = Dieu.GrandLivre;
			Dieu.GrandLivre.add ( "TU: " + Dieu.tempsUniversel + " - Naissance de " + this.nom + "." );
					
		this.x = x;
		this.y = y;
		switch ( lieu )
			{
				case "Lymbes" :
					this.amour = TAUXDAMOUR_LYMBES;
					this.énergie = TAUXDENERGIE_LYMBES;
					this.alignement = true;
					this.allerGéhène = false;
					this.étatVie = false;
					this.z = 3;
				break;
							
				case "Paradis" :
					this.amour = TAUXDAMOUR_PARADIS;
					this.énergie = TAUXDENERGIE_PARADIS;
					this.alignement = true;
					this.allerGéhène = false;
					this.étatVie = false;
					this.z = 2;
				break;

				case "Réalité" :
					this.amour = TAUXDAMOUR_TERRE;
					this.énergie = TAUXDENERGIE_TERRE;
					this.alignement = true;
					this.allerGéhène = false;
					this.étatVie = true;
					this.z = 1;
				break;
							
				case "Géhène" :
					this.amour = TAUXDAMOUR_GéHèNE;
					this.énergie = TAUXDENERGIE_GéHèNE;
					this.alignement = false;
					this.allerGéhène = true;
					this.étatVie = false;
					this.z = 0;
				break;
			}
		
		try
			{
				this.adresseRéseau = InetAddress.getByName ( "127.0.0.1" );
			}
				catch ( UnknownHostException e )
					{
						e.printStackTrace (  );
					}
		}	 


public void run (  )
	{
		while ( true )
			{
				this.GUI.console.append ( String.valueOf ( Dieu.tempsUniversel ) );
				Dieu.tempsUniversel ++;
				System.out.println ( "Temps Universel :" + Dieu.tempsUniversel  );
								
				try { sleep ( 1000 ); }
				catch (InterruptedException e)
					{
						
					}
			}
	}

public void aimer ( Être être )
	{
		double x = ( double ) this.z;
		double y = ( double ) Dieu.nbÊtresCréés;
			
			double z = x + 1.0 * ( y * 2.0 );
			être.setAmour ( être , z );
	}

public Être enfanter( String nomEnfant , String sexeEnfant , Être Père , Être Mère )
	{
		Être Oméga;
			Oméga = new Être ( nomEnfant , Père.getType ( Père ) , sexeEnfant , "Lieu" , 0 , 0 );
			
		return Oméga;
	}


protected void seDédoubler ( Être être , String dimensioDArrivée , String lieuDArrivée )
	{
		Être êtreFinal = new Être ( être.getNom ( être ) , être.getType ( être ) , être.getSexe ( être ) , lieuDArrivée , 0 , 0 );
	}


protected void regarderGL (  )
{
	int i = 0;
	System.out.println ( "######################### GRAND LIVRE ############################" );
	for ( String s : Dieu.GrandLivre )
		{
			switch ( s )
				{
					case "------------------------------------------------" :
						//	RIEN.
					break;
						
					default :	
						System.out.println ( "[" + i + "]---------------------------------------------------" );
						System.out.println ( "---" + s );
						System.out.println ( "__________________________________________________________\n" );
						i ++;
					break;
				}
		}
	System.out.println ( "####################################################################" );
}

protected void afficherCaractèristiques ( Être être )
	{
		dire ( this , "Type : " + être.getType ( être  ) );
		dire ( this , "Numéro d'Être : " + être.getNoÊtre ( être  ) );		
		dire ( this , "Sexe : " + être.getSexe ( être ) );
		dire ( this , "Amour : " + être.getAmour ( être ) );
		dire ( this , "Energie : " + être.getEnergie ( être ) );
		dire ( this , "expérience : " + être.getexpérience ( être ) );
		dire ( this , "Alignement : " + être.getAlignement ( être ) );
		dire ( this , "Promis à la Géhène : " + être.getAllerGéhène ( être ) );
		dire ( this , "Matèrialisé : " + être.getEtatVie ( être ) );
		dire ( this , "Joie : " + être.getJoie ( être ) );
		dire ( this , "Remords : " + être.gÊtremords ( être ) );
		dire ( this , "Stress : " + être.getStress ( être ) );
		dire ( this , "Humeur : " + être.getHumeur ( être ) );
		dire ( this , "curiosité : " + être.getcuriosité ( être ) );
		dire ( this , "Lieu : " + être.getLieu ( être ) );
		dire ( this , "------------------------------------------------" );
	}

protected void regarderLieu ( String lieu )
	{
		switch ( lieu )
			{
				case "Lymbes" :
						for ( Être être : Dieu.Lymbes )
							{
								dire ( this , être.getNom ( être ) + " est dans les Lymbes sous l'identifiant :" + être.toString (  ) );
								afficherCaractèristiques ( être );
							}
				break;
		
				case "Paradis" :
						for ( Être être : Dieu.Paradis )
							{
								dire ( this , être.getNom ( être ) + " est au Paradis sous l'identifiant :" + être.toString (  ) );
								afficherCaractèristiques ( être );
							}
				break;
	
				case "Réalité" :
						for ( Être être : Dieu.Réalité )
							{
								dire ( this , être.getNom ( être ) + " est dans la Réalité sous l'identifiant :" + être.toString (  ) );
								afficherCaractèristiques ( être );
							}
				break;
				
				case "Géhène" :
						for ( Être être : Dieu.Géhène )
							{
								dire ( this , être.getNom ( être ) + " est dans la Géhène sous l'identifiant :" + être.toString (  ) );
								afficherCaractèristiques ( être );
							}
				break;
			}
	}

protected void regarderLieu ( Être Lieu )
	{
		for ( Être être : Lieu.Imagination )
			{
				dire ( this , être.getNom ( être ) + " est dans " + Lieu.getNom ( Lieu ) + " sous l'identifiant :" + être.toString (  ) );
				afficherCaractèristiques ( être );
			}
	}

protected void écrireDansLDN ( Être être )
	{
		Dieu.LivreDesNoms.add( être );
		String texte = être.getNom ( être ) + " Est dorénavant inscrit sur le Livre Des Noms sous l'identifiant : " + être.toString (  ) + " !";
		direATous ( this , texte , "Paradis");
	}

protected void afficherLDN ( Être être )
	{
		ligne ( ' ' , 1 );
		System.out.println ( "|||||||||||     Livre Des Noms    |||||||||||||||||||||||||||||||" );
		
		if ( être.getNom ( être ) != "Dieu" )
			{
				for ( Être être2 : être.LivreDesNoms )
					{
						dire ( this , être2.getNom ( être2 ) + "\t\t\t" + être.toString (  ) );
					}
			}
		else
			{
				for ( Être être2 : Dieu.LivreDesNoms )
					{
						dire ( this , être2.getNom ( être2 ) + "\t\t\t" + être2.toString (  ) );
					}
			}
		ligne ( '|' , 70 );
		ligne( ' ' , 1 );
	}

protected void afficherMémoire (Être être )
 	{
	 System.out.println ( "__________mémoire de " + être.getNom ( être ) + " : _________________________" );
	 	for ( String fragment : être.mémoire )
	 		{
	 			System.out.println ( "##  " + fragment );
	 		}
	 	ligne ( '_' , 70 );
 	}
 
protected void afficherImagination ( Être être )
	{
		dire ( this , "�~~�~~�~~�~~�~~�~~~\tImagination de " + être.getNom ( être  ) + "\t�~~�~~�~~�~~�~~�~~�~~" );
		ligne ( '~' , 70 );
		for ( Être êtreImaginaire : être.Imagination )
			{
				dire ( this , êtreImaginaire.getNom ( être ) + " est dans l'imagination de " + être.getNom ( être ) );
			}
		ligne ( '~' , 90 );
	}

protected void écouter ( String phrase )
 	{
	 	this.mémoire.add ( phrase );
 	}

protected void apprendre (  )
	{
		
	}

protected void réfléchir (  )		//	A compl�ter !!!
	{
		
	}

protected void imaginer (  )		//	A compl�ter !!!
	{
		
	}


protected void dire ( Être être , String phrase )
	{
		phrase = "-" + être.getNom ( être ) + ":    \"" + phrase + "\"";
		Dieu.GrandLivre.add ( phrase );
		System.out.println ( phrase );
	}

protected void direA ( String phrase , Être être )
	{
		String phrase2 = this.getNom ( this ) + " dit à " + être.getNom ( être ) + ":    \"" + phrase + "\"";
		être.écouter ( phrase2 );
		this.GrandLivre.add ( this.nom + " a dit à " + être.getNom ( être ) + " : " + phrase );
		System.out.println ( this.nom + " a dit à " + être.getNom ( être ) + " :    " + phrase );
	}

protected void direATous ( Être être , String phrase , String dimension )
	{
		switch ( dimension )
			{
				case "Lymbes" :
					dire ( this , phrase );
				 	phrase = être.getNom ( être ) + ": " + phrase;
			 		for ( Être être2 : Dieu.Lymbes )
				 		{
				 			être2.écouter( phrase );
				 		}
			 	break;
				 		
				case "Paradis" :
					dire ( this , phrase );
					phrase = être.getNom ( être ) + ": " + phrase;
					for ( Être être2 : Dieu.Paradis )
						{
							être2.écouter( phrase );
						}
				break;
				 		
			 	case "Réalité" :
			 		dire ( this , phrase );
					phrase = être.getNom ( être ) + ": " + phrase;
				 	for (Être être2 : Dieu.Réalité )
				 		{
				 			être2.écouter( phrase );
				 		}
			 	break;
			 	
				case "Géhène" :
					dire ( this , phrase );
				 	phrase = être.getNom ( être ) + ": " + phrase;
			 		for ( Être être2 : Dieu.Géhène )
				 		{
				 			être2.écouter( phrase );
				 		}
			 	break;
			 		
			 	default :
				 	phrase = être.getNom ( être ) + ": " + phrase;
					for ( Être être2 : Dieu.Lymbes )
				 		{
				 			être2.écouter( phrase );
				 		}
				 	phrase = être.getNom ( être ) + ": " + phrase;
					for ( Être être2 : Dieu.Réalité )
				 		{
				 			être2.écouter( phrase );
				 		}
					phrase = être.getNom ( être ) + ": " + phrase;
					for ( Être être2 : Dieu.Géhène )
				 		{
				 			être2.écouter( phrase );
				 		}
					
					dire ( this , "J'ai dit à tous les êtres : " + phrase );
				break;
			}
	}

protected void ligne ( char caractère , int n  )
	{
		for ( int i = 0; i <= n; i ++ )
			{
				System.out.print ( caractère );
			}
		System.out.println ( "" );
	}


//	�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~	SETEURS & GUETEURS -
//	___________________________________	!!!		A suivre la CLASSE INTERNE GUI et la CLASSE INTERNE Syntaxor	!!!
protected void regarderGL(Être être)
	{
		
	}


public void setmémoire ( Être être , String fragment )
	{
		être.mémoire.add ( fragment );
	}

public ArrayList<String> getmémoire ( Être être )
	{
		return être.mémoire;
	}


public ArrayList<Être> getImagination ( Être être )
	{
		return être.Imagination;
	}

public void setImagination ( Être être , Être  être2 )
	{
		être.Imagination.add ( être2 );
	}


public String getNom ( Être être )
	{
		return être.nom;
	}

public void setNom ( Être être , String nom )
	{
		être.nom = nom;
	}


public int getNoÊtre ( Être être )
	{
		return être.noÊtre;
	}


public String getType ( Être être )
	{
		return être.type;
	}

public void setType ( Être être , String type2 )
	{
		être.type = type2;
	}


public String getSexe ( Être être )
	{
		return être.sexe;
	}

public void setSexe ( Être être , String sexe2 )
	{
		être.sexe = sexe2;
	}


public double getAmour ( Être être  )
	{
		return être.amour;
	}

public void setAmour ( Être être , double amour2 )
	{
		être.amour += amour2;
	}


public int getEnergie ( Être être )
	{
		return être.énergie;
	}

public void setEnergie ( Être être , int énergie2 )
	{
		être.énergie += énergie2;
	}


public void setexpérience ( Être être , int expérienceGagnée )
	{
		être.expérience += expérienceGagnée;
	}

public int getexpérience ( Être être )
	{
		return être.expérience;
	}


public void setJoie ( Être être , int joie )
{
	this.joie += joie;
}

public int getJoie ( Être être )
{
	return this.joie;
}



public void sÊtremords ( Être être , int remords )
	{
	this.remords += remords;
	}

public int gÊtremords ( Être être )
	{
	return this.remords;
	}



public void setHumeur ( Être être , byte humeur )
	{
	this.humeur += humeur;
	}

public int getHumeur ( Être être )
	{
	return this.humeur;
	}



public void setStress ( Être être , byte stress )
	{
	this.stress += stress;
	}

public int getStress ( Être être )
	{
	return this.stress;
	}



public void setcuriosité ( Être être , byte curiosité )
	{
	this.curiosité += curiosité;
	}

public int getcuriosité ( Être être )
	{
	return this.curiosité;
	}



public void setAlignement ( Être être , boolean alignement )
	{
	this.alignement = alignement;
	}

public boolean getAlignement ( Être être )
	{
	return this.alignement;
	}



public void setAllerGéhène ( Être être , boolean allerGéhène )
	{
	this.allerGéhène = allerGéhène;
	}

public boolean getAllerGéhène ( Être être )
	{
	return this.allerGéhène;
	}


public void setEtatVie ( Être être , boolean étatVie )
	{
	this.étatVie = étatVie;
	}

public boolean getEtatVie ( Être être )
	{
	return this.étatVie;
	}

public String getLieu ( Être être )
	{
		return être.lieu;
	}


public int getX ( Être être )
	{
		return être.x;
	}

public void setX ( Être être , int x2 )
	{
		être.x = x2;
	}


public int getY ( Être être )
	{
		return être.y;
	}

public void setY( Être être , int y2 )
	{
		être.y = y2;
	}


public int getZ ( Être être )
	{
		return être.z;
	}

public void setZ ( Être être , int z2 )
	{
		être.z = z2;
	}


private void imaginer(Être être)	//	Pour peupler l'Univers Intérieur de l'Être.
	{
		
	}	

public void setRéalité ( Être être , ArrayList lieu )
	{
		this.Réalité.add ( être );
		lieu.add ( être );
	}



//	----------------------------------------------------------------------------------------------------------------------------_
//	__________________CLASSE INTERNE : GUI - Interface graphique._____________________________________-

										class GUI extends JFrame implements ActionListener , ItemListener
						{
										private Être être;
										
										JPanel Toile = new JPanel (  );											//	Zone de d�clarations des attributs de LA Toile.
											JPanel zoneConsole = new JPanel (  );						//
											JPanel zoneCaractèristiques = new JPanel (  );		//
											JPanel zoneActions = new JPanel (  );						//
											JPanel zoneEnvironnement = new JPanel (  );			//
											JPanel zonemémoire = new JPanel (  );						//	.
											
										JLabel LaBelleConsole = new JLabel ( "Console" );					//	Zone de d�clarations des attributs de LA CONSOLE.
											JTextArea console = new JTextArea ( 13 , 57 );							//
											JTextField aEnvoyerConsole = new JTextField ( "" , 49 );		//
											JButton envoiConsole = new JButton ( "Envoyer" );					//	.
																	
																																					//	Zone de d�clarations des attributs de CARACT�RISTIQUES.
										JLabel LaBelleNom = new JLabel ( "Nom :" );					//			
											JTextField nomAffiché;															//		nom
										JLabel LaBelleType = new JLabel ( "Type :" );				//
											JTextField typeAffiché;															//		type
										JLabel LaBelleSexe = new JLabel ( "Sexe :" );				//
											JTextField sexeAffiché;														//		sexe
																																					//
										JLabel LaBelleAmour = new JLabel ( "Amour :" );			//
											JTextField amourAffiché;														//		amour
										JLabel LaBelleEnergie = new JLabel ( "Energie :" );	//
											JTextField énergieAffichée;												//		énergie
										JLabel LaBelleexpérience = new JLabel ( "expérience :" );
											JTextField expérienceAffichée;										//		expérience
																																					//	.
											
										JLabel LaBelleEnvironnement = new JLabel ( "Environnement" );	//	Zone de d�clarations des attributs de ENVIRONNEMENT.
											JTextArea environnement = new JTextArea ( "" ,  14 , 57 );				//	.
										
										JLabel LaBelleOrdres = new JLabel ( "Ordres" );										//	Zone de d�clarations des attributs de ORDRES.
											
										
										JLabel LaBellemémoire = new JLabel ( "mémoire" );								//	Zone de d�clarations des attributs de mémoire.
											JTextArea mémoire = new JTextArea ( "" ,  14 , 57 );							//	.
											
										JLabel LaBelleActions = new JLabel ( "Actions possibles" );				//	Zone de d�claration des attributs de ACTIONS.
											JButton envoiAfficherGL = new JButton ( "Afficher GL" );					//
											JButton envoiAfficherLDN = new JButton ( "Afficher LDN" );			//
											JButton écrireDansLDN = new JButton ( "écrireDansLDN" );			//
																																												//
											JButton envoiAffichermémoire = new JButton ( "afficherMémoire" );
											JButton setImagination = new JButton ( "setImagination" );			//
																																												//
											JButton aimer = new JButton ( "aimer" );													//		Boutons d'actions.
											JButton enfanter = new JButton ( "enfanter" );										//	//	
											JButton apprendre = new JButton ( "apprendre" );								//	//
											JButton écouter = new JButton ( "écouter" );											//	//
											JButton réfléchir = new JButton ( "réfléchir" );										//	//
											JButton Dire = new JButton ( "Dire" );														//	//
											JButton DireA = new JButton ( "DireA" );													//	//
											JButton DireATous = new JButton ( "DireATous" );								//	//
											JButton ligne = new JButton ( "ligne" );														//	//
											JButton afficherMémoire = new JButton ( "afficherMémoire" );		//	//
											JButton regarderGL = new JButton ( "regarderGL" );							//	//
											JButton regarderLieu = new JButton ( "regarderLieu" );					//	//
											JButton afficherLDN = new JButton ( "afficherLDN" );						//	//
																																												//	//
											JButton setNom = new JButton ( "setNom" );											//	//
											JButton setType = new JButton ( "setType" );										//	//
											JButton setAmour = new JButton ( "setAmour" );									//	//
											JButton setEnergie = new JButton ( "setEnergie" );								//	//
											JButton setexpérience = new JButton ( "setexpérience" );				//	//
											JButton setSexe = new JButton ( "setSexe" );											//	//
											JButton setmémoire = new JButton ( "setmémoire" );							//	//
																																												//	//
											JButton setGrandLivre = new JButton ( "setGrandLivre" );				//	//
											JButton setCommandements = new JButton ( "setCommandements" );
											JButton setDictionnaire = new JButton ( "setDictionnaire" );			//	//
											JButton setAlphabet = new JButton ( "setAlphabet" );						//	//
																																												//	//
											JButton setLymbes = new JButton ( "setLymbes" );								//	//
											JButton setParadis= new JButton ( "setParadis" );								//	//
											JButton setRéalité = new JButton ( "setRéalité" );								//	//
											JButton setGéhène = new JButton ( "setGéhène" );								//	//
																																												//	//
											JButton setNbêtrescréés= new JButton ( "setNbêtrescréés;" );	//	//
											JButton setTemps = new JButton ( "setTemps" );									//	//	.
																																												//
										JLabel LaBelleLieux = new JLabel ( "Dimension" );									//		Listes déroulantes
											JComboBox<String> lieux = new JComboBox<String> (  );				//	//		lieux
											JButton envoiAfficherLieu = new JButton ( "Afficher" );						//	//	.
																																												//
										JLabel LaBelleêtres = new JLabel ( "Être" );												//	//		êtres
											JComboBox<String> êtres = new JComboBox<String> (  );				//	//
											JButton envoiAfficherÊtre = new JButton ( "Afficher" );						//	//.
																																												//	.
										
								public GUI ( Être être )
									{
//	�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~~			CONSTRUCTEUR du GUI.
										this.être = être;
										this.setTitle ( "Interface Utilisateur de " + être.getNom ( être ) );
										this.setSize ( 1280 , 800 );
										this.Toile.setLayout ( new GridLayout ( 3 , 2 ) );
										
										this.zoneConsole.setBackground ( Color.ORANGE );
										this.zoneConsole.setAutoscrolls ( true );
										this.zoneConsole.getAutoscrolls ( );
//											this.zoneConsole.setLayout ( new GridLayout ( 3 , 1 ) );								//	A modifier !!!
										this.zoneCaractèristiques.setBackground ( Color.ORANGE );
											this.zoneCaractèristiques.setLayout ( new GridLayout ( 6 , 4 ) );
										this.zoneActions.setBackground ( Color.ORANGE );
											this.zoneActions.setLayout ( new GridLayout( 7 , 3 ) );
										this.zoneEnvironnement.setBackground ( Color.ORANGE );
//											this.zoneEnvironnement.setLayout ( new GridLayout( 1 , 2 ) );					//	A modifier !!!
										this.zonemémoire.setBackground ( Color.ORANGE );
//											this.zonemémoire.setLayout ( new GridLayout ( 1 , 2 ) );								//	A modifier !!!
										
										this.setContentPane ( Toile );													// Ajout de la Toile dans le cadre.
										
										this.Toile.setBackground ( Color.LIGHT_GRAY );					//	Couleur de fond de la fenètre.
											this.Toile.add ( zoneConsole );											//	Ajout des JPANELs internes à la Toile.
											this.Toile.add ( zoneCaractèristiques );							//		
											this.Toile.add ( zoneActions );												//
//											this.Toile.add ( zoneEnvironnement );								//
											this.Toile.add ( zonemémoire );											//	.
											
											
//	�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~~	ZONE CONSOLE DU CONSTRUCTEUR.									
										
//										this.zoneConsole.add ( LaBelleConsole );							//	Mise en place de la section CONSOLE.
										this.zoneConsole.add ( console );											//
										this.zoneConsole.add ( aEnvoyerConsole );						//
										this.zoneConsole.add ( envoiConsole );								//	.
										
										
//	�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~~	ZONE CARACT�RISTIQUES DU CONSTRUCTEUR.										
										
										this.zoneCaractèristiques.add ( LaBelleNom );									//	Mise en place de la section CARACT�RISTIQUES.
											this.nomAffiché = new JTextField ( être.getNom ( être ) , 24 );		//
											this.zoneCaractèristiques.add ( nomAffiché );								//		nom
										this.zoneCaractèristiques.add ( LaBelleType );								//
											this.typeAffiché = new JTextField ( être.getType ( être ) , 16 );		//
											this.zoneCaractèristiques.add ( typeAffiché );								//		type
										this.zoneCaractèristiques.add ( LaBelleSexe );								//
											this.sexeAffiché = new JTextField ( être.getSexe ( être ) , 4 );			//
											this.zoneCaractèristiques.add ( sexeAffiché );								//		sexe
										this.zoneCaractèristiques.add ( LaBelleAmour );							//
											this.amourAffiché = new JTextField ( String.valueOf ( amour ) , 16 );
											this.zoneCaractèristiques.add ( amourAffiché );							//		amour
										this.zoneCaractèristiques.add ( LaBelleEnergie );							//
											this.énergieAffichée = new JTextField ( String.valueOf ( énergie ) , 16 );
											this.zoneCaractèristiques.add ( énergieAffichée );						//		énergie
										this.zoneCaractèristiques.add ( LaBelleexpérience );					//
											this.expérienceAffichée = new JTextField ( String.valueOf ( expérience ) , 16 );
											this.zoneCaractèristiques.add ( expérienceAffichée );				//		expérience
																																							//	.
											
//	�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~~	ZONE ACTIONS POSSIBLES DU CONSTRUCTEUR.
											
										this.zoneActions.add ( LaBelleLieux );					//			Mise en place des listes déroulantes :
										this.zoneActions.add ( lieux );									//	//	Liste déroulante LIEUX.
											this.lieux.addItem ( "Sélectionnez..." );				//	//
											this.lieux.addItem ( "- aucun -" );						//	//
											this.lieux.addItem ( "Lymbes" );							//	//
											this.lieux.addItem ( "Paradis" );							//	//
											this.lieux.addItem ( "Réalité" );							//	//
											this.lieux.addItem ( "Géhène" );							//	//
										this.zoneActions.add ( envoiAfficherLieu );		//	//.
																																//
										this.zoneActions.add ( LaBelleêtres );					//	//	Liste déroulante êtres.
										this.zoneActions.add ( êtres );								//	//
										this.êtres.addItem ( "Sélectionnez..." );					//	//
										this.êtres.addItem ( "Dieu" );									//	//
											for ( Être être2 : Dieu.LivreDesNoms )					//	//
												{																				//	//
													êtres.addItem ( être2.getNom ( être2 ) );//	//		Chargement du nom de tous les êtres dans la liste déroulante.
												}																				//	//
										this.zoneActions.add ( envoiAfficherÊtre );			//	//	.
																																//	.

										this.zoneActions.add ( envoiAfficherGL );					//	Mise en place de la section ACTIONS POSSIBLES.
										this.zoneActions.add ( envoiAfficherLDN );				//
										this.zoneActions.add ( écrireDansLDN );						//
										this.zoneActions.add ( envoiAffichermémoire );		//
										this.zoneActions.add ( setImagination );						//
										this.zoneActions.add ( aimer );										//
										this.zoneActions.add ( apprendre );							//					A compléter !!!
										this.zoneActions.add ( écouter );									//
										this.zoneActions.add ( réfléchir );									//
										this.zoneActions.add ( Dire );											//
										this.zoneActions.add ( DireA );										//
										this.zoneActions.add ( DireATous );								//
										this.zoneActions.add ( ligne );										//	.
										
										
//	�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~~	ZONE ENVIRONNEMENT DU CONSTRUCTEUR.
										
										this.zoneEnvironnement.add ( LaBelleEnvironnement );		//	Mise en place de la section ENVIRONNEMENT.
										this.zoneEnvironnement.add ( environnement );						//	.
										
										
//	�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~~	ZONE mémoire DU CONSTRUCTEUR.										
										
										this.zonemémoire.add ( LaBellemémoire );						//	Mise en place de la section mémoire.
										this.zonemémoire.add ( mémoire );										//	.
										
//	_______________________________________________________________________________
//	�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�~~�		LISTENEURS DU CONSTRUCTEUR.
										
										envoiConsole.addActionListener ( this );						//	Rajout des Listeners divers.
										envoiAfficherLDN.addActionListener ( this );				//
										envoiAfficherGL.addActionListener ( this );				//
																																		//
										écrireDansLDN.addActionListener ( this );					//
										envoiAffichermémoire.addActionListener ( this );		//
										setImagination.addActionListener ( this );					//
																																		//
										aimer.addActionListener ( this );									//
										apprendre.addActionListener ( this );							//
										écouter.addActionListener ( this );								//
										réfléchir.addActionListener ( this );								//
																																		//
										Dire.addActionListener ( this );										//
										DireA.addActionListener ( this );										//
										DireATous.addActionListener ( this );							//
										ligne.addActionListener ( this );										//
																																		//
										lieux.addItemListener ( this );											//
										envoiAfficherLieu.addActionListener ( this );				//
																																		//
										êtres.addItemListener ( this );											//
										envoiAfficherÊtre.addActionListener ( this );				//	.
										
										this.setVisible ( true );												//	Rendre la fenêtre visible.
										
										String nom4 = être.getNom ( être );
										if ( nom4 == "Dieu" )												//	Si c'est l'interface graphique de DIEU :
											{
												this.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );//	Termine le programme 
											}																											//à la fermeture car c'est Dieu.
									}	// ___________________Fin du CONSTRUCTEUR de GUI.___________________________________

								
//	�~~�~~�~~�~~�~~�~~�~~Gestion des action produites
								@Override
								public void actionPerformed ( ActionEvent e )
									{
										if ( e.getSource (  ) == aEnvoyerConsole )
											{
												console.append (  "Vous avez appuyé sur le bouton 'Envoyer' de la console.\n" );
											}
										else if ( e.getSource (  ) == Dire )
											{
												console.append ( "Vous avez appuyé sur le bouton de la fonction 'Dire'.\n" );
											}
										else if ( e.getSource (  ) == DireA )
											{
												console.append (  "Vous avez appuyé sur le bouton de la fonction 'DireA'.\n" );
											}	
										else if ( e.getSource (  ) == DireATous )
											{
												console.append (  "Vous avez appuyé sur le bouton de la fonction 'DireATous'.\n" );
											}	
										else if ( e.getSource (  ) == envoiAfficherLieu )
											{
												console.append (  "Vous avez appuyé sur le bouton 'Afficher' le la liste déroulante 'lieu'.\n" );
											}
										else if ( e.getSource (  ) == aimer )
											{
												this.être.aimer ( null );		//	A corriger !!!
//												this.zoneCaractèristiques.repaint (  );
												console.append ( "Vous avez lancé l'action : aimer (  ).\n" );
											}
									}
								
								@Override
								public void itemStateChanged ( ItemEvent e2 )
									{
										if ( e2.getItemSelectable (  ) == lieux )
											{
												console.append (  "Vous avez sélectionné l'option " + e2.getItem (  ) + " dans " + LaBelleLieux.getText (  ) + ".\n" );
											}
										
										if ( e2.getItemSelectable (  ) == êtres )
											{
												console.append ( "Vous avez sélectionné l'option " + e2.getItem (  ) + " dans " + LaBelleêtres.getText (  ) + ".\n" );
											}
									}
						}	//	FIN DE LA CLASSE -INTERNE- : GUI ------------------------------------------------------------------------------------------

										
										
						                                        public class Syntaxor
						
						{
						                        private String                                                                   texte;
						                        private Map<Character, String>                               alphabet;
						                        private Character                                                          caractèreActuel;
						                        private String                                                                   motActuel;
						                        private String                                                                   phraseActuelle;
						                        private String                                                                   chapitreActuel;
						                        private Map<String,String>        									Dictionnaire;
						                        private int                                                                        	i;	//	Pratique ! ;)
						                        private boolean																analyseTerminée;
						                       
						
						public Syntaxor ( String texte )
							{
						        this.texte = texte;
						        this.alphabet = new HashMap<Character , String> (  );
						        this.motActuel = "";
						        this.phraseActuelle = "";
						        this.chapitreActuel = "";
						        this.Dictionnaire = new HashMap<String , String> (  );
						        this.i = 0;
						        this.analyseTerminée = false;
						
						        analyserTexte (  );
						
						        //            TEST DIVERS
						       this.alphabet.put ( 'a' , "Première lettre" );
						       System.out.println ( this.alphabet.get ( 'a' ) );
						        this.alphabet.clear (  );
						        this.Dictionnaire.put ( "salope", "insulte;répondre:VTFE!;" );
						        System.out.println ( this.Dictionnaire.get ( "salope" ) );
						        this.Dictionnaire.clear (  );
						        //            FIN TESTS
							}
						
						
						private void analyserTexte (  )
							{
						        for ( this.i = this.i; this.i <= this.texte.length (  ); this.i ++ )
						        	{
						            	déterminerMots (  );
						            }
						        
						        setI ( 0 );
						        System.out.println ( "Du coup, this.i = " + this.i + 
						        									" car je viens en mode à la batarde de le réinitialiser !" );
						        this.analyseTerminée = true;
							}
						
						private void déterminerMots (  )
							{
						        System.out.println ( "Je fontionne pour chaque caractère ! Caractère n° " + this.i );
							}
						
						
						
						private void setI ( int i )
							{
						        this.i = i;
							}
						
						private int getI (  )
							{
						        return this.i;
							}
						}	// FIN DE LA CLASSE INTERNE : Syntaxor--------------------------------------------------------------------------------------

}	//	FIN DE LA CLASSE : Être	===============================================================
