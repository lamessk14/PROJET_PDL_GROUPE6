package fr.istic.pdl.groupe6.msw;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import fr.istic.pdl.groupe6.msw.Compare;

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

public class CompareTest {
	
	/**
	 * @param m1
	 *            premier infobox
	 * @param m2
	 *            deuxième infobox
	 * @param m3
	 *            deuxième infobox
	 * @return true if All infobox have the infobox parameter
	 */
	@Test
	public void testCheckParameterInfobox() {
		Map<String, String> m1 = new LinkedHashMap<String, String>();
		m1.put("infobox","Country");
		m1.put("case1","Value case1");
		m1.put("case2","value case2");
		Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
		comp.put(1, m1);
		comp.put(2, m1);
		comp.put(3, m1);
		Map<Integer, Boolean> res = new LinkedHashMap<Integer, Boolean>();
		res.put(1,true);
		res.put(2,true);
		res.put(3,true);
		Compare compare = new Compare();
		assertEquals("All objects have the infobox parameter", compare.checkParameterInfobox(comp), res);
	}
	
	/**
	 * @param m1
	 *            premier infobox
	 * @param m2
	 *            deuxième infobox
	 * @param m3
	 *            troisième infobox : without parameter "infobox"
	 * @return true if one infobox does not have the infobox parameter
	 */
	@Test
	public void testCheckParameterInfobox2() {
		Map<String, String> m1 = new LinkedHashMap<String, String>();
		m1.put("infobox","Country");
		m1.put("case1","Value case1");
		m1.put("case2","value case2");
		Map<String, String> m3 = new LinkedHashMap<String, String>();
		m3.put("infooooox","Country");
		m3.put("case1","Value case1");
		m3.put("case2","value case2");
		Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
		comp.put(1, m1);
		comp.put(2, m1);
		comp.put(3, m3);
		Map<Integer, Boolean> res = new LinkedHashMap<Integer, Boolean>();
		res.put(1,true);
		res.put(2,true);
		res.put(3,false);
		Compare compare = new Compare();
		assertNotEquals("One object does not have the infobox parameter or is empty", compare.checkParameterInfobox(comp), res);
	}
	
	@Test
	public void testGetContentParameterInfobox1() {
		Map<String, String> m1 = new LinkedHashMap<String, String>();
		m1.put("infobox","Country");
		m1.put("case1","Value case1");
		m1.put("case2","value case2");
		Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
		comp.put(1, m1);
		comp.put(2, m1);
		comp.put(3, m1);
		Map<Integer, String> res = new LinkedHashMap<Integer, String>();
		res.put(1,"Country");
		res.put(2,"Country");
		res.put(3,"Country");
		Compare compare = new Compare();
		assertTrue("Content of infobox parameter", res.equals(compare.getContentParameterInfobox(comp)));
	}
	
	@Test
	public void testGetNumParametres() {
		Map<String, String> m1 = new LinkedHashMap<String, String>();
		m1.put("infobox","Country");
		m1.put("key1","value1");
		m1.put("key2","value2");
		Map<String, String> m2 = new LinkedHashMap<String, String>();
		m2.put("key3","value3");
		m2.put("key4","value4");
		Map<String, String> m3 = new LinkedHashMap<String, String>();
		m3.put("key5","value5");
		m3.put("key6","value6");
		m3.put("key7","value7");
		m3.put("key8","value8");
		Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
		comp.put(1, m1);
		comp.put(2, m2);
		comp.put(3, m3);
		Map<Integer, Integer> res = new LinkedHashMap<Integer, Integer>();
		res.put(1,3);
		res.put(2,2);
		res.put(3,4);
		Compare compare = new Compare();
		assertEquals("Number of parameters", compare.getNumParametres(comp), res);
	}
	
	@Test
	public void testGetIntersection1() {
		Map<String, String> m1 = new LinkedHashMap<String, String>();
		m1.put("infobox","Country");
		m1.put("key1","value1");
		m1.put("key2","value2");
		Map<String, String> m2 = new LinkedHashMap<String, String>();
		m2.put("key1","value1");
		m2.put("key2","value2");
		m2.put("key4","value4");
		Map<String, String> m3 = new LinkedHashMap<String, String>();
		m3.put("key1","value1");
		m3.put("key2","value2");
		m3.put("key3","value3");
		m3.put("key4","value4");
		Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
		comp.put(1, m1);
		comp.put(2, m2);
		comp.put(3, m3);
		Set<String> res = new LinkedHashSet<String>();
		res.add("key1");
		res.add("key2");
		Compare compare = new Compare();
		assertEquals("Number of commun keys 1", compare.getIntersection(comp), res);
	}
	
	@Test
	public void testGetIntersection2() {
		Map<String, String> m1 = new LinkedHashMap<String, String>();
		m1.put("infobox","Country");
		m1.put("key1","value1");
		m1.put("key2","value2");
		Map<String, String> m2 = new LinkedHashMap<String, String>();
		m2.put("key3","value3");
		m2.put("key4","value4");
		m2.put("key5","value6");
		Map<String, String> m3 = new LinkedHashMap<String, String>();
		m3.put("infobox","Country");
		m3.put("key1","value1");
		m3.put("key2","value2");
		m3.put("key3","value3");
		m3.put("key4","value4");
		Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
		comp.put(1, m1);
		comp.put(2, m2);
		comp.put(3, m3);
		Set<String> res = new LinkedHashSet<String>();
		Compare compare = new Compare();
		assertEquals("Number of commun keys 2", compare.getIntersection(comp), res);
	}
	
	@Test
	public void testGetIntersection3() {
		Map<String, String> m1 = new LinkedHashMap<String, String>();
		m1.put("infobox","Country");
		m1.put("key1","value1");
		m1.put("key2","value2");
		Map<Integer, Map> comp = new LinkedHashMap<Integer, Map>();
		comp.put(1, m1);
		comp.put(2, m1);
		comp.put(3, m1);
		Set<String> res = new LinkedHashSet<String>();
		res.add("infobox");
		res.add("key1");
		res.add("key2");
		Compare compare = new Compare();
		assertEquals("Number of commun keys 3", compare.getIntersection(comp), res);
	}
	
	
}
