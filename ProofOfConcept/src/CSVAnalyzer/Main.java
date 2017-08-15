package CSVAnalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String csvFileSurvey = "/run/media/yaosa/YAOSA/ProofOfConceptsurvey.csv"; // replace "" with file name + directory
		String csvFileNode = "";
		
		BufferedReader br = null;
		BufferedReader br2 = null;
		String line = "";
		
		List<Node> Locations = new ArrayList<Node>();
		List<Node> SamplePoints = new ArrayList<Node>();
		
		List<String> macs = new ArrayList<String>();
		List<String> SSIDs = new ArrayList<String>();
		List<String> Signal = new ArrayList<String>();

		try {

			br = new BufferedReader(new FileReader(csvFileSurvey));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				if(line.length() > 2)
				{
					String[] csvline = line.split(",");
					macs.add(csvline[0]);
					SSIDs.add(csvline[1]);
					Signal.add(csvline[2]);

					System.out.println(csvline[0]); //modify here to do different things
				}
				else
				{
					Locations.add(new Node(Locations.size(), macs, Signal, SSIDs));
					macs.clear();
					SSIDs.clear();
					Signal.clear();
				}
			}
			
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {

			br2 = new BufferedReader(new FileReader(csvFileNode));
			while ((line = br2.readLine()) != null) {
				// use comma as separator
				if(line.length() > 2)
				{
					String[] csvline = line.split(",");
					macs.add(csvline[0]);
					SSIDs.add(csvline[1]);
					Signal.add(csvline[2]);

					System.out.println(csvline[0]); //modify here to do different things
				}
				else
				{
					Locations.add(new Node(Locations.size(), macs, Signal, SSIDs));
					macs.clear();
					SSIDs.clear();
					Signal.clear();
				}
			}
			
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
