package fr.istic.pdl.groupe6.msw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * PDL MIAGE 1718, Project #3 (MatrixSynthesizerWikipedia), Group 6
 * 
 * @author HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnès, ZOHOUN Nellya,
 *         TCHIDIME Hugues, NGOUALEM Alvine
 * @version 1.0
 * @since 2017-10-31
 * 
 *        Principal class
 * 
 */

public class App {
	/**
	 * Main method
	 * 
	 * @param args
	 * 			Main method
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		ReadConfigFile fileInPut = new ReadConfigFile();
//		List<String> listeAComparer = fileInPut.getList();
//		TraitetementDisambiguation traitementFile = new TraitetementDisambiguation();
//		traitementFile.getListPourComparaion(listeAComparer);
//
//		ConfigFile configFile = new ConfigFile();
//		String search = configFile.getTitles();
		
		System.out.println ("Veuillez indiquer le moteur de recherche que vous voulez utiliser :" 
				+ "\n[1] Wikipedia"
				+ "\n[2] Wikidata");
		
		int option = 0;
		try {
			option = sc.nextInt();
		} catch (Exception e) {}
		
		
		switch(option) {
		case 1:
			System.out.println("*** Wikipedia *** \nLoading...");
			ParserWikipedia parserWp = new ParserWikipedia();
			
			// parser.searchWP("Cartagena");
			
			// TODO add disambiguation, must return a list like listToCompare
			
			List<String> listToCompare = new ArrayList<String>();
			listToCompare.add("5222");
			listToCompare.add("5843419");
			
			Map<Integer, Map> compWp = new LinkedHashMap<Integer, Map>();
			String id;
			
			for(Iterator it = listToCompare.iterator(); it.hasNext();) {
				id = (String) it.next();
				Map<String, String> mapWd = parserWp.getContentWP(id);
				assert (!mapWd.isEmpty());
				compWp.put(Integer.parseInt(id), mapWd);
				System.out.println(mapWd);
			}
		
			Compare compare = new Compare();
	
			int sizeIntersection = compare.getIntersection(compWp).size();
			float percentageMatch = compare.getPercentageMatch(compWp) * 100;
	
			System.out.println("Le % de coïncidence est " + String.format("%.1f", percentageMatch) + "% et le "
					+ "nombre de paramètre en " + "commun est " + sizeIntersection);
	
			if (compare.checkAllParameterInfobox(compWp)) {
				System.out.println("\nVeuillez selectionner l'option pour le nombre de paramètres que vous "
						+ "voulez avoir dans le fichier CSV ? \n" + "[1] Tous le paramètres\n"
						+ "[2] Les paramètres en commun\n" + "[5] Annuler");
				
				int option1 = 0;
				try {
					option1 = sc.nextInt();
				} catch (Exception e) {System.out.println(e);}
				
				switch(option1) {
				case 1:
					System.out.println("Création du fichier CSV avec tous les paramètres ... ");
					// TODO code class that create file CSV from variable comp
					System.out.println("done !");
					break;
				case 2:
					System.out.println("Création du fichier CSV avec les paramètres en commun ... ");
					// TODO code class that create file CSV from variable comp
					break;
				default:
					System.out.println("Operation annulée");
					break;
				}
			} else {
				System.out.println("Un ou plusieurs elements de votre choix ne peuvent pas être comparés .");
			}
			break;
			
		case 2:
			System.out.println("*** Wikidata *** \nLoading...");
			ParserWikidata parserWd = new ParserWikidata();
			
			// TODO add disambiguation, must return a list like listToCompare2
//			parser.searchWD("Cartagena");
			List<String> listToCompare2 = new ArrayList<String>();
			listToCompare2.add("Q739");
			listToCompare2.add("Q142");
			
			Map<Integer, Map> compWd = new LinkedHashMap<Integer, Map>();
			String id2 = "";
			
			for(Iterator it = listToCompare2.iterator(); it.hasNext();) {
				assert (id2.charAt(0) == 'Q');
				id2 = (String) it.next();
				Map<String, String> mapWd = parserWd.getContentWD(id2);
				assert (!mapWd.isEmpty());
				compWd.put(Integer.parseInt(id2.substring(1)), mapWd);
				System.out.println(mapWd);
			}
			
			Compare compareWd = new Compare();
			
			int sizeIntersectionWd = compareWd.getIntersection(compWd).size();
			float percentageMatchWd = compareWd.getPercentageMatch(compWd) * 100;
	
			System.out.println("Le % de coïncidence est " + String.format("%.1f", percentageMatchWd) + "% et le "
					+ "nombre de paramètre en " + "commun est " + sizeIntersectionWd);
			
			if (compareWd.checkAllParameterInfobox(compWd)) {
				System.out.println("\nVeuillez selectionner l'option pour le nombre de paramètres que vous "
						+ "voulez avoir dans le fichier CSV ? \n" + "[1] Tous le paramètres\n"
						+ "[2] Les paramètres en commun\n" + "[a] Annuler");
				System.out.print("Creating file CSV .... ");
				// TODO code class that create file CSV from variable comp
				System.out.println("done !");
			} else {
				System.out.println("Un ou plusieurs elements de votre choix ne peuvent pas être comparés .");
			}
			break;
			
		default:
			System.out.println("L'option saisie n'existe pas.");
				
		}
		sc.close();

	}
}