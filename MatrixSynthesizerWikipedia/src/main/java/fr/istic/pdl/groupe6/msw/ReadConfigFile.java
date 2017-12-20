package fr.istic.pdl.groupe6.msw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadConfigFile {
	List<String> listeToCompareWP = new ArrayList<String>();
	List<String> listeToCompareWD = new ArrayList<String>();
	
	public List<String> getListWP() {

		try {
			File entree = new File("configFile.json");
			BufferedReader br = new BufferedReader(new FileReader(entree));
			String ligne;

			while ((ligne = br.readLine()) != null) {
				listeToCompareWP.add(ligne);
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return listeToCompareWP;
	}
	
	public List<String> getListWD() {

		try {
			File entree = new File("configFileWD.json");
			BufferedReader br = new BufferedReader(new FileReader(entree));
			String ligne;

			while ((ligne = br.readLine()) != null) {
				listeToCompareWD.add(ligne);
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return listeToCompareWD;
	}
}
