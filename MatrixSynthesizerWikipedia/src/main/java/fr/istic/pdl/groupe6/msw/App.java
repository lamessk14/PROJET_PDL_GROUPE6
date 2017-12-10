package fr.istic.pdl.groupe6.msw;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

		ParserWikipedia parser = new ParserWikipedia();
//		parser.searchWP("Cartagena");

		Map<String, String> map1 = parser.getContentWP("5222");
		// System.out.println(map1);
		Map<String, String> map2 = parser.getContentWP("5843419");
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
			System.out.println("\nVeuillez selectionner le option pour le nombre de paramètres que vous "
					+ "voulez avoir dans le fichier CSV ? \n" + "[1] Tous le paramètres\n"
					+ "[2] Les paramètres en commun\n" + "[a] Annuler");
			System.out.print("Creating file CSV .... ");
			// TODO code class that create file CSV from variable comp
			System.out.println("done !");
		} else {
			System.out.println("Un ou plusieurs elements de votre choix ne peuvent pas être comparés .");
		}

	}
}