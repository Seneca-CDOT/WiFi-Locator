import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSV {
	List<Node> list;

	public CSV(List<Node> list) {
		this.list = list;

	}

	public void writeFile() {
		try {
			String dir = System.getProperty("user.dir");
			File survey = new File(dir + "survey.csv");
			FileWriter output = new FileWriter(survey, true);

			for (int i = 0; i < list.size(); i++) {
				output.write(list.get(i).NodeNum); 
				output.write(list.get(i).MacAddr.toString()); 
				output.write(list.get(i).NodeStrength.toString());
			}
			
			System.out.println("data is in file");
			output.close();
		}catch(IOException e) {
			
		}
		
	}

}
