import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ethchil
 * @author sammydamdam
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

		while (esp == null) {
			try {

				// br = new BufferedReader(new FileReader(FILENAME));
				fr = new FileReader(FILENAME);
				br = new BufferedReader(fr);

				BaseFunc.sleep(1200);

				String sCurrentLine;

				output = new ArrayList<String>();

				while (br.ready()) {
					BaseFunc.sleep(100);

					sCurrentLine = br.readLine();
					if (!sCurrentLine.trim().isEmpty()) {
						output.add(sCurrentLine);
					}
					System.out.println(sCurrentLine);

				}
				if (output.size() > 1) {
					timestamp = Long.parseLong(output.get(0));
					MACAddress = output.get(1);
					ssid = output.get(2).split(",");
					signal = output.get(3).split(",");
					mac = output.get(4).split(",");

					List<String> ssids = new ArrayList<String>();
					for (int i = 0; i < ssid.length; i++) {
						System.out.println("==== ssid ========");
						System.out.println(ssid[i]);
						System.out.println("==== ssid ========");
						ssids.add(ssid[i]);
					}
					List<String> signals = new ArrayList<String>();
					for (int i = 0; i < signal.length; i++) {
						System.out.println("==== signal ========");
						System.out.println(signal[i]);
						System.out.println("============");
						signals.add(signal[i]);
					}
					List<String> macs = new ArrayList<String>();
					for (int i = 0; i < mac.length; i++) {
						System.out.println("==== Macs ========");
						System.out.println(mac[i]);
						System.out.println("==============");
						macs.add(mac[i]);
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
		}

		return esp;

	}

	public static Node read(int Num) {

		BufferedReader br = null;
		FileReader fr = null;
		List<String> output = new ArrayList<String>();
		Node node = null;

		String[] ssid;
		String[] mac;
		String[] signal;

		while (node == null) {
			try {

				fr = new FileReader(FILENAME);
				br = new BufferedReader(fr);

				BaseFunc.sleep(1200);

				String sCurrentLine;

				output = new ArrayList<String>();

				while (br.ready()) {
					BaseFunc.sleep(100);

					sCurrentLine = br.readLine();
					if (!sCurrentLine.trim().isEmpty()) {
						output.add(sCurrentLine);
					}
					
					System.out.println(sCurrentLine);

				}
				if (output.size() > 1) {
					while (output.get(0).length() < 2) {
						output.remove(0);
					}

					ssid = output.get(2).split(",");
					signal = output.get(3).split(",");
					mac = output.get(4).split(",");
					
					System.out.println(output);
					List<String> ssids = new ArrayList<String>();
					for (int i = 0; i < ssid.length; i++) {
						System.out.println("==== ssid ========");
						System.out.println(ssid[i]);
						System.out.println("============");
						ssids.add(ssid[i]);
					}
					List<String> signals = new ArrayList<String>();
					for (int i = 0; i < signal.length; i++) {
						System.out.println("==== SIGNAL ========");
						System.out.println(signal[i]);
						System.out.println("============");
						signals.add(signal[i]);
					}
					List<String> macs = new ArrayList<String>();
					for (int i = 0; i < mac.length; i++) {
						System.out.println("==== MAC ========");
						System.out.println(mac[i]);
						System.out.println("============");
						macs.add(mac[i]);

					}
					node = new Node(Num, macs, signals, ssids);
				}

			} catch (IOException e) {
				System.out.println("Error");
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
		}

		return node;

	}

}