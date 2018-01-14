package fr.istic.pdl.groupe6.msw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DesambiguationWikidata {
	ParserWikidata parserWD;
	List<String> listeComparaisonWD = new ArrayList<String>();
	ReadConfigFile fileInPut = new ReadConfigFile();
	
	@SuppressWarnings("rawtypes")
	public List<String> getListPourComparaion(List<String> listeAComparer) {

		// int num_Page_choisie = 1;

		for (String pageName : listeAComparer) {

			try {
				if (Integer.parseInt(pageName) / 1 == Integer.parseInt(pageName))
					listeComparaisonWD.add("Q"+pageName);

			} catch (NumberFormatException e) {
				ConfigFile configFile = new ConfigFile(pageName);
				String search = configFile.getTitles();
				parserWD = new ParserWikidata();

				HashMap map = new HashMap<Integer, String>();
				map = parserWD.searchWD(search);

				System.out.println("Veuillez faire votre choix");

				int keyValue = App.sc.nextInt();

				Iterator iterator = map.entrySet().iterator();

				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					Integer cle = (Integer) entry.getKey();
					String valeur = (String) entry.getValue();
					if (keyValue == cle) {
						listeComparaisonWD.add(valeur);
					}
				}
			}
		}
		
		return listeComparaisonWD;
	}
}
