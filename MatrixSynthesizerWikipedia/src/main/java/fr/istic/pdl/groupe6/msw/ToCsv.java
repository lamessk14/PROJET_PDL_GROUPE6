package fr.istic.pdl.groupe6.msw;

import java.io.FileWriter;
import java.util.Map;
import java.util.Set;

import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * 
 * PDL MIAGE 1718, Project #3 (MatrixSynthesizerWikipedia), Group 6
 * 
 * @author HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnés, ZOHOUN Nellya,
 *         TCHIDIME Hugues, NGOUALEM Alvine
 * @version 1.0
 * @since 2017-10-31
 * 
 *        Class for creating csv file
 * 
 */

public class ToCsv {
	/**
	 * 
	 * @param map
	 * @param header
	 * @throws Exception
	 */

	public void writeWithCsvMapWriter(Map<String, String> map, String[] header) throws Exception {

		//Set<String> resultComp = compare.getIntersection(comp1);
		//header = resultComp.toArray(new String[resultComp.size()]);

		ICsvMapWriter mapWriter = null;
		try {
			mapWriter = new CsvMapWriter(
					new FileWriter("./writeWithCsvMapWriter.csv"),
					CsvPreference.STANDARD_PREFERENCE);

			// write the header
			mapWriter.writeHeader(header);

			// write the maps
			mapWriter.write(map, header);

		} finally {
			if (mapWriter != null) {
				mapWriter.close();
			}
		}
	}
}
