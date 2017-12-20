package fr.istic.pdl.groupe6.msw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * PDL MIAGE 1718, Project #3 (MatrixSynthesizerWikipedia), Group 6
 * 
 * @author HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnès, ZOHOUN Nellya,
 *         TCHIDIME Hugues, NGOUALEM Alvine
 * @version 1.0
 * @since 2017-10-31
 * 
 *        Class for creating a CSV file from an Map object
 * 
 */

public class MapToCSVFile {
	
	/**
	 * Method for creating CSV file with parameters in common
	 * 
	 * @param map
	 * 			Map<Integer, Map>
	 * @throws IOException 
	 */
	public void mapToCSV(Map<Integer, Map> map) throws IOException {
		
		String eol = System.getProperty("line.separator");
		
		SimpleDateFormat formato = new SimpleDateFormat("yyMMddHHmmss"); 
		Date now = new Date();
		String fileName = formato.format(now);
		
		File matrice = new File("files/matrice" + fileName + ".csv");
		FileWriter outfile = new FileWriter(matrice);
		
		boolean b = true;
		
			for(Map.Entry<Integer, Map> entry : map.entrySet()) {
				
				if(b) {
					Collection<String> keys = entry.getValue().keySet();
					int i1 = 0;
					Iterator<String> it1 = keys.iterator();
					while(it1.hasNext()) {
						String par = (String) it1.next();
						
						if(i1 != keys.size() - 1) {
							try {
								outfile.write(par + ",");
							} catch (IOException e) {
								System.out.println(e);
								e.printStackTrace();
							}
						} else {
							try {
								outfile.write(par + eol);
							} catch (IOException e) {
								System.out.println(e);
								e.printStackTrace();
							}
						}
						i1++;
					}
					b = false;
				}
	
				Collection<String> parametres = entry.getValue().values();
				Iterator<String> it = parametres.iterator();
				int i = 0;
				while(it.hasNext()) {
					String par = (String) it.next();
					
					if(i != parametres.size() - 1) {
						try {
							outfile.write(par + ",");
						} catch (IOException e) {
							System.out.println(e);
							e.printStackTrace();
						}
					} else {
						try {
							outfile.write(par + eol);
						} catch (IOException e) {
							System.out.println(e);
							e.printStackTrace();
						}
					}
					i++;
				}
			}
			outfile.flush();
			outfile.close();
	}
	
	/**
	 * Method for creating CSV file without parameters in common
	 * 
	 * @param map
	 * 			Map<Integer, Map>
	 * @throws IOException 
	 */
	public void mapToCSVComplex(Map<Integer, Map> map) throws IOException {
		
		String eol = System.getProperty("line.separator");
		
		SimpleDateFormat formato = new SimpleDateFormat("yyMMddHHmmss"); 
		Date now = new Date();
		String fileName = formato.format(now);
		
		File matrice = new File("files/matrice" + fileName + ".csv");
		FileWriter outfile = new FileWriter(matrice);
		
		Set<String> keys = new HashSet<String>();
		
		for(Map.Entry<Integer, Map> entry1 : map.entrySet()) {
			Collection<String> keysTemp = entry1.getValue().keySet();
			Iterator<String> it1 = keysTemp.iterator();
			while(it1.hasNext()) {
				keys.add(it1.next());
			}
		}
		
		Iterator<String> it2 = keys.iterator();
		int i2 = 0;
		while(it2.hasNext()) {
			String par = (String) it2.next();
			
			if(i2 != keys.size() - 1) {
				try {
					outfile.write(par + ",");
				} catch (IOException e) {
					System.out.println(e);
					e.printStackTrace();
				}
			} else {
				try {
					outfile.write(par + eol);
				} catch (IOException e) {
					System.out.println(e);
					e.printStackTrace();
				}
			}
			i2++;
		}
		
		
		for(Map.Entry<Integer, Map> entry : map.entrySet()) {
			
			Map<String, String> element = entry.getValue();
			Iterator<String> it3 = keys.iterator();
			int i = 0;
			while(it3.hasNext()) {
				String keyRef = it3.next();
				if(element.containsKey(keyRef)) {
					try {
						outfile.write(element.get(keyRef));
					} catch (IOException e) {
						System.out.println(e);
						e.printStackTrace();
					}
				}
				
				
				if(i != keys.size() - 1) {
					try {
						outfile.write(",");
					} catch (IOException e) {
						System.out.println(e);
						e.printStackTrace();
					}
				}
				i++;
			}
			try {
				outfile.write(eol);
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			
		}
		outfile.flush();
		outfile.close();
	}
	

}
