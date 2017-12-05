package fr.istic.pdl.wms.groupe6.mediawikitest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadConfigFile {
	List<String> listeAComparer =  new ArrayList<String>();
	
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
