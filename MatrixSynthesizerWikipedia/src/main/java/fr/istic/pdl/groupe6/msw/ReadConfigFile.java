package fr.istic.pdl.wms.groupe6.mediawikitest;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

public class ReadConfigFile {

	List<String> listeToCompareWP = new ArrayList<String>();
	List<String> listeToCompareWD = new ArrayList<String>();
	JSONParser parser = new JSONParser();

	@SuppressWarnings("unchecked")
	public List<String> getListWP() {

		try {

			Object obj = parser.parse(new FileReader("configFile.json"));

			JSONObject jsonObject = (JSONObject) obj;
			JSONArray listNom = (JSONArray) jsonObject.get("ListName");
			JSONArray listId = (JSONArray) jsonObject.get("ListIdWikipedia");

			Iterator<String> iteratorNom = listNom.iterator();
			Iterator<Long> iteratorId = listId.iterator();

			while (iteratorNom.hasNext()) {
				listeToCompareWP.add(iteratorNom.next());
			}

			while (iteratorId.hasNext()) {
				String valId = iteratorId.next().toString();
				listeToCompareWP.add(valId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeToCompareWP;
	}

	@SuppressWarnings("unchecked")
	public List<String> getListWD() {

		try {

			Object obj = parser.parse(new FileReader("configFile.json"));

			JSONObject jsonObject = (JSONObject) obj;
			JSONArray listNom = (JSONArray) jsonObject.get("ListName");
			JSONArray listId = (JSONArray) jsonObject.get("ListIdWikidata");

			Iterator<String> iteratorNom = listNom.iterator();
			Iterator<Long> iteratorId = listId.iterator();

			while (iteratorNom.hasNext()) {
				listeToCompareWD.add(iteratorNom.next());
			}

			while (iteratorId.hasNext()) {
				String valId = iteratorId.next().toString();
				listeToCompareWD.add(valId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeToCompareWD;
	}
}