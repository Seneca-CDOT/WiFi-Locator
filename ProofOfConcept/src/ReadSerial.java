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

	public static List<String> read() {
		System.out.println("read starting");
		
		BufferedReader br = null;
		FileReader fr = null;
		List<String> output = new ArrayList<String>();

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				output.add(sCurrentLine);
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

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
		
		return output;

	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
        read();
        
    }

   
}