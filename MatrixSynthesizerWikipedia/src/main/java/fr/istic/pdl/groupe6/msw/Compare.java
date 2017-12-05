package fr.istic.pdl.groupe6.msw;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

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
 * Class to load configuration file
 * 
 */
public class Compare {
	
	public boolean checkProperties(Map<Integer, Map> comp) {
        Iterator it = comp.keySet().iterator();
        while(it.hasNext()){
        	int key = (Integer) it.next();
        	//System.out.println(key + "->" + comp.get(key));
//        	assert comp.get(key).isEmpty();
	        if(comp.get(key).isEmpty()) {
	        	System.out.println("Error: infobox is empty");
	        }
        	if(!comp.get(key).containsKey("infobox")) {
        		return false;
        	}
        }
        return true;
	}	
	
	public Map<Integer, Boolean> checkParameterInfobox(Map<Integer, Map> comp) {
		Map<Integer, Boolean> res = new LinkedHashMap<Integer, Boolean>(); 
        Iterator it = comp.keySet().iterator();
        while(it.hasNext()){
        	int key = (Integer) it.next();
        	if(comp.get(key).containsKey("infobox")) {
        		res.put(key, true);
        	} else {
        		res.put(key, true);
        	}
        }
        return res;
	}
	
	public Map<Integer, String> getContentParameterInfobox(Map<Integer, Map> comp) {
		Map<Integer, String> res = new LinkedHashMap<Integer, String>(); 
        Iterator it = comp.keySet().iterator();
        while(it.hasNext()){
        	int key = (Integer) it.next();
        	res.put(key, (String) comp.get(key).get("infobox"));
        }
        return res;
	}
	
	public Map<Integer, Integer> getNumParametres(Map<Integer, Map> comp) {
		Map<Integer, Integer> res = new LinkedHashMap<Integer, Integer>(); 
        Iterator it = comp.keySet().iterator();
        while(it.hasNext()){
        	int key = (Integer) it.next();
        	res.put(key, comp.get(key).size());
        }
        return res;
	}
	
	public Set<String> getIntersection(Map<Integer, Map> comp) {
		Set<String> res = new LinkedHashSet<String>();
		boolean bool = true;
        Iterator it = comp.keySet().iterator();
        while(it.hasNext()){
        	int key = (Integer) it.next();
        	Set<String> cur = comp.get(key).keySet();
        	if(bool) {
        		for(String cle : cur) {
            		res.add(cle);
            	}
        		bool = false;
        	}
        	
        	Set<String> prob = new LinkedHashSet<String>();
        	for(String cle : res) {
        		prob.add(cle);
        	}
    		for(String cle : prob) {
    			if(!cur.contains(cle)) {
    				res.remove(cle);
    			} else {
    				res.add(cle);
    			}
            }
        }
        return res;
	}
	
	public float getPercentageMatch(Map<Integer, Map> comp) {
		float inter = getIntersection(comp).size();
		float avg = 0;
		Iterator it = comp.keySet().iterator();
        while(it.hasNext()){
        	int key = (Integer) it.next();
        	avg += comp.get(key).size();
        }
        return inter * comp.size() / avg;	
	}
}
