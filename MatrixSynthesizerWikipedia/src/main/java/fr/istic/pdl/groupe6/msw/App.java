package fr.istic.pdl.groupe6.msw;

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
 * 
 * 
 */

public class App 
{
    public static void main( String[] args )
    {        
        ConfigFile configFile = new ConfigFile();
        String search = configFile.getTitles();
        
        ParserWikipedia parser = new ParserWikipedia();
        parser.searchWP(search);
        
        //parser.getContentWP("5222");
        
    }
}
