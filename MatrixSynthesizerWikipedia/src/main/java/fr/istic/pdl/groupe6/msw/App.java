package fr.istic.pdl.wms.groupe6.mediawikitest;

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
    }
}