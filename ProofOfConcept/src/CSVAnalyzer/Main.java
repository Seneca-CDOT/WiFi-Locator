package CSVAnalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Node> nodes = readSurvey();
		for (int i = 0;i<nodes.size();i++) {
			//System.out.println(nodes.get(i).toString());
		}
		

		List<ESPLocation> locations = readLocations();
		for (int i = 0; i < locations.size(); i++) {
			//System.out.println(locations.get(i).toString());
			//System.out.println(FuzzyLogic.CalcFuzzyLogic(nodes, locations));
		}
		System.out.println("Logic results are = " + FuzzyLogic.CalcFuzzyLogic(nodes, locations));

	}

	public static List<Node> readSurvey() {
		String csvFileSurvey = "CSV Files/FSOSS Building Survey/survey.csv";

		List<String> macs = new ArrayList<String>();
		List<String> SSIDs = new ArrayList<String>();
		List<String> Signal = new ArrayList<String>();

		List<Node> survey = new ArrayList<Node>();

		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(csvFileSurvey));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				
				if (line.length() > 5) {
					String[] csvline = line.split(",");
					macs.add(csvline[0]);
					SSIDs.add(csvline[1]);
					Signal.add(csvline[2]);

				} else {
					Node node = new Node(survey.size(), macs, Signal, SSIDs);
					
					survey.add(node);
					
					macs = new ArrayList<String>();
					SSIDs = new ArrayList<String>();
					Signal = new ArrayList<String>();

					

				}
			}
			survey.remove(0);

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
		
		System.out.println(survey.get(survey.size()-1).MacAddrs.size());
		return survey;

	}

	public static List<ESPLocation> readLocations() {
		String csvFileLocations = "CSV Files/FSOSS Building Survey/locations.csv";

		long time = 0;
		String MAC = "hello";

		List<String> macs = new ArrayList<String>();
		List<String> SSIDs = new ArrayList<String>();
		List<String> Signal = new ArrayList<String>();

		List<ESPLocation> locations = new ArrayList<ESPLocation>();

		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(csvFileLocations));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				if (line.contains("Closest Node")) {
					locations.add(new ESPLocation(time, MAC, SSIDs, macs, Signal));
					macs.clear();
					SSIDs.clear();
					Signal.clear();
					
				} else if (line.contains("Timestamp")){
					time = Long.valueOf(line.substring(11));
					
				} else if (line.contains("MAC of ESP")) {
					MAC = line.substring(14);
				}
				else {
					String[] info = line.split(",");
					macs.add(info[0]);
					SSIDs.add(info[1]);
					Signal.add(info[2]);
					
					
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
		return locations;

	}

}
