package fr.istic.pdl.groupe6.msw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * PDL MIAGE 1718, Project #3 (MatrixSynthesizerWikipedia), Group 6
 * 
 * @author HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnès, ZOHOUN Nellya,
 *         TCHIDIME Hugues, NGOUALEM Alvine
 * @version 1.0
 * @since 2017-10-31
 * 
 *        Class to parse Wikidata
 * 
 */

public class ParserWikidata {

	/**
	 * Request to Wikidata
	 * 
	 * @param url
	 *            url requested
	 * @return rootObj Object json of the response
	 */
	private static JsonElement dataRequest(String url) {
		InputStream is = null;
		JsonElement rootObj = null;
		try {
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			rootObj = new JsonParser().parse(jsonText);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rootObj;
	}

	/**
	 * TODO
	 */
	private static String readAll(Reader rd) throws IOException {
		BufferedReader reader = new BufferedReader(rd);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

	/**
	 * TODO
	 */
	private static JsonElement getValues(JsonElement json, String keys) {
		String jsonString[] = keys.split("/");
		for (int i = 0; i < jsonString.length; i++) {
			json = json.getAsJsonObject().get(jsonString[i]);
		}
		return json;
	}

	/**
	 * TODO
	 */
	public static void searchWD(String search) {
		String url = "https://www.wikidata.org/w/api.php?action=wbsearchentities&search=" + search
				+ "&language=en&format=json";
		JsonElement rootObj = dataRequest(url);
		JsonElement disambiguation = getValues(rootObj, "search");
		System.out.println(disambiguation);
		// TODO check "id" and "label", in addition, a description could be add it
	}

	/**
	 * Method that returns the value of the id's entity
	 * 
	 * @param id
	 * 			id of the entity
	 * @return map
	 * 			Object Map with the infobox of the request
	 */
	public static Map<String, String> getContentWD(String id) {
		String url = "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=" + id + "&languages=en&format=json";
		JsonElement rootObj = dataRequest(url);
		JsonElement claims = getValues(rootObj, "entities/" + id + "/claims");

		Iterator<JsonElement> iterator;
		// since we know it's a JsonObject
		JsonObject obj = claims.getAsJsonObject();
		// will return members of your object
		Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("infobox","Wikidata");
		
		for (Map.Entry<String, JsonElement> entry : entries) {
			//System.out.print(entry.getKey() + " ");
			Set<String> value = new LinkedHashSet<String>();

			iterator = ((JsonArray) entry.getValue()).iterator();
			while (iterator.hasNext()) {
				JsonElement json = iterator.next();
				
				// If the data type is "string"
				if (getValues(json, "mainsnak/datatype").getAsString().equals("string")) {
					value.add(getValues(json, "mainsnak/datavalue/value").toString());
				} 
				
				// If the data type is "wikibase-item"
				else if (getValues(json, "mainsnak/datatype").getAsString().equals("wikibase-item")) {
					try {
						value.add(getIdValue(getValues(json, "mainsnak/datavalue/value/id").getAsString()));
					} catch (Exception e) {}
				} 
				
				// If the data type is "quantity"
				else if (getValues(json, "mainsnak/datatype").getAsString().equals("quantity")) {
					try {
						JsonElement value1 = getValues(json, "mainsnak/datavalue/value/amount");
						value.add(value1.toString());
					} catch (Exception e) {System.out.print(e);}
				}
				
				// Others cases
				else {
					//System.out.println(getValues(json, "mainsnak/datatype").getAsString());
				}
			}
			
			if(!value.isEmpty()) {
				map.put(getIdValue(entry.getKey()), value.toString());
			}
		}
		return map;
	}

	/**
	 * Method that returns the value of the id's entity
	 * 
	 * @param id
	 *            id of the entity
	 * @return value value of the entity
	 */
	private static String getIdValue(String id) {
		String url = "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=" + id
				+ "&languages=en&props=labels&format=json";
		JsonElement rootObj = dataRequest(url);
		String value = getValues(rootObj, "entities/" + id + "/labels/en/value").toString();
		return value;
	}

//	public static void main(String[] arg) {
//		System.out.println("************");
//		// searchWD("Colombia");
//		System.out.println("************");
//		
//		System.out.println(getContentWD("Q142"));
//		System.out.println("************");
//	}
}
