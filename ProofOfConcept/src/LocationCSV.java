import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author sammydamdam
 */
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
			output.write("\r\n");
			output.write("MAC of ESP is " + location.mac);
			output.write("\r\n");
			for (int i = 0; i < location.MacAddrs.size(); i++) {
				output.write(location.MacAddrs.get(i));
				output.write(",");
				output.write(location.SSID.get(i));
				output.write(",");
				output.write(location.Signal.get(i));
				output.write("\r\n");
			}
			
			System.out.println("location data is in file");
			output.close();
		} catch (IOException e) {

		}

	}

}
