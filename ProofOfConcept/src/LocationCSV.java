import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LocationCSV {
	Node location;
	int closestNode;
	List<Integer> scores;

	public LocationCSV(Node UPos, int closestNode, List<Integer> scores) {
		location = UPos;
		this.closestNode = closestNode;
		this.scores = scores;

	}

	public void writeFile() {
		try {
			String dir = System.getProperty("user.dir");
			File survey = new File(dir + "locations.csv");
			FileWriter output = new FileWriter(survey, true);

			output.write(location.NodeNum);
			output.write("\r\n");
			for (int i = 0; i < location.MacAddr.size(); i++) {
				output.write(location.MacAddr.get(i));
				output.write(",");
				output.write(location.SSID.get(i));
				output.write(",");
				output.write(location.NodeStrength.get(i));
				output.write("\r\n");
			}
			output.write("Calculations");
			output.write("\r\n");
			for (int j = 0; j < scores.size();j++) {
				output.write(j);
				output.write(",");
				output.write(scores.get(j));
				output.write("\r\n");
			}

			output.write("Closest Node is " + String.valueOf(closestNode));

			System.out.println("location data is in file");
			output.close();
		} catch (IOException e) {

		}

	}

}
