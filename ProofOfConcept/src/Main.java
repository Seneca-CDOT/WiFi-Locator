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

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame("Create Nodes");
		JButton saveNode = new JButton();
		JPanel pane = new JPanel();

		saveNode.setText("Create New Node");
		window.setSize(500, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.add(pane);
		saveNode.setPreferredSize(new Dimension(200, 40));
		pane.add(saveNode);
		
		HashMap<List<String>, List<String>> nodes = new HashMap<List<String>, List<String>>();


		saveNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("button pressed");
				System.out.println(getInfo("Access")); // iwconfig wlp2s0| grep "Signal"
				
				List<String> Address = new ArrayList<String>();
				List<String> Signal = new ArrayList<String>();
				Address = getInfo("Access");
				Signal = getInfo("Signal");
				
				
				for(int i = 0; i < Signal.size(); i++)
				{
					StringBuilder sbaddress = new StringBuilder(Address.get(i)); //getting access point

					sbaddress.delete(0, 59);
					String address = sbaddress.toString();
					System.out.println(address);
					Address.set(i, address);
				}
				
				for(int i = 0; i < Signal.size(); i++)
				{
					StringBuilder sbsignal = new StringBuilder(Signal.get(i)); //getting signal strength

					sbsignal.delete(0, 43);
					sbsignal.delete(3, 7);
					String signal = sbsignal.toString();
					System.out.println(signal);
					Signal.set(i, signal);
				}
				
				nodes.put(Address, Signal);
				
			}
		});

	}

	public static List<String> getInfo(String command) {
		String s = null;
		List<String> output = new ArrayList<String>();

		try {
			
			
			// run the terminal command
			// using the Runtime exec method:
			Process p = Runtime.getRuntime().exec("iwconfig wlp2s0");

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			//System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				if(s.contains(command))
				{
					System.out.println(s);
					output.add(s);
				}
			}

			// read any errors from the attempted command
			//System.out.println("Here is the standard error of the command (if any):\n");
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
