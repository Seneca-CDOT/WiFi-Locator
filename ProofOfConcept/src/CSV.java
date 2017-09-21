import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;

/**
 * @author sammydamdam
 */
public class CSV {
	List<Node> list;

	public CSV(List<Node> list) {
		this.list = list;

	}

	public void writeFile() {
		try {
			String dir = System.getProperty("user.dir");
			File survey = new File(dir + "survey.csv");
			PrintWriter output = new PrintWriter(survey);

			output.write("NodeNum");
			output.write(",");
			output.write("Mac");
			output.write(",");
			output.write("SSID");
			output.write(",");
			output.write("SignalStrength");
			output.write("\n");
			
			for (int i = 0; i < list.size(); i++) {

				for (int j = 0; j < list.get(i).esp.size(); j++) {
					output.write(String.valueOf(i + 1));
					output.write(",");
					output.write(String.valueOf(list.get(i).esp.get(j).macAddrs));
					output.write(",");
					output.write(String.valueOf(list.get(i).esp.get(j).SSID));
					output.write(",");
					output.write(String.valueOf(list.get(i).esp.get(j).signalStrength));
					output.write("\n");
				}
				output.write("\n");
			}

			System.out.println("data is in file");
			output.close();
		} catch (IOException e) {

		}

	}

}
