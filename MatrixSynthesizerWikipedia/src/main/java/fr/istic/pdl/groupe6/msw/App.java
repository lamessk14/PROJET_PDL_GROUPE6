package fr.istic.pdl.groupe6.msw;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

public class App 
{
    public static void main( String[] args )
    {
    	ReadConfigFile fileInPut = new ReadConfigFile();
    	List<String> listeAComparer =  fileInPut.getList();
    	TraitetementDisambiguation traitementFile = new TraitetementDisambiguation();
    	traitementFile.getListPourComparaion(listeAComparer);    	
    	
//        ConfigFile configFile = new ConfigFile();
//        String search = configFile.getTitles();
        
        ParserWikipedia parser = new ParserWikipedia();
//        parser.searchWP("Cartagena");
        
        Map<String, String> map1 = parser.getContentWP("5222");
//        System.out.println(map1);
        Map<String, String> map2 = parser.getContentWP("5843419");
//        System.out.println(map2);
        
        Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
        
        assert(map1.isEmpty());
        comp.put(5222, map1);
        comp.put(5843419, map2);
        
        Compare compare = new Compare();
        System.out.println(compare.getIntersection(comp));
        System.out.println("Paramètre en commun = " + compare.getIntersection(comp).size());
        System.out.println("% de coïncidence = " + compare.getPercentageMatch(comp));
        
    }
}