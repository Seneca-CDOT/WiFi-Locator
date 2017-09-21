import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LocationDump {
	List<ESPDevice> locations;
	int closestNode;
	List<Float> scores;

	public LocationDump(List<ESPDevice> UPos, int closestNode /*, List<Float> scores*/ ) {
		locations = UPos;
		this.closestNode = closestNode;
		// this.scores = scores;

	}

	public void writeFile() {
		try {
			String dir = System.getProperty("user.dir");
			File survey = new File(dir + "locations.txt");
			FileWriter output = new FileWriter(survey, true);

			output.write("Closest Node is " + String.valueOf(closestNode));
			output.write("\n");
//			output.write("Timestamp: " + String.valueOf(locations.timestamp));
//			output.write("\r\n");
//			output.write("MAC of ESP is " + location.mac);
//			output.write("\r\n");
			for (int i = 0; i < locations.size(); i++) {
				output.write(String.valueOf(locations.get(i).timestamp));
				output.write(",");
				output.write(String.valueOf(locations.get(i).macAddrs));
				output.write(",");
				output.write(String.valueOf(locations.get(i).SSID));
				output.write(",");
				output.write(String.valueOf(locations.get(i).signalStrength));
				output.write("\n");
			}

			System.out.println("location data is in file");
			output.close();
		} catch (IOException e) {

		}

	}
}
