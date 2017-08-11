import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IamUsman
 */
public class ReadSerial {

	private static final String FILENAME = "/dev/ttyUSB0";
	public static ArrayList<ESPLocation> locations = new ArrayList<ESPLocation>();

	public static ESPLocation read() {
		// System.out.println("read starting");

		BufferedReader br = null;
		FileReader fr = null;
		List<String> output = new ArrayList<String>();
		ESPLocation esp = null;

		long timestamp;
		String MACAddress;
		String[] ssid;
		String[] mac;
		String[] signal;

		try {

			// br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			String sCurrentLine;

			output = new ArrayList<String>();

			while (br.ready()) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

				sCurrentLine = br.readLine();
				output.add(sCurrentLine);
				System.out.println(sCurrentLine);

			}
			if (output.size() > 1) {
				timestamp = Long.parseLong(output.get(0));
				MACAddress = output.get(1);
				ssid = output.get(2).split(",");
				signal = output.get(3).split(",");
				mac = output.get(3).split(",");

				List<String> ssids = new ArrayList<String>();
				for (int i = 0; i < ssid.length; i++) {
					ssids.add(ssid[i]);
				}
				List<String> signals = new ArrayList<String>();
				for (int i = 0; i < signal.length; i++) {
					signals.add(signal[i]);
				}
				List<String> macs = new ArrayList<String>();
				for (int i = 0; i < mac.length; i++) {
					ssids.add(mac[i]);
				}
				esp = new ESPLocation(timestamp, MACAddress, ssids, macs, signals);

			}

			locations.add(esp);

		} catch (IOException e) {
			System.out.println("Aror");
			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		return esp;

	}

	public static Node read(int Num) {
		// System.out.println("read starting");

		BufferedReader br = null;
		FileReader fr = null;
		List<String> output = new ArrayList<String>();
		Node node = null;

		long timestamp;
		String MACAddress;
		String[] ssid;
		String[] mac;
		String[] signal;

		try {

			// br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			String sCurrentLine;

			output = new ArrayList<String>();

			while (br.ready()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

				sCurrentLine = br.readLine();
				output.add(sCurrentLine);
				System.out.println(sCurrentLine);

			}
			if (output.size() > 1) {
				while (output.get(0).length()<2) {
					output.remove(0);
				}
				timestamp = Long.parseLong(output.get(0));
				MACAddress = output.get(1);
				ssid = output.get(2).split(",");
				signal = output.get(3).split(",");
				mac = output.get(3).split(",");

				List<String> ssids = new ArrayList<String>();
				for (int i = 0; i < ssid.length; i++) {
					ssids.add(ssid[i]);
				}
				List<String> signals = new ArrayList<String>();
				for (int i = 0; i < signal.length; i++) {
					signals.add(signal[i]);
				}
				List<String> macs = new ArrayList<String>();
				for (int i = 0; i < mac.length; i++) {
					ssids.add(mac[i]);
				}
				node = new Node(Num, macs, signals, ssids);
				System.out.println(Num + "number");
			}

		} catch (IOException e) {
			System.out.println("Aror");
			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		return node;

	}

}