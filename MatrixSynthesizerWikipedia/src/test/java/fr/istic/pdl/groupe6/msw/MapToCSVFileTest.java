package fr.istic.pdl.groupe6.msw;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.istic.pdl.groupe6.msw.MapToCSVFile;

/**
 * 
 * PDL MIAGE 1718, Project #3 (MatrixSynthesizerWikipedia), Group 6
 * 
 * @author HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnès, ZOHOUN Nellya,
 *         TCHIDIME Hugues, NGOUALEM Alvine
 * @version 1.0
 * @since 2017-10-31
 * 
 *        Class for testing MapCSVFileTest
 * 
 */

public class MapToCSVFileTest {
	
	/**
	 * Method for testing MapToCSV()
	 * @throws IOException 
	 */
	@Test
	public void testMapToCSV() throws IOException {
		Map<String, String> m1 = new LinkedHashMap<String, String>();
		m1.put("key1", "value1");
		m1.put("key2", "value2");
		m1.put("key3", "value3");
		Map<String, String> m2 = new LinkedHashMap<String, String>();
		m2.put("key1", "value1");
		m2.put("key2", "value2");
		m2.put("key3", "value3");
		Map<String, String> m3 = new LinkedHashMap<String, String>();
		m3.put("key1", "value1");
		m3.put("key2", "value2");
		m3.put("key3", "value3");
		Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
		comp.put(1, m1);
		comp.put(2, m2);
		comp.put(3, m3);
		Map<Integer, Integer> res = new LinkedHashMap<Integer, Integer>();
		res.put(1, 3);
		res.put(2, 2);
		res.put(3, 4);
		
		MapToCSVFile mapToCSV = new MapToCSVFile();
		mapToCSV.mapToCSV(comp);	
	}
	
	/**
	 * Method for testing MapToCSVComplex()
	 * @throws IOException 
	 */
	@Test
	public void testMapToCSVComplex() throws IOException {
		Map<String, String> m1 = new LinkedHashMap<String, String>();
		m1.put("key1", "value1");
		Map<String, String> m2 = new LinkedHashMap<String, String>();
		m2.put("key2", "value2");
		Map<String, String> m3 = new LinkedHashMap<String, String>();
		m3.put("key3", "value3");
		Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
		comp.put(1, m1);
		comp.put(2, m2);
		comp.put(3, m3);
		Map<Integer, Integer> res = new LinkedHashMap<Integer, Integer>();
		res.put(1, 3);
		res.put(2, 2);
		res.put(3, 4);
		
		MapToCSVFile mapToCSV = new MapToCSVFile();
		mapToCSV.mapToCSVComplex(comp);	
	}
	
	/**
	 * Method for testing MapToCSVComplex()
	 * @throws IOException 
	 */
	@Test
	public void testMapToCSVComplex2() throws IOException {
		Map<String, String> m1 = new LinkedHashMap<String, String>();
		m1.put("key1", "value1");
		m1.put("key2", "value2");
		m1.put("key5", "value5");
		Map<String, String> m2 = new LinkedHashMap<String, String>();
		m2.put("key2", "value2");
		m2.put("key4", "value4");
		m2.put("key6", "value6");
		m2.put("key7", "value7");
		Map<String, String> m3 = new LinkedHashMap<String, String>();
		m3.put("key3", "value3");
		m3.put("key4", "value4");
		m3.put("key8", "value8");
		m3.put("key5", "value5");
		m3.put("key9", "value9");
		m3.put("key10", "value10");
		Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
		comp.put(1, m1);
		comp.put(2, m2);
		comp.put(3, m3);
		Map<Integer, Integer> res = new LinkedHashMap<Integer, Integer>();
		res.put(1, 3);
		res.put(2, 2);
		res.put(3, 4);
		
		MapToCSVFile mapToCSV = new MapToCSVFile();
		mapToCSV.mapToCSVComplex(comp);	
	}

}
