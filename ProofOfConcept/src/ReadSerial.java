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

	public static ArrayList<ESPLocation> read() {
		// System.out.println("read starting");

		BufferedReader br = null;
		FileReader fr = null;
		List<String> output = new ArrayList<String>();

		String mac = "";
		long timestamp = 0;
		List<String> ssid = new ArrayList<String>();
		List<String> addresses = new ArrayList<String>();
		List<Integer> signal = new ArrayList<Integer>();

		try {

			// br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			mac = "";
			timestamp = 0;
			ssid = new ArrayList<String>();
			addresses = new ArrayList<String>();
			signal = new ArrayList<Integer>();

			while ((sCurrentLine = br.readLine()) != null) {
				output.add(sCurrentLine);

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

		timestamp = Long.parseLong(output.get(0));
		mac = output.get(1);
		for (int i = 2; i < output.size(); i++) {
			ssid.add(output.get(i));
			addresses.add(output.get(i + 1));
			signal.add(Integer.valueOf(output.get(i + 2)));
			i += 2;
		}

		ESPLocation location = new ESPLocation(timestamp, mac, ssid, addresses, signal);
		locations.add(location);

		return locations;

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("Starting Read");
		while (true) {
			read();
			System.out.println(locations.get(0).MACAddress);
		}
	}

}