package fr.istic.pdl.groupe6.msw;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * PDL MIAGE 1718, Project #3 (MatrixSynthesizerWikipedia), Group 6
 * 
 * @author HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnès, ZOHOUN Nellya,
 *         TCHIDIME Hugues, NGOUALEM Alvine
 * @version 1.0
 * @since 2017-10-31
 * 
 * 
 * 
 */

public class ConfigFile {
	String pageName;
	List<String> listeAComparer = new ArrayList<String>();

	/**
	 * TODO
	 */
	public ConfigFile(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * TODO
	 */
	public String getTitles() {
		return this.pageName;
	}
}
