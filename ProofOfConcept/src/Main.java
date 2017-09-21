import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ethchil
 * @author sammmydamdam
 */
public class Main {

	static List<Node> nodes = new ArrayList<Node>();

	public static void main(String[] args) {

		// Make Window
		JFrame window = new JFrame("Peapod");

		// Make 3 buttons
		JButton saveNode = new JButton();
		JButton findLocation = new JButton();
		JButton createCSV = new JButton();

		// JPane to hold the buttons on the window
		JPanel pane = new JPanel();

		// Setup window
		window.setSize(275, 200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		// setup buttons
		saveNode.setText("Create New Node");
		saveNode.setPreferredSize(new Dimension(200, 40));

		findLocation.setText("Find Location");
		findLocation.setPreferredSize(new Dimension(200, 40));

		createCSV.setText("Create CSV");
		createCSV.setPreferredSize(new Dimension(200, 40));

		// add buttons to pane
		pane.add(saveNode);
		pane.add(findLocation);
		pane.add(createCSV);

		// add pane to window
		window.add(pane);

		// Create New Node action listener
		saveNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("saveNode button pressed");

				// Gets node and places it in ReadSerial
				nodes.add(ReadSerial.read(nodes.size() + 1));
			}
		});

		// CSV dump actionlistener
		createCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("CSV Button pressed");
				System.out.println(nodes);

				// Only writes Nodes
				CSV csv = new CSV(nodes);
				csv.writeFile();
			}
		});

		// Find nearest node actionlistener
		findLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("findLocation button pressed");

				// Gets a "Node" of the current location
				ESPLocation UPos = ReadSerial.read();

				// Vars needed
				int closestNode = 0;
				int score = 100000;
				int CurScore = 0;
				int counter = 1;
				Node CurNode;

				List<Integer> scores = new ArrayList<>();

				// Fuzzy logic comparison
				System.out.println("Calculating Fuzzy Logic");
				for (int i = 0; i < nodes.size(); i++) {
					for (int j = 0; j < UPos.MacAddrs.size(); j++) {
						CurNode = nodes.get(i);

						if (CurNode.MacAddrs.contains(UPos.MacAddrs.get(j))) {

							System.out.println("Calc " + j + " = "
									+ Math.abs((Integer.parseInt(
											CurNode.NodeSignal.get(CurNode.MacAddrs.indexOf(UPos.MacAddrs.get(j))))
											- Integer.parseInt(UPos.Signal.get(j))))
									+ " ESSID of " + UPos.SSID.get(j));

							CurScore += Math.abs(Integer
									.parseInt(CurNode.NodeSignal.get(CurNode.MacAddrs.indexOf(UPos.MacAddrs.get(j))))
									- Integer.parseInt(UPos.Signal.get(j)));

							counter++;
						}
						CurScore += 5;
					}

					System.out.println("Finished Calc of Node " + (i+1));

					CurScore = CurScore / counter;
					scores.add(CurScore);

					System.out.println(CurScore);
					if (CurScore < score) {
						score = CurScore;
						closestNode = i + 1;
					}
				}
				System.out.println("Closest Node Is " + closestNode);

				LocationCSV locationCSV = new LocationCSV(UPos, closestNode, scores);
				locationCSV.writeFile();

			}
		});

	}

}
