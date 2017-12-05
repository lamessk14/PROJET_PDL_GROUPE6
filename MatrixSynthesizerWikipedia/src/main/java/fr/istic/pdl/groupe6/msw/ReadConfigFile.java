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
 * @authors HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnès, ZOHOUN Nellya, TCHIDIME Hugues, NGOUALEM Alvine
 * @version 1.0
 * @since 2017-10-31
 * 
 * 
 * 
 */

public class ReadConfigFile {
	List<String> listeAComparer =  new ArrayList<String>();
	
	public List<String> getList(){
		String fichier ="src/main/java/fr/istic/pdl/groupe6/msw/config.json";
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