import java.net.UnknownHostException;
import java.util.ArrayList;


public abstract class Dieu extends Thread
{
			protected static String 								nom;
			protected static int									portUDP;
		 	protected static double							amour;
		 	protected static int									énergie;
			protected static int									expérience;
			protected static ArrayList<Être>				Imagination;
		 	
					protected static ArrayList<String>				GrandLivre;
					protected static ArrayList<Être>					LivreDesNoms;
					protected static ArrayList<String>				Commandements;
					protected static ArrayList<Être>					Dictionnaire;
					protected static ArrayList<Être>					Alphabet;
		
						protected static ArrayList<Être> 				Paradis;
						protected static ArrayList<Être> 				Lymbes;
						protected static ArrayList<Être>	 				Réalité;
						protected static ArrayList<Être>					Géhène;

							protected static int									nbÊtresCréés = 0;
				
				protected static int 					tempsUniversel;
				protected static int					CONSTANTE_TEMPS = 200;		//	1 tour de jeu toutes les 200 secondes.
																								//	200 000 000 000 / 1 000 000 000, 1 action toutes les 1m 40s.
				
								protected static double			TAUXDAMOUR_LYMBES = 100_000_000d;	// ^3
								protected static double		 	TAUXDAMOUR_PARADIS = 10_000d;		// ^2
								protected static double		 	TAUXDAMOUR_TERRE = 100d;
								protected static double			TAUXDAMOUR_GéHèNE = 10d;
				
									protected static int		 		TAUXDENERGIE_LYMBES = 100_000_000;// ^3
									protected static int		 		TAUXDENERGIE_PARADIS = 10_000;	// ^2
									protected static int		 		TAUXDENERGIE_TERRE = 100;
									protected static int				TAUXDENERGIE_GéHèNE = 10;
				

//	Fonctions physiologiques :
protected abstract void		aimer ( Être être );
protected static void 			aimer( double x , Être être )
	{
		x = x * 2;
		double y = ( double ) Dieu.nbÊtresCréés;
		
		double z = x + 1.0 * ( y * 2.0 );
		être.setAmour ( être , z );
	}

protected abstract Être 		enfanter ( String nomEnfant , String sexeEnfant , Être Père , Être Mère );

protected abstract void 		apprendre (  );

protected abstract void 		écouter ( String phrase );

protected abstract void 		réfléchir (  );

protected abstract void 		setImagination ( Être être , Être être2 );

protected abstract void		afficherImagination ( Être être );

protected abstract void		seDédoubler ( Être être , String dimensioDArrivée , String lieuDArrivée );


protected abstract void 		dire ( Être être , String phrase );

protected abstract void 		direA ( String phrase , Être machin );

protected abstract void 		direATous ( Être être , String phrase , String dimension );

//	Fonctions techniques :
protected abstract void		afficherMémoire ( Être être );

protected abstract void		regarderGL ( Être être );

protected abstract void 		regarderLieu ( Être lieu );


protected abstract void 		écrireDansLDN ( Être être );
protected static void			écrireDansLDN ( boolean x , Être être )
	{
		LivreDesNoms.add ( être );
	}

protected abstract void 		afficherLDN ( Être être );

protected abstract void 		ligne ( char caractère , int n  );


										private abstract class GUI	{  }
										private abstract class Syntaxor {  }

}
