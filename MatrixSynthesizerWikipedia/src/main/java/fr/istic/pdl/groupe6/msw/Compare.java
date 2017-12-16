package fr.istic.pdl.groupe6.msw;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
 *        Class for checking the coincidence between elements
 * 
 */

public class Compare {

	/**
	 * Method that checks if all infobox are not empty
	 * 
	 * @param comp
	 *            Variable of type Map<Integer, Map>, Key = pageid, value =
	 *            parameters of infobox
	 * @return res Return true if all infobox are not empty
	 */
	public boolean checkInfoboxContent(Map<Integer, Map> comp) {
		Iterator<Integer> it = comp.keySet().iterator();
		boolean res = true;
		while (it.hasNext()) {
			int key = (Integer) it.next();
			// assert comp.get(key).isEmpty();
			if (comp.get(key).isEmpty()) {
				res = false;
				return res;
			}
		}
		return res;
	} // TODO make test

	/**
	 * Method that checks if each element has the infobox parameter
	 * 
	 * @param comp
	 *            Variable of type Map<Integer, Map>, Key = pageid, value =
	 *            parameters of infobox
	 * @return res True for each element if it contains the infobox parameter
	 */
	public Map<Integer, Boolean> checkParameterInfobox(Map<Integer, Map> comp) {
		Map<Integer, Boolean> res = new LinkedHashMap<Integer, Boolean>();
		Iterator<Integer> it = comp.keySet().iterator();
		while (it.hasNext()) {
			int key = (Integer) it.next();
			if (comp.get(key).containsKey("infobox")) {
				res.put(key, true);
			} else {
				res.put(key, true);
			}
		}
		return res;
	}

	/**
	 * Method that checks if all elements have the infobox parameter
	 * 
	 * @param comp
	 *            Variable of type Map<Integer, Map>, Key = pageid, value =
	 *            parameters of infobox
	 * @return res True for all elements have the infobox parameter
	 */
	public boolean checkAllParameterInfobox(Map<Integer, Map> comp) {
		boolean res = true;
		Iterator<Integer> it = comp.keySet().iterator();
		while (it.hasNext()) {
			int key = (Integer) it.next();
			if (!comp.get(key).containsKey("infobox")) {
				res = false;
				return res;
			}
		}
		return res;
	} // TODO add test

	/**
	 * Method that return the infobox parametre for each element
	 * 
	 * @param comp
	 *            Variable of type Map<Integer, Map>, Key = pageid, value =
	 *            parameters of infobox
	 * @return res Map<Integer, String>, Key = pageid, Value = content of infobox
	 *         parameter
	 */
	public Map<Integer, String> getContentParameterInfobox(Map<Integer, Map> comp) {
		Map<Integer, String> res = new LinkedHashMap<Integer, String>();
		Iterator<Integer> it = comp.keySet().iterator();
		while (it.hasNext()) {
			int key = (Integer) it.next();
			res.put(key, (String) comp.get(key).get("infobox"));
		}
		return res;
	}

	/**
	 * Method that return the number of parameter for each infobox
	 * 
	 * @param comp
	 *            Variable of type Map<Integer, Map>, Key = pageid, value =
	 *            parameters of infobox
	 * @return res Map<Integer, Integer>, Key = pageid, Value = number of parameters
	 */
	public Map<Integer, Integer> getNumParametres(Map<Integer, Map> comp) {
		Map<Integer, Integer> res = new LinkedHashMap<Integer, Integer>();
		Iterator<Integer> it = comp.keySet().iterator();
		while (it.hasNext()) {
			int key = (Integer) it.next();
			res.put(key, comp.get(key).size());
		}
		return res;
	}

	/**
	 * Method that returns the parameters in common between all elements
	 * 
	 * @param comp
	 *            Variable of type Map<Integer, Map>, Key = pageid, value =
	 *            parameters of infobox
	 * @return res Parameter in common
	 */
	public Set<String> getIntersection(Map<Integer, Map> comp) {
		Set<String> res = new LinkedHashSet<String>();
		boolean bool = true;
		Iterator<Integer> it = comp.keySet().iterator();
		while (it.hasNext()) {
			int key = (Integer) it.next();
			Set<String> cur = comp.get(key).keySet();
			if (bool) {
				for (String cle : cur) {
					res.add(cle);
				}
				bool = false;
			}

			Set<String> prob = new LinkedHashSet<String>();
			for (String cle : res) {
				prob.add(cle);
			}
			for (String cle : prob) {
				if (!cur.contains(cle)) {
					res.remove(cle);
				} else {
					res.add(cle);
				}
			}
		}
		return res;
	}

	/**
	 * Method that calculates the match percentage between the elements
	 * 
	 * @param comp
	 *            Variable of type Map<Integer, Map>, Key = pageid, value =
	 *            parameters of infobox
	 * @return res Match percentage
	 */
	public float getPercentageMatch(Map<Integer, Map> comp) {
		float inter = getIntersection(comp).size();
		float avg = 0;
		Iterator<Integer> it = comp.keySet().iterator();
		while (it.hasNext()) {
			int key = (Integer) it.next();
			avg += comp.get(key).size();
		}
		return inter * comp.size() / avg;
	} // TODO add test
}
