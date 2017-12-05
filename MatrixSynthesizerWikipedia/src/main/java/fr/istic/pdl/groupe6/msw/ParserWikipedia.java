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
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * PDL MIAGE 1718
 * Project #3 (MatrixSynthesizerWikipedia)
 * Group 6
 * 
 * @authors HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnès, ZOHOUN Nellya, TCHIDIME Hugues
 * @version 1.0
 * @since 2017-10-31
 * 
 * Class to parse Wikipedia's results
 * 
 */

public class ParserWikipedia {

	private static String readAll(Reader rd) throws IOException {
	    BufferedReader reader = new BufferedReader(rd);
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    return sb.toString();
    }

	private static JsonElement getValues(JsonElement json, String keys) {
	    String jsonString[] = keys.split("/");
	    for (int i = 0; i < jsonString.length; i++) {
	    	json = json.getAsJsonObject().get(jsonString[i]);
	    }
	    return json;
	}
	
	public static String searchWP(String search) {
		//String url = "https://en.wikipedia.org/w/api.php?action=query&titles=" + search + "&prop=links&format=json";
	    String url = "https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + search + "&format=json";
		InputStream is = null;
	    try {
	        is = new URL(url).openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	        String jsonText = readAll(rd);
	        JsonElement rootObj = new JsonParser().parse(jsonText);
	        JsonElement disambiguation = getValues(rootObj, "query/search/");
	        //System.out.println(disambiguation);
	        JsonArray msg = (JsonArray) disambiguation;
	        Iterator<JsonElement> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(getValues(iterator.next(), "title").getAsString());
                //System.out.println(getValues(iterator.next(), "pageid").getAsString());
            }
            Iterator<JsonElement> iterator1 = msg.iterator();
            while (iterator1.hasNext()) {
                System.out.println(getValues(iterator1.next(), "pageid").getAsString());
                //System.out.println(getValues(iterator.next(), "pageid").getAsString());
            }
	        
//	        String idpage = null; // TODO Il faut récupérer la clé
//	        
//	        JsonElement disambiguation1 = getValues(rootObj, "query/pages/");      
//	        JsonObject root = disambiguation1.getAsJsonObject();
//	        for (Entry<String, JsonElement> e : root.entrySet()) {
//	            idpage = e.getKey();
//	        }
//	        
//	        JsonElement disambiguation = getValues(rootObj, "query/pages/" + idpage + "/links/");
//	        JsonArray msg = (JsonArray) disambiguation;
//	        Iterator<JsonElement> iterator = msg.iterator();
//            while (iterator.hasNext()) {
//                System.out.println(getValues(iterator.next(), "title").getAsString());
//            }
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
		
		return null;
	}
	
	public static Map<String, String> getContentWP(String pageid) {
		String url = "https://www.wikipedia.org/w/api.php?action=query&pageids=" + pageid + "&prop=revisions&rvprop=content&rvsection=0&format=json";
	    InputStream is = null;
	    try {
	        is = new URL(url).openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	        String jsonText = readAll(rd);
	        JsonElement rootObj = new JsonParser().parse(jsonText);
	        // Check if exist the page
	        String infoboxJson = getValues(rootObj, "query/pages/" + pageid + "/revisions/").toString();
//	        System.out.println(infoboxJson);
	        
	        // Check if there is an infobox
	        int start = infoboxJson.indexOf("Infobox ");
	        assert start>0;
	        if(start<0) {
	        	System.out.println("Error: Infobox is empty");
	        }
	        int counter = 2;
	        while(counter > 0) {
	        	if(infoboxJson.charAt(start) == ('[' | '(' | '{')) {
	        		counter ++;
	        	}
	        	else if(infoboxJson.charAt(start) == (']' | ')' | '}')) {
	        		counter --;
	        	}
	        	start++;
	        }
	        String infobox = infoboxJson.substring(infoboxJson.indexOf("Infobox ")+8,start-4);
	        String[] infoboxSplit = infobox.split("\\\\n\\|");
	        infoboxSplit[0] = "infobox = " + infoboxSplit[0];
	        Map<String, String> map = new LinkedHashMap<String, String>();
	        for(String i: infoboxSplit) {
	        	map.put(i.split("=", 2)[0].replaceAll("\\s", ""), i.split("=", 2)[1].trim());
	        }
	        return map;
	        
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
		return null;
	}
	
	
	
	
	public void getContentWP2(String pageid) {
		String url = "https://www.wikipedia.org/w/api.php?action=query&titles=" + pageid + "&prop=revisions&rvprop=content&rvsection=0&format=json";
	    InputStream is = null;
	    try {
	        is = new URL(url).openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	        String jsonText = readAll(rd);
	        JsonElement rootObj = new JsonParser().parse(jsonText);
	        System.out.println(rootObj);
	        
	        
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
	}
}
