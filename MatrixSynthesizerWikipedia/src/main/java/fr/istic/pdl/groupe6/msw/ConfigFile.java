package fr.istic.pdl.groupe6.msw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
 * Class to load configuration file
 * 
 */

public class ConfigFile {
	 String pageName;
	 List<String> listeAComparer =  new ArrayList<String>();
	 
	public ConfigFile(String pageName) {
		this.pageName = pageName;
	}
	
	public String getTitles() {
		System.out.print( "Loading configuration file..." );
		// ADD ME THE CODE TO LOAD THE FILE
		System.out.println( "    File loaded successfully." );
		return this.pageName;
	}
	
	/**
	 * retourne une liste de PageName
	 * @return 
	 * 			listeAComparer
	 */
	public List<String> getList(){
		String fichier ="E:\\config.json";
		try{
	        InputStream ips=new FileInputStream(fichier);
	        InputStreamReader ipsr=new InputStreamReader(ips);
	        BufferedReader br=new BufferedReader(ipsr);
	        String ligne;
	        while ((ligne=br.readLine())!=null){
	           listeAComparer.add(ligne);
	        }
	        br.close();
	     }    
	     catch (Exception e){
	        System.out.println(e.toString());
	     }
	     return listeAComparer;
	} 

}
