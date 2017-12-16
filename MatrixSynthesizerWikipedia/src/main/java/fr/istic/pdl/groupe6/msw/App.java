package fr.istic.pdl.groupe6.msw;

import java.io.IOException;
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
	 * TODO
	 */
	public static void main(String[] args) {
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
		Scanner sc = new Scanner(System.in);
		int option = 0;
		try {
			option = sc.nextInt();
		} catch (Exception e) {}
		sc.close();
		
		switch(option) {
		case 1:
			System.out.println("*** Wikipedia ***");

			ParserWikipedia parserWp = new ParserWikipedia();
	//		parser.searchWP("Cartagena"); TODO add disambiguation
			
			Map<String, String> map1 = parserWp.getContentWP("5222");
			// System.out.println(map1);
			Map<String, String> map2 = parserWp.getContentWP("5843419");
			// System.out.println(map2);
	
			Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
	
			assert (map1.isEmpty());
			comp.put(5222, map1);
			comp.put(5843419, map2);
		
			Compare compare = new Compare();
	
			int sizeIntersection = compare.getIntersection(comp).size();
			float percentageMatch = compare.getPercentageMatch(comp) * 100;
	
			System.out.println("Le % de coïncidence est " + String.format("%.1f", percentageMatch) + "% et le "
					+ "nombre de paramètre en " + "commun est " + sizeIntersection);
	
			if (compare.checkAllParameterInfobox(comp)) {
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
			
		case 2:
			System.out.println("*** Wikidata ***");
			ParserWikidata parserWd = new ParserWikidata();
			
			// TODO add disambiguation, must return a list like listToCompare
//			parser.searchWD("Cartagena");
			List<String> listToCompare = new ArrayList<String>();
			listToCompare.add("Q739");
			listToCompare.add("Q142");
			
//			Map<String, Map> compWd = new LinkedHashMap<String, Map>();
//			String id;
//			
//			for(Iterator it = listToCompare.iterator(); it.hasNext();) {
				// TODO check first character
//				id = (String) it.next();
//				id = Integer.parseInt((String) it.next(););
//				Map<String, String> mapWd = parserWd.getContentWD(id);
//				assert (mapWd.isEmpty());
//				compWd.put(id, mapWd);
//				System.out.println(mapWd);
//			}
			
			Map<Integer, Map> compWd = new LinkedHashMap<Integer, Map>();
			
			Map<String, String> mapWd1 = parserWd.getContentWD("Q739");
			System.out.println(mapWd1);
			Map<String, String> mapWd2 = parserWd.getContentWD("Q142");
			System.out.println(mapWd2);			

			compWd.put(5222, mapWd1);
			compWd.put(5843419, mapWd2);
			
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

	}
}