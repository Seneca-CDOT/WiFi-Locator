import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



/**
 * @author sammydamdam
 */
public class CSV {
	List<Node> list;

	public CSV(List<Node> list) {
		this.list = list;

	}
	
	/** 
	 *  Load Previously detected nodes
	 *  
	 * */
	public List<Node> readFile(String filename) {
		File file= new File(filename);

        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        // Read lines from CSV
        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");
                lines.add(Arrays.asList(values));
            }
            System.out.println(lines);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        List<ESPDevice> devices = new ArrayList<ESPDevice>();
        int prevLineId = 1;
        
        // Parse lines into Node list
        for(int i =1; i < lines.size(); i++) {	
        	
        	ESPDevice esp = new ESPDevice(0, lines.get(i).get(1), Integer.parseInt(lines.get(i).get(3)), lines.get(i).get(2));
        	
        	// Belongs to the same Node
        	if (prevLineId == Integer.parseInt(lines.get(i).get(0)) && (i != lines.size()-1)) {
        		devices.add(esp);
        		
        	} else { // Belongs to the same Node
        		if (i == lines.size()-1) {
        			devices.add(esp);
        		}

        		Node n = new Node(new ArrayList<ESPDevice>(devices));
        		n.id = prevLineId;
        		list.add(n);
        		
        		prevLineId = Integer.parseInt(lines.get(i).get(0));
        		devices.clear();
        		devices.add(esp);
        	}
        }
        
        return list;
	}

	public void writeFile(String filename) {
		try {
			File survey = new File(filename);
			FileWriter output = new FileWriter(survey, false);

			output.write("NodeID");
			output.write(",");
			output.write("Mac");
			output.write(",");
			output.write("SSID");
			output.write(",");
			output.write("SignalStrength");
			output.write(",");
			output.write("NodeScore");
			
			output.write("\n");
			
			for (int i = 0; i < list.size(); i++) {

				for (int j = 0; j < list.get(i).esp.size(); j++) {
					output.write(String.valueOf(list.get(i).id));
					output.write(",");
					output.write(String.valueOf(list.get(i).esp.get(j).macAddrs));
					output.write(",");
					output.write(String.valueOf(list.get(i).esp.get(j).SSID));
					output.write(",");
					output.write(String.valueOf(list.get(i).esp.get(j).signalStrength));
					output.write(",");
					output.write(String.valueOf(list.get(i).score));
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
