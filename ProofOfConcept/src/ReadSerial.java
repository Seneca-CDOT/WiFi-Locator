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

	public static List<String> read() {
		// System.out.println("read starting");

		BufferedReader br = null;
		FileReader fr = null;
		List<String> output = new ArrayList<String>();
		
		long timestamp;
		String MACAddress;
		List<String> ssid;
		List<String> mac;
		List<Integer> signal;

		try {

			// br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			output = new ArrayList<String>();

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
		MACAddress = output.get(1);
		

		return output;

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