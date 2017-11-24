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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
		String url = "https://www.wikipedia.org/w/api.php?action=query&titles=" + search + "_(disambiguation)&prop=links&format=json";
	    InputStream is = null;
	    try {
	        is = new URL(url).openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	        String jsonText = readAll(rd);
	        JsonElement rootObj = new JsonParser().parse(jsonText);
	        String idpage = "10710961"; // Il faut récupérer la clé
	        JsonElement disambiguation = getValues(rootObj, "query/pages/" + idpage + "/links/");
	        JsonArray msg = (JsonArray) disambiguation;
	        Iterator<JsonElement> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(getValues(iterator.next(), "title").getAsString());
            }
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
	
	public static String getContentWP(String pageid) {
		String url = "https://www.wikipedia.org/w/api.php?action=query&pageids=" + pageid + "&prop=revisions&rvprop=content&rvsection=0&format=json";
	    InputStream is = null;
	    try {
	        is = new URL(url).openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	        String jsonText = readAll(rd);
	        JsonElement rootObj = new JsonParser().parse(jsonText);
	        // Check if exist the page
	        String infoboxJson = getValues(rootObj, "query/pages/" + pageid + "/revisions/").toString();
	        System.out.println(infoboxJson);
	        
	        // Check if there is an infobox
	        int start = infoboxJson.indexOf("Infobox ");
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
//	        String[] infoboxSplit = infobox.split("\\\\n\\|\\ ");
	        String[] infoboxSplit = infobox.split("\\\\n\\|");
	        for(String i: infoboxSplit)
	        	System.out.println(i);
	        
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
	
}
