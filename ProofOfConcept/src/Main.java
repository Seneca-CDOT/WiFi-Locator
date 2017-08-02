import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame("Create Nodes");
		JButton saveNode = new JButton();
		JPanel pane = new JPanel();

		window.setSize(500, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.add(pane);
		saveNode.setPreferredSize(new Dimension(100, 40));
		pane.add(saveNode);

		saveNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("button pressed");
				System.out.println(getInfo("iwconfig wlp2s0| grep \"Access\"")); // iwconfig wlp2s0| grep "Signal"
				getInfo("iwconfig wlp2s0| grep \"Signal\"");

				StringBuilder sbaddress = new StringBuilder(getInfo("iwconfig wlp2s0| grep \"Access\"")); //getting access point
				for (int i = 0; i < 49; i++) {
					sbaddress.deleteCharAt(0);
				}
				String address = sbaddress.toString();
				System.out.println(address);
				
				StringBuilder sbsignal = new StringBuilder(getInfo("iwconfig wlp2s0| grep \"Signal\"")); //getting signal strength
				for (int i = 0; i < 33; i++) {
					sbsignal.deleteCharAt(0);
				}
				String signal = sbsignal.toString();
				System.out.println(signal);
				
			}
		});

	}

	public static String getInfo(String command) {
		String s = null;

		try {

			// run the terminal command
			// using the Runtime exec method:
			Process p = Runtime.getRuntime().exec(command);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				return s;
			}

			// read any errors from the attempted command
			//System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				return s;
			}

			System.exit(0);

		} catch (IOException e) {
			System.out.println("Exception occurred.");
			e.printStackTrace();
			System.exit(-1);
		}
		return s;

	}

}
