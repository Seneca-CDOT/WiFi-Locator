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
	
	static List<Node> nodes = new ArrayList<Node>();
	
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
		

		saveNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("saveNode button pressed");
				
				nodes.add(GetOutput());
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

				Node UPos = GetOutput();
				
				
				int closestNode = 0;
				int score = 100000;
				int CurScore = 0;
				int counter = 1;

				System.out.println("Calculating Fuzzy Logic");
				for (int i = 0; i < nodes.size(); i++) {
					for (int j = 0; j < UPos.MacAddr.size(); j++) {
						if (nodes.get(i).MacAddr.contains(UPos.MacAddr.get(j))) {
							System.out.println("Calc " + j + " = " + Math.abs((Integer.parseInt(
									nodes.get(i).NodeStrength.get(nodes.get(i).MacAddr.indexOf(UPos.MacAddr.get(j))))
									- Integer.parseInt(UPos.NodeStrength.get(j)))));
							
							CurScore += Math.abs(Integer.parseInt(
									nodes.get(i).NodeStrength.get(nodes.get(i).MacAddr.indexOf(UPos.MacAddr.get(j))))
									- Integer.parseInt(UPos.NodeStrength.get(j)));
						counter++;
						}
						
					}
					
					System.out.println("Finished Calc of Node " + i);
					
					CurScore = CurScore/counter;
					
					System.out.println(CurScore);
					if (CurScore  < score) {
						score = CurScore;
						closestNode = i + 1;
					}
				}
				System.out.println("Closest Node Is " + closestNode);

			}
		});

	}

	public static Node GetOutput()
	{
		List<String> Address = new ArrayList<String>();
		List<String> Signal = new ArrayList<String>();
		List<String> ID = new ArrayList<String>();
		
		Address = getOutputRaw("Address");
		Signal = getOutputRaw("Signal");
		ID = getOutputRaw("ESSID");
		

		for (int i = 0; i < Address.size(); i++) {
			StringBuilder sbaddress = new StringBuilder(Address.get(i)); // getting access point
			
			sbaddress.delete(0, 29);
			String address = sbaddress.toString();
			Address.set(i, address);
			
			System.out.println(address);
		}

		for (int i = 0; i < Signal.size(); i++) {
			StringBuilder sbsignal = new StringBuilder(Signal.get(i)); // getting signal strength

			sbsignal.delete(0, 49);
			sbsignal.delete(3, 7);
			String signal = sbsignal.toString();
			signal = signal.trim();
			Signal.set(i, signal);
			
			System.out.println(signal);
		}
		
		for(int i = 0; i < ID.size(); i++)	{
			StringBuilder sbID = new StringBuilder(ID.get(i));
			
			sbID.delete(0, 6);
			sbID.delete(sbID.length()-1, sbID.length());
			String address = sbID.toString();
			Address.set(i, address);
			
		}

		return new Node(nodes.size() + 1, Address, Signal, ID);
	}
	
	public static List<String> getOutputRaw(String command) {
		String s = null;
		List<String> output = new ArrayList<String>();

		try {

			// run the terminal command
			// using the Runtime exec method:
			Process p = Runtime.getRuntime().exec("iwlist wlp2s0 scan");
			
			try {
			    Thread.sleep(5000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
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
