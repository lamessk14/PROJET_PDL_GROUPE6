package fr.istic.pdl.groupe6.msw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
 *        Class for checking wikipedia's disambiguation
 * 
 */

public class TraitetementDisambiguation {

	ParserWikipedia parser;
	List<String> listePourComparaison = new ArrayList<String>();
	ReadConfigFile fileInPut = new ReadConfigFile();
	Scanner sc = new Scanner(System.in);
	List<String> listeAComparer = fileInPut.getList();

	@SuppressWarnings("rawtypes")
	public List<String> getListPourComparaion(List<String> listeAComparer) {

		// int num_Page_choisie = 1;

		for (String pageName : listeAComparer) {

			try {
				if (Integer.parseInt(pageName) / 1 == Integer.parseInt(pageName))
					listePourComparaison.add(pageName);

			} catch (NumberFormatException e) {
				ConfigFile configFile = new ConfigFile(pageName);
				String search = configFile.getTitles();
				parser = new ParserWikipedia();

				HashMap map = new HashMap<Integer, String>();
				map = parser.searchWP(search);

				System.out.println("Veuillez faire votre choix");
				int keyValue = sc.nextInt();

				Iterator iterator = map.entrySet().iterator();

				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					Integer cle = (Integer) entry.getKey();
					String valeur = (String) entry.getValue();
					if (keyValue == cle) {
						// System.out.println(num_Page_choisie+" Page choisie "+valeur.replaceAll(" ",
						// "_"));
						// listePourComparaison.add(valeur.replaceAll(" ", "_"));
						listePourComparaison.add(valeur);
					}
				}
				// num_Page_choisie++;

			}
		}
		sc.close();

		System.out.println(listePourComparaison);

		for (String pageid : listePourComparaison) {
			System.out.println(parser.getContentWP(pageid));
		}

		return listePourComparaison;
	}

}
