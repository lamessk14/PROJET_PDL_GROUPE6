package fr.istic.pdl.wms.groupe6.mediawikitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
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
	
	private HashMap<Integer, String> listePage;
	private HashMap<Integer, String> listePageId;
	private HashMap<Integer, String> listePages;
	String search;
	String idpage;
	
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
	
	
	
	public String getPageId(String search) {
		
		String url = "https://en.wikipedia.org/w/api.php?action=query&titles=" + search + "&prop=links&format=json";
		
		InputStream is = null;
	    try {
	        is = new URL(url).openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	        String jsonText = readAll(rd);

	        //JsonElement rootObj = new JsonParser().parse(jsonText);
	        //String idpage = "10710961"; // Il faut récupérer la clé
	        
	        
	        JsonElement rootObj = new JsonParser().parse(jsonText);
	        idpage = null; // TODO Il faut récupérer la clé
	        
	        JsonElement disambiguation1 = getValues(rootObj, "query/pages/");      
	        JsonObject root = disambiguation1.getAsJsonObject();
	        for (Entry<String, JsonElement> e : root.entrySet()) {
	            idpage = e.getKey();
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
		
		return idpage;
	}
	
	
	public  HashMap<Integer, String> searchWP(String search) {
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
	        
	        //valeur numérique 
	        int i=0;
	        String pageName;
	        String pageId;
	        //HasMap : pour récupérer la liste des pages
	        listePage = new HashMap<Integer, String>();
	        listePageId = new HashMap<Integer, String>();
	        listePages = new HashMap<Integer, String>();
	        while (iterator.hasNext()) {
	        	//recupère chaque page ambigu
	        	pageName= getValues(iterator.next(), "title").getAsString();
	        	//Ajout dans un HashMap
            	listePage.put(i, pageName);
            	//affichage de la liste des pages
                System.out.println(i+" "+pageName);
                i++;
            }
	        
	        int j=0;
	        Iterator<JsonElement> iterator1 = msg.iterator();
	        while (iterator1.hasNext()) {
	        	//recupère chaque page ambigu
	        	pageId= getValues(iterator1.next(), "pageid").getAsString();
	        	//Ajout dans un HashMap
	        	listePageId.put(j, pageId);
            	//affichage de la liste des pages
                //System.out.println(j+" "+pageId);
                j++;
            }
	        
	        listePages.putAll(listePage);
	        listePages.putAll(listePageId);
            
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
		
		return listePages;
	}
	
	public  String getContentWP(String pageid) {
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