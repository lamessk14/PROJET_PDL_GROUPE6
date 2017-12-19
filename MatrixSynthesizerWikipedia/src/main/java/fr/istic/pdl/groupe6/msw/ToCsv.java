package fr.istic.pdl.groupe6.msw;

import java.io.FileWriter;
import java.util.Map;
import java.util.Set;

import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

public class ToCsv {

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
