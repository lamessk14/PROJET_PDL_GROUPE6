package fr.istic.pdl.groupe6.msw;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * PDL MIAGE 1718
 * Project #3 (MatrixSynthesizerWikipedia)
 * Group 6
 * 
 * @author HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnès, ZOHOUN Nellya, TCHIDIME Hugues
 * @version 1.0
 * @since 2017-10-31
 * 
 * 
 * 
 */

public class App 
{
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
    {   		
		System.out.println ("Veuillez indiquer le moteur de recherche que vous voulez utiliser :" 
				+ "\n[1] Wikipedia"
				+ "\n[2] Wikidata");
		
		int option = 0;
		try {
			option = sc.nextInt();
		} catch (Exception e) {}
		
		
		switch(option) {
		case 1:
			System.out.println("*** Wikipedia ***");
			ParserWikipedia parserWp = new ParserWikipedia();
			
			ReadConfigFile fileInPut = new ReadConfigFile();
			List<String> listeAComparer = fileInPut.getListWP();
			DesambiguationWikipedia traitementFile = new DesambiguationWikipedia();
			List<String> listToCompare= traitementFile.getListPourComparaion(listeAComparer);
	
			System.out.println("Loading...");
			Map<Integer, Map> compWp = new LinkedHashMap<Integer, Map>();
			String id;
			
			for(Iterator<String> it = listToCompare.iterator(); it.hasNext();) {
				id = (String) it.next();
				Map<String, String> mapWd = parserWp.getContentWP(id);
				assert (!mapWd.isEmpty());
				compWp.put(Integer.parseInt(id), mapWd);
			}
		
			Compare compare = new Compare();
	
			int sizeIntersection = compare.getIntersection(compWp).size();
			float percentageMatch = compare.getPercentageMatch(compWp) * 100;
	
			System.out.println("Le % de coïncidence est " + String.format("%.1f", percentageMatch) + "% et le "
					+ "nombre de paramètre en " + "commun est " + sizeIntersection);
			
			for(Map.Entry<Integer, Map> entry : compWp.entrySet()) {
				System.out.println(entry);
			}
	
			if (compare.checkAllParameterInfobox(compWp)) {
				System.out.println("\nVeuillez selectionner l'option pour le nombre de paramètres que vous "
						+ "voulez avoir dans le fichier CSV ? \n" + "[1] Tous le paramètres\n"
						+ "[2] Les paramètres en commun\n" + "[5] Annuler");
				
				int option1 = 0;
				try {
					option1 = sc.nextInt();
				} catch (Exception e) {System.out.println(e);}
				
				MapToCSVFile mapToCSV = new MapToCSVFile();
				
				switch(option1) {
				case 1:
					System.out.println("Création du fichier CSV avec tous les paramètres ... ");
					try {
						mapToCSV.mapToCSV(compWp);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Fait !");
					break;
				case 2:
					System.out.println("Création du fichier CSV avec les paramètres en commun ... ");
					try {
						mapToCSV.mapToCSVComplex(compWp);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Fait !");
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
			
			ReadConfigFile fileInPutWD = new ReadConfigFile();
			List<String> listACompare2 = fileInPutWD.getListWD();
			DesambiguationWikidata traitementFileWD = new DesambiguationWikidata();
			List<String> listToCompare2=traitementFileWD.getListPourComparaion(listACompare2);

			System.out.println("Loading...");
			Map<Integer, Map> compWd = new LinkedHashMap<Integer, Map>();
			String id2 = "";
			
			for(Iterator<String> it = listToCompare2.iterator(); it.hasNext();) {
				assert (id2.charAt(0) == 'Q');
				id2 = (String) it.next();
				Map<String, String> mapWd = parserWd.getContentWD(id2);
				assert (!mapWd.isEmpty());
				compWd.put(Integer.parseInt(id2.substring(1)), mapWd);
			}
			
			Compare compareWd = new Compare();
			
			int sizeIntersectionWd = compareWd.getIntersection(compWd).size();
			float percentageMatchWd = compareWd.getPercentageMatch(compWd) * 100;
	
			System.out.println("Le % de coïncidence est " + String.format("%.1f", percentageMatchWd) + "% et le "
					+ "nombre de paramètre en " + "commun est " + sizeIntersectionWd);
			
			for(Map.Entry<Integer, Map> entry : compWd.entrySet()) {
				System.out.println(entry);
			}
			
			if (compareWd.checkAllParameterInfobox(compWd)) {
				System.out.println("\nVeuillez selectionner l'option pour le nombre de paramètres que vous "
						+ "voulez avoir dans le fichier CSV ? \n" + "[1] Tous le paramètres\n"
						+ "[2] Les paramètres en commun\n" + "[5] Annuler");
				int option1 = 0;
				try {
					option1 = sc.nextInt();
				} catch (Exception e) {System.out.println(e);}
				
				MapToCSVFile mapToCSV = new MapToCSVFile();
				
				switch(option1) {
				case 1:
					System.out.println("Création du fichier CSV avec tous les paramètres ... ");
					try {
						mapToCSV.mapToCSV(compWd);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Fait !");
					break;
				case 2:
					System.out.println("Création du fichier CSV avec les paramètres en commun ... ");
					try {
						mapToCSV.mapToCSVComplex(compWd);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Fait !");
					break;
				default:
					System.out.println("Operation annulée");
					break;
				}
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