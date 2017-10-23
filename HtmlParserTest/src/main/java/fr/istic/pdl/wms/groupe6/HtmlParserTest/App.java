package fr.istic.pdl.wms.groupe6.HtmlParserTest;

import java.io.IOException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
	public static void main(String[] args) throws IOException{    		
		Response res = Jsoup.connect("http://en.wikipedia.org/wiki/Colombia").execute();
	    String html = res.body();
	    Document doc = Jsoup.parseBodyFragment(html);
	    Element body = doc.body();
	    Elements tables = body.getElementsByTag("table");

	    for (Element table : tables) {
	    	// There are many different types of infobox class, but all of them start with "infobox" followed by the type
	        if (table.className().equalsIgnoreCase("infobox geography vcard")) {
	        	Elements trs = table.select("tr");
	        	for (Element tr : trs) {
		        	Elements ths = tr.select("th");
		        	Elements tds = tr.select("td");
		            System.out.println(ths.text() + " : " + tds.text());
	        	}
	            break;
	        }
	    }
    }
}