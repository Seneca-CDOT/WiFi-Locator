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
		for (int i = 0; i < nodes.size(); i++) {
			System.out.println(nodes.get(i).toString());
		}

		List<ESPLocation> locations = readLocations();
		for (int i = 0; i < locations.size(); i++) {
			System.out.println(locations.get(i).toString());
		}

	}

	public static List<Node> readSurvey() {
		String csvFileSurvey = "/home/yaosa/Downloads/survey.csv";

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
				if (line.length() > 2) {
					String[] csvline = line.split(",");
					macs.add(csvline[0]);
					SSIDs.add(csvline[1]);
					Signal.add(csvline[2]);

				} else {
					survey.add(new Node(survey.size() + 1, macs, Signal, SSIDs));
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
		return survey;

	}

	public static List<ESPLocation> readLocations() {
		String csvFileLocations = "/home/yaosa/Downloads/locations.csv";

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
					locations.add(new ESPLocation(time, MAC, macs, Signal, SSIDs));
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
