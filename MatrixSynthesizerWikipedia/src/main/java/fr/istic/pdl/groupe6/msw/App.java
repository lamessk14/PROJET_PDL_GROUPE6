package fr.istic.pdl.groupe6.msw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * PDL MIAGE 1718, Project #3 (MatrixSynthesizerWikipedia), Group 6
 * 
 * @author HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agn�s, ZOHOUN Nellya,
 *         TCHIDIME Hugues, NGOUALEM Alvine
 * @version 1.0
 * @since 2017-10-31
 * 
 *        Principal class
 * 
 */

public class App {
	public static Scanner sc = new Scanner(System.in);
	/**
	 * Main method
	 * 
	 * @param args
	 * 			Main method
	 */
	public static void main(String[] args) {
		
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
			
			//add desambiguation wp
			ReadConfigFile fileInPut = new ReadConfigFile();
			List<String> listeAComparer = fileInPut.getList();
			DesambiguationWikipedia traitementFile = new DesambiguationWikipedia();
			List<String> listToCompare= traitementFile.getListPourComparaion(listeAComparer);
			
			Map<Integer, Map> compWp = new LinkedHashMap<Integer, Map>();
			String id;
			//Variable qui récupère le contenu final de mapWd
			Map<String, String> mapToCsv = null;
			
			for(Iterator it = listToCompare.iterator(); it.hasNext();) {
				id = (String) it.next();
				Map<String, String> mapWd = parserWp.getContentWP(id);
				assert (!mapWd.isEmpty());
				compWp.put(Integer.parseInt(id), mapWd);
				System.out.println(mapWd);
				mapToCsv = mapWd;
			}
		
			Compare compare = new Compare();
	
			int sizeIntersection = compare.getIntersection(compWp).size();
			float percentageMatch = compare.getPercentageMatch(compWp) * 100;
	
			System.out.println("Le % de coïncidence est " + String.format("%.1f", percentageMatch) + "% et le "
					+ "nombre de paramètre en " + "commun est " + sizeIntersection);
	
			if (compare.checkAllParameterInfobox(compWp)) {
				System.out.println("\nVeuillez selectionner l'option pour le nombre de paramètres que vous "
						+ "voulez avoir dans le fichier CSV ? \n" + "[1] Tous le paramètres\n"
						+ "[2] Les paraètres en commun\n" + "[5] Annuler");
				
				int option1 = 0;
				try {
					option1 = sc.nextInt();
				} catch (Exception e) {System.out.println(e);}
				
				switch(option1) {
				case 1:
					System.out.println("Création du fichier CSV avec tous les paramètres ... ");
					// TODO code class that create file CSV from variable comp
					ToCsv toCsv = new ToCsv();
			        Set<String> resultComp = compare.getIntersection(compWp);
					String [] header = resultComp.toArray(new String[resultComp.size()]);
					
			        try {
						toCsv.writeWithCsvMapWriter(mapToCsv,header);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("done !");
					break;
				case 2:
					System.out.println("Création du fichier CSV avec les paramètres en commun ... ");
					// TODO code class that create file CSV from variable comp
					ToCsv toCsv1 = new ToCsv();
			        Set<String> resultComp1 = compare.getIntersection(compWp);
					String [] header1 = resultComp1.toArray(new String[resultComp1.size()]);
					
			        try {
						toCsv1.writeWithCsvMapWriter(mapToCsv,header1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
			//Variable qui r�cup�re le contenu final de mapWd
			Map<String, String> mapToCsv2 = null;
			
			for(Iterator it = listToCompare2.iterator(); it.hasNext();) {
				assert (id2.charAt(0) == 'Q');
				id2 = (String) it.next();
				Map<String, String> mapWd = parserWd.getContentWD(id2);
				assert (!mapWd.isEmpty());
				compWd.put(Integer.parseInt(id2.substring(1)), mapWd);
				System.out.println(mapWd);
				mapToCsv2 = mapWd;
			}
			
			Compare compareWd = new Compare();
			
			int sizeIntersectionWd = compareWd.getIntersection(compWd).size();
			float percentageMatchWd = compareWd.getPercentageMatch(compWd) * 100;
	
			System.out.println("Le % de coïncidence est " + String.format("%.1f", percentageMatchWd) + "% et le "
					+ "nombre de paramétre en " + "commun est " + sizeIntersectionWd);
			
			if (compareWd.checkAllParameterInfobox(compWd)) {
				System.out.println("\nVeuillez selectionner l'option pour le nombre de paramètres que vous "
						+ "voulez avoir dans le fichier CSV ? \n" + "[1] Tous le paramètres\n"
						+ "[2] Les paramètres en commun\n" + "[a] Annuler");
				System.out.print("Creating file CSV .... ");
				// TODO code class that create file CSV from variable comp
				ToCsv toCsv1 = new ToCsv();
		        Set<String> resultComp1 = compareWd.getIntersection(compWd);
				String [] header1 = resultComp1.toArray(new String[resultComp1.size()]);
				
		        try {
					toCsv1.writeWithCsvMapWriter(mapToCsv2,header1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
