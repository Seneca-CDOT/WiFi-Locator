import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame("Create Nodes");
		JButton saveNode = new JButton();
		JPanel pane = new JPanel();
		JButton findLocation = new JButton();
		JButton createCSV = new JButton();

		saveNode.setText("Create New Node");
		window.setSize(500, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.add(pane);
		saveNode.setPreferredSize(new Dimension(200, 40));
		pane.add(saveNode);
		findLocation.setText("Find Location");
		findLocation.setPreferredSize(new Dimension(200, 40));
		pane.add(findLocation);
		createCSV.setText("Create CSV");
		createCSV.setPreferredSize(new Dimension(200, 40));
		pane.add(createCSV);

		// HashMap<List<String>, List<String>> nodes = new HashMap<List<String>,
		// List<String>>();
		List<Node> nodes = new ArrayList<Node>();

		saveNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("saveNode button pressed");
				System.out.println(getInfo("Access")); // iwconfig wlp2s0| grep "Signal"

				List<String> Address = new ArrayList<String>();
				List<String> Signal = new ArrayList<String>();
				Address = getInfo("Access");
				Signal = getInfo("Signal");

				for (int i = 0; i < Signal.size(); i++) {
					StringBuilder sbaddress = new StringBuilder(Address.get(i)); // getting access point

					sbaddress.delete(0, 59);
					String address = sbaddress.toString();
					System.out.println(address);
					Address.set(i, address);
				}

				for (int i = 0; i < Signal.size(); i++) {
					StringBuilder sbsignal = new StringBuilder(Signal.get(i)); // getting signal strength

					sbsignal.delete(0, 44);
					sbsignal.delete(3, 7);
					String signal = sbsignal.toString();
					signal = signal.trim();
					Signal.set(i, signal);
					System.out.println(signal);
				}

				nodes.add(new Node(nodes.size() + 1, Address, Signal));

			}
		});

		createCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("CSV Button pressed");
				CSV csv = new CSV(nodes);
				csv.writeFile();

			}
		});

		findLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("findLocation button pressed");

				List<String> currentAddress = new ArrayList<String>();
				List<String> currentSignal = new ArrayList<String>();
				currentAddress = getInfo("Access");
				currentSignal = getInfo("Signal");

				for (int i = 0; i < currentAddress.size(); i++) {
					StringBuilder sbaddress = new StringBuilder(currentAddress.get(i)); // getting access point

					sbaddress.delete(0, 59);
					String address = sbaddress.toString();
					System.out.println(address);
					currentAddress.set(i, address);
				}

				for (int i = 0; i < currentSignal.size(); i++) {
					StringBuilder sbsignal = new StringBuilder(currentSignal.get(i)); // getting signal strength

					sbsignal.delete(0, 44);
					sbsignal.delete(3, 7);
					String signal = sbsignal.toString();
					signal = signal.trim();
					System.out.println(signal);
					currentSignal.set(i, signal);
				}

				int closestNode = 0;
				int score = -100000;
				int CurScore = 0;

				for (int i = 0; i < nodes.size(); i++) {
					for (int j = 0; j < currentAddress.size(); j++) {
						if (nodes.get(i).MacAddr.contains(currentAddress.get(j))) {
							System.out.println(nodes.get(i).NodeStrength.get(nodes.get(i).MacAddr.indexOf(currentAddress.get(j))).length());
							
							CurScore += Integer.parseInt(
									nodes.get(i).NodeStrength.get(nodes.get(i).MacAddr.indexOf(currentAddress.get(j))))
									- Integer.parseInt(currentSignal.get(j));
						}
					}
					if (CurScore  > score) {
						score = CurScore;
						closestNode = i++;
					}
				}
				System.out.println(closestNode + "Closest Node Is");

			}
		});

	}

	public static List<String> getInfo(String command) {
		String s = null;
		List<String> output = new ArrayList<String>();

		try {

			// run the terminal command
			// using the Runtime exec method:
			Process p = Runtime.getRuntime().exec("iwlist wlp2s0 scan");

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			// System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				if (s.contains(command)) {
					System.out.println(s);
					output.add(s);
				}
			}

			// read any errors from the attempted command
			// System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				return output;
			}

		} catch (IOException e) {
			System.out.println("Exception occurred.");
			e.printStackTrace();
			System.exit(-1);
		}
		return output;

	}

}
