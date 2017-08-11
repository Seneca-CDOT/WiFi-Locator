import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LocationCSV {
	ESPLocation location;
	int closestNode;
	List<Integer> scores;

	public LocationCSV(ESPLocation UPos, int closestNode, List<Integer> scores) {
		location = UPos;
		this.closestNode = closestNode;
		this.scores = scores;

	}

	public void writeFile() {
		try {
			String dir = System.getProperty("user.dir");
			File survey = new File(dir + "locations.csv");
			FileWriter output = new FileWriter(survey, true);

			output.write("Closest Node is " + String.valueOf(closestNode));
			output.write("\r\n");
			output.write("Timestamp: " + String.valueOf(location.timestamp));
			output.write("MAC of ESP is " + location.MACAddress);
			for (int i = 0; i < location.mac.size(); i++) {
				output.write(location.mac.get(i));
				output.write(",");
				output.write(location.ssid.get(i));
				output.write(",");
				output.write(location.signal.get(i));
				output.write("\r\n");
			}
			
			System.out.println("location data is in file");
			output.close();
		} catch (IOException e) {

		}

	}

}
