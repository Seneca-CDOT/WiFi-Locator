import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
	
	static List<Node> nodes      = new ArrayList<Node>();
	static List<Node> mystery    = new ArrayList<Node>();
	/* Access points */
	static SortedSet<String> ap = new TreeSet<String>();
	
	static int surveyNum = 0; 

	public static void printSurvey () {
		for (int i = 0; i < nodes.size(); i++ ) {
			System.out.println(">> Node ID        : " + nodes.get(i).id);
			System.out.println(">> Num of Devices : " + nodes.get(i).esp.size());
			System.out.println(nodes.get(i));
		}
	}
	
	public static void main(String[] args) {

		// Make Window
		JFrame window = new JFrame("Peapod");

		// Make buttons
		JButton saveNode = new JButton();
		JButton findLocation = new JButton();
		JButton report  = new JButton();
		JButton createCSV  = new JButton();
		JButton loadCSV  = new JButton();
		JButton clearNodes = new JButton();	
		
		// JPane to hold the buttons on the window
		JPanel pane = new JPanel(new FlowLayout());

		// Setup window
		window.setSize(275, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		// setup buttons
		saveNode.setText("Create New Node");
		saveNode.setPreferredSize(new Dimension(200, 40));

		clearNodes.setText("Clear All Nodes");
		clearNodes.setPreferredSize(new Dimension(200, 40));
	
		findLocation.setText("Find Location");
		findLocation.setPreferredSize(new Dimension(200, 40));
		
		report.setText("Generate Report");
		report.setPreferredSize(new Dimension(200, 40));

		createCSV.setText("Create CSV");
		createCSV.setPreferredSize(new Dimension(200, 40));

		loadCSV.setText("Load CSV");
		loadCSV.setPreferredSize(new Dimension(200, 40));
		
		// add buttons to pane
		pane.add(saveNode);
		pane.add(findLocation);
		pane.add(createCSV);
		pane.add(loadCSV);
		pane.add(clearNodes);
		pane.add(report);

		// add pane to window
		window.add(pane);
		
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("======================");
				System.out.println("Generating Report");
				System.out.println("======================");
				
				CSV csv = new CSV(nodes);
				csv.createReport("REPORT.csv", ap, mystery);
			}
		});
		
		// Create New Node action listener
		clearNodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("======================");
				System.out.println("Dropping all the Nodes");
				System.out.println("======================");
				
				surveyNum = 0;
				nodes.clear();
			}
		});
		
		// CSV dump actionlistener
		createCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("CSV Button pressed");
				// Only writes Nodes
				CSV csv = new CSV(nodes);
				csv.writeFile("registered_nodes.csv");
			}
		});
		
		// CSV dump actionlistener
		loadCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Load CSV Button pressed");
				
				CSV csv = new CSV(nodes);
				nodes = csv.readFile("registered_nodes.csv");
				printSurvey();
			}
		});
		
		// Create New Node action listener
		saveNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("===========================");
				System.out.println("Initiated Node Registration");
				System.out.println("===========================");
				// Gets node and places it in ReadSerial
				List<ESPDevice> foundDevices = SerialReader.read(false);	
				for (ESPDevice d : foundDevices){
					ap.add(d.SSID);
				}
				Node n = new Node(foundDevices);
				n.id = nodes.size() + 1;
				nodes.add(n);
				
				System.out.println("\n++ ++ ++ ++ ++ ++ ++ ++ ++ ++");
				System.out.println("++ All Registered Devices ++");
				printSurvey();
				System.out.println("++ ++ ++ ++ ++ ++ ++ ++ ++");
			}
		});
		
	
		// Find nearest node actionlistener
		findLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				surveyNum++;
				// Fuzzy logic comparison
				System.out.println("============================");
				System.out.println("Searching for Node Locations");
				System.out.println("============================");
				
				// Gets a "Node" of the current location
				List<ESPDevice> UPos = SerialReader.read(false);
				mystery.add(new Node(UPos));
				
				System.out.println("\n++ ++ ++ ++ ++ ++ ++ ++ +++");
				System.out.println("++ Retrieved new Signals ++");
				System.out.println(UPos);
				System.out.println("++ ++ ++ ++ ++ ++ ++ ++ ++ ++");

				// Vars needed
				double bestScore = 10000;
				int penalty = 0;
				int penaltyFactor = 5;
				
				Node curNode;

				// Fuzzy logic comparison
				System.out.println("=======================");
				System.out.println("Calculating Fuzzy Logic");
				System.out.println("=======================");
				for (int i = 0; i < nodes.size(); i++) {  // for each node-survey 
					
					List<Double> curScores = new ArrayList<>();

					curNode = nodes.get(i); 
					System.out.println("++ ++ ++ ++ ++");
					System.out.println("++ Node : " + nodes.get(i).id);
					
					for (int j = 0; j < UPos.size(); j++) {   // for each device 

						if (curNode.esp.contains(UPos.get(j))){
	
							ESPDevice device = curNode.esp.get(curNode.esp.indexOf(UPos.get(j)));
							int diff = Math.abs(device.signalStrength - UPos.get(j).signalStrength);

							System.out.println("Calc " + j + " = " + diff + " ESSID of " + UPos.get(j).SSID);

							curScores.add((double)diff);
						} else {
							System.out.println("Not found...");
							//double avgScore = curScores.stream().mapToDouble(d -> d).average().orElse(999);
							//curScores.add(avgScore);
							penalty += penaltyFactor;
						}
					}

					System.out.println("Finished Calc of Node " + (i+1));

					double avgScore = curScores.stream().mapToDouble(d -> d).average().orElse(999);
					if (penalty > 0) {
						curScores.add(avgScore + penalty);
						avgScore = curScores.stream().mapToDouble(d -> d).average().orElse(999);	
					}
					curNode.score = avgScore;

					System.out.println("Calculated score: " + avgScore + "\n");
					if (avgScore < bestScore) {
						bestScore = avgScore;
					}
				}
				
				ArrayList<Node> sortedNodes = new ArrayList<Node>(nodes);
				Collections.sort(sortedNodes);
				System.out.println("Closest Node Is " + sortedNodes.get(0).id);

				CSV csv = new CSV(sortedNodes); 
				csv.writeFile("s" + surveyNum + ".csv");
				

			}
		});

	}
}